package mainpkg;


import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.RDFNode;

import java.util.ArrayList;

public class SPARQLInterpreter {

    public static String result;
    public static final String PREFIX = "PREFIX movie: <http://localhost:3030/movies/>\n" +
            "PREFIX vcard: <http://www.w3.org/2001/vcard-rdf/3.0#>\n" +
            "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
            "prefix owl: <http://www.w3.org/2002/07/owl#>";
    public static final String localhostURI = "http://localhost:3030/movies";

    /**
     * public static void execSelectAndPrint(String serviceURI, String query) {
     * try {
     * <p>
     * QueryExecution q = QueryExecutionFactory.sparqlService(serviceURI,
     * query);
     * ResultSet results = q.execSelect();
     * <p>
     * ResultSetFormatter.out(System.out, results);
     * result = results.toString();
     * while (results.hasNext()) {
     * QuerySolution soln = results.nextSolution();
     * RDFNode x = soln.get("x");
     * result = result + x;
     * }
     * } catch (Exception e) {
     * System.out.println(e.getMessage());
     * }
     * }
     * <p>
     * /*public static void execSelectAndProcess(String serviceURI, String query) {
     * try {
     * QueryExecution q = QueryExecutionFactory.sparqlService(serviceURI,
     * query);
     * ResultSet results = q.execSelect();
     * result = results.toString();
     * while (results.hasNext()) {
     * QuerySolution soln = results.nextSolution();
     * // assumes that you have an "?x" in your query
     * RDFNode x = soln.get("x");
     * result = result + x;
     * }
     * } catch (Exception e) {
     * System.out.println(e.getMessage());
     * }
     * }
     */

    /*public String printResult(){
        String query = SPARQLInterpreter.PREFIX+"SELECT ?title ?duration ?actor WHERE { ?uri movie:Title ?title . ?uri movie:duration ?duration . ?uri movie:Actor ?actor . }";
        SPARQLInterpreter.execSelectAndPrint(
                "http://localhost:3030/movies",query
        );

//        SPARQLInterpreter.execSelectAndProcess(
//                "http://localhost:3030/movies",
//                query);
        return result;
    }*/

//    public static void main(String[] argv) {
//        // uploadRDF(new File("test.rdf"), );
//
//        String query = PREFIX+"SELECT ?title ?duration ?actor WHERE { ?uri movie:Title ?title . ?uri movie:duration ?duration . ?uri movie:Actor ?actor . }";
//        execSelectAndPrint(
//                "http://localhost:3030/movies",query
//                );
//
//        execSelectAndProcess(
//                "http://localhost:3030/movies",
//                query);
//    }
    public String createListActors() {
        String actorsList = "";
        String query = PREFIX + "SELECT ?actor WHERE { \n" +
                "  \n" +
                "  ?uri vcard:FN ?actor . \n" +
                "  FILTER regex(str(?uri), 'actor','i')\n" +
                "\n" +
                "}";
        try {

            QueryExecution q = QueryExecutionFactory.sparqlService(localhostURI,
                    query);
            ResultSet results = q.execSelect();

            //ResultSetFormatter.out(System.out, results);
            //result = results.toString();
            while (results.hasNext()) {
                QuerySolution soln = results.nextSolution();
                RDFNode x = soln.get("actor");
                actorsList = actorsList+"<li><a href='result.jsp?value="+x+"&attribute=actor'>"+x+"</a></li>";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //System.out.println(actorsList);
    return actorsList;
}

    public String createListDirectors() {
        String directorList = "";
        String query = PREFIX + "SELECT ?director WHERE { \n" +
                "  \n" +
                "  ?uri vcard:FN ?director . \n" +
                "  FILTER regex(str(?uri), 'director','i')\n" +
                "\n" +
                "}";
        try {

            QueryExecution q = QueryExecutionFactory.sparqlService(localhostURI,
                    query);
            ResultSet results = q.execSelect();

            //ResultSetFormatter.out(System.out, results);
            //result = results.toString();
            while (results.hasNext()) {
                QuerySolution soln = results.nextSolution();
                RDFNode x = soln.get("director");
                directorList = directorList+"<li><a href='result.jsp?value="+x+"&attribute=director'>"+x+"</a></li>";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //System.out.println(directorList);
        return directorList;
    }

    public String createListCountry() {
        String countryList = "";
        ArrayList<String> countries = new ArrayList<String>();
        String query = PREFIX + "SELECT ?country WHERE { \n" +
                "  \n" +
                "  ?uri movie:country ?country . \n" +
                "}";
        try {

            QueryExecution q = QueryExecutionFactory.sparqlService(localhostURI,
                    query);
            ResultSet results = q.execSelect();

            //ResultSetFormatter.out(System.out, results);
            //result = results.toString();
            while (results.hasNext()) {
                QuerySolution soln = results.nextSolution();
                RDFNode x = soln.get("country");
                String countrySplit[] = x.toString().split(", ");
                for(int k=0; k<countrySplit.length; k++){
                    if(!countries.contains(countrySplit[k])){
                        countries.add(countrySplit[k]);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //System.out.println(directorList);
        return finalList(countries, "country");
    }

    public String createListYear() {
        String yearList = "";
        String query = PREFIX + "SELECT DISTINCT ?year WHERE { \n" +
                "  \n" +
                "  ?uri movie:releaseYear ?year . \n" +
                "}";
        try {

            QueryExecution q = QueryExecutionFactory.sparqlService(localhostURI,
                    query);
            ResultSet results = q.execSelect();

            //ResultSetFormatter.out(System.out, results);
            //result = results.toString();
            while (results.hasNext()) {
                QuerySolution soln = results.nextSolution();
                RDFNode x = soln.get("year");
                yearList = yearList+"<li><a href='result.jsp?value="+x+"&attribute=year'>"+x+"</a></li>";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //System.out.println(directorList);
        return yearList;
    }

    public String createListLanguage() {
        String languageList = "";
        ArrayList<String> languages = new ArrayList<String>();
        String query = PREFIX + "SELECT ?language WHERE { \n" +
                "  \n" +
                "  ?uri movie:language ?language . \n" +
                "}";
        try {

            QueryExecution q = QueryExecutionFactory.sparqlService(localhostURI,
                    query);
            ResultSet results = q.execSelect();

            //ResultSetFormatter.out(System.out, results);
            //result = results.toString();
            while (results.hasNext()) {
                QuerySolution soln = results.nextSolution();
                RDFNode x = soln.get("language");
                //System.out.println(x);
                String languageSplit[] = x.toString().split(", ");
                for(int k=0; k<languageSplit.length; k++){
                    if(!languages.contains(languageSplit[k])){
                        languages.add(languageSplit[k]);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //System.out.println(directorList);
        return finalList(languages, "language");
    }

    public String createListGenre() {
        String countryList = "";
        ArrayList<String> genres = new ArrayList<String>();
        String query = PREFIX + "SELECT ?genre WHERE { \n" +
                "  \n" +
                "  ?uri movie:genre ?genre . \n" +
                "}";
        try {

            QueryExecution q = QueryExecutionFactory.sparqlService(localhostURI,
                    query);
            ResultSet results = q.execSelect();

            //ResultSetFormatter.out(System.out, results);
            //result = results.toString();
            while (results.hasNext()) {
                QuerySolution soln = results.nextSolution();
                RDFNode x = soln.get("genre");
                String genreSplit[] = x.toString().split(", ");
                for(int k=0; k<genreSplit.length; k++){
                    if(!genres.contains(genreSplit[k])){
                        genres.add(genreSplit[k]);
                    }
                }
                //countryList = countryList+"<li><a>"+x+"</a></li>";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //System.out.println(directorList);
        return finalList(genres,"genre");
    }

    public String finalList(ArrayList<String> tmpList, String attribute){
        String finalList = "";
        for (int i=0; i<tmpList.size(); i++) {
            finalList = finalList+"<li><a href='result.jsp?value="+tmpList.get(i)+"&attribute="+attribute+"'>"+tmpList.get(i)+"</a></li>";
        }
        return finalList;
    }

    public void runQuery(String value) {
        System.out.println(value);
    }




}


