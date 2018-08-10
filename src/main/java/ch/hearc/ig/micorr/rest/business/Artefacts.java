package ch.hearc.ig.micorr.rest.business;

/**
 * Classe métier contenant tous les artefacts existants pour une 
 * recherche d'informations via les webservices
 * 
 * @author Jérôme Schneider
 *
 */
public class Artefacts {
	
	/**
	 * Un texte libre recherché
	 */
	private Artefact text;
	
	/**
	 * Un pays recherché
	 */
	private Artefact country;
	
	/**
	 * Un métal recheché
	 */
	private Artefact metal;
	
	/**
	 * Un type de corrosion recherché
	 */
	private Artefact corrosion;
	
	/**
	 * Un environnement recherché
	 */
	private Artefact environment;

	public Artefacts() {
		
	}

	public Artefact getText() {
		return text;
	}

	public void setText(Artefact text) {
		this.text = text;
	}

	public Artefact getCountry() {
		return country;
	}

	public void setCountry(Artefact country) {
		this.country = country;
	}

	public Artefact getMetal() {
		return metal;
	}

	public void setMetal(Artefact metal) {
		this.metal = metal;
	}

	public Artefact getCorrosion() {
		return corrosion;
	}

	public void setCorrosion(Artefact corrosion) {
		this.corrosion = corrosion;
	}

	public Artefact getEnvironment() {
		return environment;
	}

	public void setEnvironment(Artefact environment) {
		this.environment = environment;
	}
	
}
