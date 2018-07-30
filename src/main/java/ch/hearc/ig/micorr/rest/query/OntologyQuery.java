package ch.hearc.ig.micorr.rest.query;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;

public class OntologyQuery {

	private static final String FILENAME1 = "C:\\DEV\\SPARQL\\micorr_query1.txt";
	private static final String FILENAME2 = "C:\\DEV\\SPARQL\\micorr_query2.txt";

	public OntologyQuery() {
	}

	public List<QuerySolution> getPropertiesDataQuery(String text) {
		String sparqlRequest = readFileToString(FILENAME1);

		sparqlRequest = sparqlRequest.replaceAll("%text%", text);

		Query query = QueryFactory.create(sparqlRequest);

		return queryExec(query);
	}

	private static List<QuerySolution> queryExec(Query query) {
		List<QuerySolution> list = null;
		
		// TODO Mettre l'adresse du serveur dans le fichier de configuration YAML avec
		// les requêtes
		try (QueryExecution qexec = QueryExecutionFactory.sparqlService("http://localhost:8080/MiCorrDS/", query)) {
			// Set the DBpedia specific timeout.
			((QueryEngineHTTP) qexec).addParam("timeout", "10000");

			// Execute.
			ResultSet rs = qexec.execSelect();
			// ResultSet rsOut = qexec.execSelect();

			list = ResultSetFormatter.toList(rs);
			// ResultSetFormatter.out(System.out, rsOut, query);

			qexec.close();
		} catch (Exception e) {
			e.printStackTrace();
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
