package mainpkg;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.RDFNode;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Simone on 03/02/2016.
 */
public class GraphQueryExecutor {

    public static final String PREFIX = "PREFIX movie: <http://localhost:3030/movies/>\n" +
            "PREFIX vcard: <http://www.w3.org/2001/vcard-rdf/3.0#>\n" +
            "PREFIX mo: <http://purl.org/ontology/mo/>\n" +
            "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
            "prefix owl: <http://www.w3.org/2002/07/owl#>";
    public static final String localhostURI = "http://localhost:3030/movies";
    private static final String GRAPH_QUERY = "SELECT ?actor ?name ?title WHERE {\n" +
            "  ?x movie:playRoleIn ?film .\n" +
            "  ?film movie:Title ?title.\n" +
            "  ?x vcard:FN ?actor .\n" +
            "  ?director movie:directedBy ?film .\n" +
            "  ?director vcard:FN ?name .\n" +
            "} LIMIT 200";

    public void runGraphQuery(){
        HashMap<String, Integer> st = new HashMap<String, Integer>();
        ArrayList<NodeAD> nodeADs = new ArrayList<NodeAD>();
        ArrayList<LinkAD> linkADs = new ArrayList<LinkAD>();
        String query = PREFIX + GRAPH_QUERY;
        try {
            //System.out.println("Run graph query");
            QueryExecution q = QueryExecutionFactory.sparqlService(localhostURI,
                    query);
            ResultSet results = q.execSelect();
            //ResultSetFormatter.out(System.out, results);
            /**System.out.println("Start writing json file");
            File f = new File("actor-directors.json");
            FileWriter fw = new FileWriter(f);
            BufferedWriter writer = new BufferedWriter(fw);
            writer.write("{\n" +
                    "  \"nodes\":[");
            System.out.println(results);*/
        int c = 0;
            while (results.hasNext()) {
                System.out.println("while");
                QuerySolution soln = results.nextSolution();
                if(soln!=null) {
                    System.out.println(results.nextSolution());
                    //RDFNode title = soln.get("title");
                    RDFNode actor = soln.get("actor");
                    RDFNode director = soln.get("name");
                    RDFNode film = soln.get("title");
                    if (!st.containsKey(actor.toString().replaceAll("[\\W\\s]", "")))
                        st.put(actor.toString().replaceAll("[\\W\\s]", ""), c);

                    NodeAD currActor = new NodeAD(actor.toString().replaceAll("[\\W\\s]", ""), 1);
                    c = c + 1;
                    if (!st.containsKey(director.toString().replaceAll("[\\W\\s]", "")))
                        st.put(director.toString().replaceAll("[\\W\\s]", ""), c);
                    NodeAD currDirector = new NodeAD(director.toString().replaceAll("[\\W\\s]", ""), 2);
                    LinkAD currLink = new LinkAD(st.get(director.toString().replaceAll("[\\W\\s]","")), st.get(actor.toString().replaceAll("[\\W\\s]","")));
                    if (!nodeADs.contains(currActor)) {
                        nodeADs.add(currActor);
                    }
                    if (!nodeADs.contains(currDirector)) {
                        nodeADs.add(currDirector);
                    }
                    if (!linkADs.contains(currLink)) {
                        linkADs.add(currLink);
                    }
                    if (linkADs.contains(currLink)) {
                        int i = linkADs.indexOf(currLink);
                        int oldValue = linkADs.get(i).value;
                        linkADs.get(i).value = oldValue + 1;
                    }


                    //System.out.println(actor.toString()+director.toString());
                    //writer.write("{\"name\":\""+actor+"\",\"group\":1},");
                    //writer.write("{\"name\":\""+director+"\",\"group\":2},");
                    c = c + 1;
                }
            }

            /**writer.write("],\n" +
                    "  \"links\":[");

            /*while (results.hasNext()) {
                QuerySolution soln = results.nextSolution();
                //RDFNode title = soln.get("title");
                RDFNode actor = soln.get("title");
                writer.println("{\"name\":\""+actor+"\",\"group\":1},");
            }*/
            System.out.println(nodeADs);
            System.out.println(linkADs);


            /**writer.write("]\n" +
                    "}");
            //result = results.toString();
            writer.close();**/
            writeJSONFile(nodeADs, linkADs);
        } catch (Exception e) {
            System.out.println("Exception");
            e.printStackTrace();
        }

    }

    public void writeJSONFile(ArrayList<NodeAD> n, ArrayList<LinkAD> l) {
        try {
            int k=0;
            System.out.println("Start writing json file");
            File f = new File("C:\\Users\\Simone\\Documents\\rdf_server_movie\\web\\actor-directors.json");
            FileWriter fw = new FileWriter(f);
            BufferedWriter writer = new BufferedWriter(fw);
            writer.write("{\n" +
                    "  \"nodes\":[");
            for(NodeAD node: n) {
                if(k==n.size()-1)
                    writer.write("{\"name\":\"" + node.person + "\",\"group\":"+node.group+"}");
                else
                    writer.write("{\"name\":\"" + node.person + "\",\"group\":"+node.group+"},");
                k++;
            }
            k=0;
            writer.write("],\n" +
                    "  \"links\":[");
            for(LinkAD link: l) {
                if(k==l.size()-1)
                    writer.write("{\"source\":" + link.source + ",\"target\":"+link.target+",\"value\":"+link.value+"}");
                else
                    writer.write("{\"source\":" + link.source + ",\"target\":"+link.target+",\"value\":"+link.value+"},");
                k++;
            }

            writer.write("]\n" +
                    "}");
            writer.close();
            fw.close();
            System.out.println("Json file completed");
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
