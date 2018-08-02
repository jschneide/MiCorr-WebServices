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

/**
 * Classe contenant les méthodes d'exécutions des requêtes SPARQL
 * sur l'ontologie.
 * Les différents paramètres ainsi que les requêtes sont contenus 
 * dans un fichier YAML.
 * 
 * @author Jérôme Schneider
 *
 */
public class OntologyQuery {

	/**
	 * Paramètres de configuration de l'application
	 */
	private static final String YAML_FILE = "/usr/local/micorr/config.yaml";
	private YamlConfig config;

	public OntologyQuery() {
		getYamlConfigData();
	}

	/**
	 * Méthode de recherche des propriétes d'un texte dans l'ontologie
	 * MiCorr.
	 * 
	 * @param text le texte à rechercher dans l'ontologie
	 * @return QuerySolution contenant le résultat de la recherche 
	 */
	public List<QuerySolution> getPropertiesDataQuery(String text) {
		String sparqlRequest = this.config.getQueries().get("query1");

		sparqlRequest = sparqlRequest.replaceAll("%text%", text);

		Query query = QueryFactory.create(sparqlRequest);

		return queryExec(query);
	}
	
	/**
	 * Méthode de recherche des parents d'un texte dans l'ontologie
	 * MiCorr.
	 * 
	 * @param text le texte à rechercher dans l'ontologie
	 * @return QuerySolution contenant le résultat de la recherche 
	 */
	public List<QuerySolution> getParentsDataQuery(String text) {
		String sparqlRequest = this.config.getQueries().get("query2");

		sparqlRequest = sparqlRequest.replaceAll("%text%", text);

		Query query = QueryFactory.create(sparqlRequest);
		
		return queryExec(query);
	}
	
	/**
	 * Méthode de recherche des assertions d'un texte dans l'ontologie
	 * MiCorr.
	 * 
	 * @param text le texte à rechercher dans l'ontologie
	 * @return QuerySolution contenant le résultat de la recherche 
	 */
	public List<QuerySolution> getPropertyAssertionsDataQuery(String text) {
		String sparqlRequest = this.config.getQueries().get("query3");

		sparqlRequest = sparqlRequest.replaceAll("%text%", text);

		Query query = QueryFactory.create(sparqlRequest);
		
		return queryExec(query);
	}

	/**
	 * Méthode permettant l'exécution d'une requête sur le serveur Apache
	 * Fuseki. Le nom du serveur ainsi que la durée du timeout pour la recherche
	 * est fourni en paramètres dans le fichier YAML.
	 *  
	 * @param query la requête SPARQL à exécuter sur le serveur
	 * @return QuerySolution contenant le résultat de la recherche
	 */
	private List<QuerySolution> queryExec(Query query) {
		List<QuerySolution> list = null;
		
		try (QueryExecution qexec = QueryExecutionFactory.sparqlService(this.config.getFusekiAddress(), query)) {
			// Set the DBpedia specific timeout.
			((QueryEngineHTTP) qexec).addParam("timeout", this.config.getTimeoutQuery());

			// Execute.
			ResultSet rs = qexec.execSelect();

			list = ResultSetFormatter.toList(rs);

			qexec.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	/**
	 * Méthode de lecture du fichier YAML contenu sur le serveur Tomcat. Le fichier 
	 * contient les paramètres du serveur Fuseki ainsi que les différentes requêtes
	 * nécessaires à l'interrogation des données.
	 */
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
