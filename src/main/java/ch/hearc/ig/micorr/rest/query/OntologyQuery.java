package ch.hearc.ig.micorr.rest.query;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdfconnection.RDFConnectionFuseki;
import org.apache.jena.rdfconnection.RDFConnectionRemoteBuilder;

public class OntologyQuery {

	private static final String FILENAME1 = "C:\\DEV\\SPARQL\\micorr_query1.txt";
	
	private static final boolean CONSOLE_LOG = true;

	

	private RDFConnectionRemoteBuilder builder;
	
	
	public OntologyQuery() {
    	builder = RDFConnectionFuseki.create()
                .destination("http://localhost:8080/MiCorrDS/")
                .gspEndpoint("MiCorrGraph");
	}

	public List<QuerySolution> getPropertiesDataQuery(String text) {
		String sparqlRequest = readFileToString(FILENAME1);
		
		sparqlRequest.replace("%text", text);

		System.out.println(sparqlRequest);

		Query query = QueryFactory.create(sparqlRequest);

		List<QuerySolution> list = null;

		// In this variation, a connection is built each time.
		try (RDFConnectionFuseki conn = (RDFConnectionFuseki) builder.build()) {
			if(CONSOLE_LOG) {
				conn.queryResultSet(query, ResultSetFormatter::out);
			}
			list = ResultSetFormatter.toList(conn.query(query).execSelect());
		}

		return list;
	}

	private static String readFileToString(String filename) {

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
