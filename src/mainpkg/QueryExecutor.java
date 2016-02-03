package mainpkg;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.RDFNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Simone on 03/02/2016.
 */
public class QueryExecutor {

    public static final String PREFIX = "PREFIX movie: <http://localhost:3030/movies/>\n" +
            "PREFIX vcard: <http://www.w3.org/2001/vcard-rdf/3.0#>\n" +
            "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
            "prefix owl: <http://www.w3.org/2002/07/owl#>";
    public static final String localhostURI = "http://localhost:3030/movies";

    public String singleQuery(String parameter, String attribute) {
        //String list = "";
        String finalTable = null;

        String query = PREFIX;
        if (attribute.equalsIgnoreCase("genre")) {
            query = query + "SELECT ?title WHERE {  \n" +
                    "  ?uri movie:Title ?title . \n" +
                    "  ?uri movie:genre ?genre .\n" +
                    "  FILTER regex(?genre, '"+parameter+"', 'i')\n" +
                    "}";
            finalTable = runSingleQuery(query, attribute);
        } else if (attribute.equalsIgnoreCase("language")) {
            query = query +  "SELECT ?title WHERE {  \n" +
                    "  ?uri movie:Title ?title . \n" +
                    "  ?uri movie:language ?language .\n" +
                    "  FILTER regex(?language, '"+parameter+"', 'i')\n" +
                    "}";
            finalTable = runSingleQuery(query, attribute);
        } else if (attribute.equalsIgnoreCase("country")) {
            query = query +  "SELECT ?title WHERE {  \n" +
                    "  ?uri movie:Title ?title . \n" +
                    "  ?uri movie:country ?country .\n" +
                    "  FILTER regex(?country, '"+parameter+"', 'i')\n" +
                    "}";
            finalTable = runSingleQuery(query, attribute);
        } else if (attribute.equalsIgnoreCase("actor")) {
            query = query + "SELECT ?title ?actor WHERE {\n" +
                    "                ?uri movie:Actor ?actor .\n" +
                    "                        ?uri movie:Title ?title.\n" +
                    "                        FILTER regex(str(?actor),'"+parameter.replaceAll(" ","")+"', 'i').\n" +
                    "            }";
            finalTable = runSingleQuery(query, attribute);
        } else if (attribute.equalsIgnoreCase("director")) {
            query = query + "SELECT ?title ?director WHERE {\n" +
                    "                ?uri movie:Director ?director .\n" +
                    "                        ?uri movie:Title ?title.\n" +
                    "                        FILTER regex(str(?director),'"+parameter.replaceAll(" ","")+"', 'i').\n" +
                    "            }";
            finalTable = runSingleQuery(query, attribute);
        } else if (attribute.equalsIgnoreCase("year")) {
            query = query + "  SELECT ?title WHERE {   \n" +
                    "  ?uri movie:Title ?title.\n" +
                    "  ?uri movie:releaseYear ?year . \n" +
                    " FILTER(?year = \""+parameter+"\").\n" +
                    "}";
            finalTable = runSingleQuery(query, attribute);
        } else if (attribute.equalsIgnoreCase("customquery")) {
            query = parameter;
            runCustomQuery(query);
            finalTable = runCustomQuery(query);
            //System.out.println(parameter);
        }


        //System.out.println(directorList);
        return finalTable;
    }

    public String formatTableHTML(ArrayList<String> list, String type) {
        String finalTable = "<table class=\"table table-hover\" >\n" +
                "            <thead >\n" +
                "            <tr >\n" +
                "            <th >#</th >\n" +
                "            <th >"+type+"</th >\n" +
                "            </tr >\n" +
                "            </thead >\n" +
                "            <tbody >";
        for (int i=0; i<list.size(); i++) {
            finalTable = finalTable +
                    "            <tr >\n" +
                    "            <td > "+(i+1)+" </td >\n" +
                    "            <td > "+list.get(i)+" </td >\n" +
                    "            </tr >\n";
        }
        finalTable = finalTable +
                "            </tbody >\n" +
                "            </table >";
        return finalTable;
    }

    public String runSingleQuery(String query, String attribute){
        ArrayList<String> list = new ArrayList<String>();
        try {

            QueryExecution q = QueryExecutionFactory.sparqlService(localhostURI,
                    query);
            ResultSet results = q.execSelect();

            //ResultSetFormatter.out(System.out, results);
            //result = results.toString();
            while (results.hasNext()) {
                QuerySolution soln = results.nextSolution();
                RDFNode x = soln.get("title");
                //System.out.println(x);
                list.add(x.toString());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return formatTableHTML(list, "title".toUpperCase());
    }

    public String runCustomQuery(String query){
        List<QuerySolution> list = null;
        try {

            QueryExecution q = QueryExecutionFactory.sparqlService(localhostURI,
                    query);
            ResultSet results = q.execSelect();

            list = ResultSetFormatter.toList(results);

            formatTableHTMLCustomQuery(list);
            //result = results.toString();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return formatTableHTMLCustomQuery(list);
    }

    public String formatTableHTMLCustomQuery(List<QuerySolution> list) {
        String finalTable = "<table class=\"table table-hover\" >\n" +
                "            <thead > " +
                "            <tr >\n" +"            <th >#</th >\n" ;
        ArrayList<String> headers = new ArrayList<String>();
        QuerySolution qeFirst = list.get(0);
        Iterator<String> attributes = qeFirst.varNames();
        while (attributes.hasNext()) {
            String tmpH = attributes.next();
            headers.add(tmpH);
            System.out.println(tmpH);
            finalTable = finalTable +
                    "            <th >"+tmpH.toUpperCase()+"</th >\n";
        }
        finalTable = finalTable + "            </tr >\n" +
                "            </thead >\n" +
                "            <tbody >";
        for (int k=0; k<list.size(); k++) {
            finalTable = finalTable + "            <tr >\n" +
                    "            <td > "+(k+1)+" </td >\n";
            for (int i=0; i<headers.size(); i++) {
                finalTable = finalTable +
                        "            <td > "+list.get(k).get(headers.get(i))+" </td >\n";
            }
            finalTable = finalTable + "            </tr >\n";
        }
        finalTable = finalTable +
                "            </tbody >\n" +
                "            </table >";
        return finalTable;
    }

}
