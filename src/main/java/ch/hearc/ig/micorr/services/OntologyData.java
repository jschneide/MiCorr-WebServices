package ch.hearc.ig.micorr.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdfconnection.RDFConnectionFuseki;
import org.apache.jena.rdfconnection.RDFConnectionRemoteBuilder;

import ch.hearc.ig.micorr.rest.business.Artefact;

public class OntologyData {
	
	private static final String FILENAME = "C:\\DEV\\SPARQL\\pizza_q1.txt";

    public List<Artefact> getResearchProperties () {

        String sparqlRequest = readFileToString(FILENAME);

        RDFConnectionRemoteBuilder builder = RDFConnectionFuseki.create()
            .destination("http://localhost:8080/PizzaDS/")
            .gspEndpoint("PizzaGraph");

        System.out.println(sparqlRequest);

        Query query = QueryFactory.create(sparqlRequest);
        
        List<QuerySolution> list = null; 

        // In this variation, a connection is built each time.
        try ( RDFConnectionFuseki conn = (RDFConnectionFuseki)builder.build() ) {
            conn.queryResultSet(query, ResultSetFormatter::out);
            list = ResultSetFormatter.toList(conn.query(query).execSelect());
        }
        
        return null;

    }

    public static String readFileToString(String filename) {

        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                sb.append(sCurrentLine);
                sb.append(" ");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

}
