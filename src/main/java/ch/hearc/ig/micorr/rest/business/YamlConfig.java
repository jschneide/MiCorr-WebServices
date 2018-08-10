package ch.hearc.ig.micorr.rest.business;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe contenant les différents paramètres de configuration
 * de l'application présent dans un fichier YAML.
 * 
 * @author jerome.schneide
 *
 */
public class YamlConfig {
	
	/**
	 * Adresse du serveur Apache JENA Fuseki dans Docker
	 */
	@JsonProperty("fuseki-address")
	private String fusekiAddress;
	
	/**
	 * Durée du timeout pour une requête SPARQL
	 */
	@JsonProperty("timeout-query")
	private String timeoutQuery;
	
	/**
	 * Liste de requêtes SPARQL
	 */
	private Map<String, String> queries;

	public YamlConfig() {
		super();
	}

	public String getFusekiAddress() {
		return fusekiAddress;
	}

	public void setFusekiAddress(String fusekiAddress) {
		this.fusekiAddress = fusekiAddress;
	}

	public String getTimeoutQuery() {
		return timeoutQuery;
	}

	public void setTimeoutQuery(String timeoutQuery) {
		this.timeoutQuery = timeoutQuery;
	}

	public Map<String, String> getQueries() {
		return queries;
	}

	public void setQueries(Map<String, String> queries) {
		this.queries = queries;
	}

	@Override
	public String toString() {
		return "YamlConfig [fusekiAddress=" + fusekiAddress + ", timeoutQuery=" + timeoutQuery + ", queries=" + queries
				+ "]";
	}
	
}
