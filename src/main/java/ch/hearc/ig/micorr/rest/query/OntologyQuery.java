package ch.hearc.ig.micorr.rest.query;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import ch.hearc.ig.micorr.rest.business.YamlConfig;

public class OntologyQuery {

	//private static final String YAML_FILE = "C:\\DEV\\SPARQL\\config.yaml";
	private static final String YAML_FILE = "/usr/local/micorr/config.yaml";
	
	private YamlConfig config;

	public OntologyQuery() {
		getYamlConfigData();
	}

	public List<QuerySolution> getPropertiesDataQuery(String text) {
		String sparqlRequest = this.config.getQueries().get("query1");

		sparqlRequest = sparqlRequest.replaceAll("%text%", text);

		Query query = QueryFactory.create(sparqlRequest);

		return queryExec(query);
	}
	
	public List<QuerySolution> getParentsDataQuery(String text) {
		String sparqlRequest = this.config.getQueries().get("query2");

		sparqlRequest = sparqlRequest.replaceAll("%text%", text);

		Query query = QueryFactory.create(sparqlRequest);
		
		return queryExec(query);
	}
	
	public List<QuerySolution> getPropertyAssertionsDataQuery(String text) {
		String sparqlRequest = this.config.getQueries().get("query3");

		sparqlRequest = sparqlRequest.replaceAll("%text%", text);

		Query query = QueryFactory.create(sparqlRequest);
		
		return queryExec(query);
	}

	private List<QuerySolution> queryExec(Query query) {
		List<QuerySolution> list = null;
		
		try (QueryExecution qexec = QueryExecutionFactory.sparqlService(this.config.getFusekiAddress(), query)) {
			// Set the DBpedia specific timeout.
			((QueryEngineHTTP) qexec).addParam("timeout", this.config.getTimeoutQuery());

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

	private void getYamlConfigData() {
		
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		
		try {
            this.config = mapper.readValue(new File(YAML_FILE), YamlConfig.class);
            System.out.println(ReflectionToStringBuilder.toString(config,ToStringStyle.MULTI_LINE_STYLE));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
}
