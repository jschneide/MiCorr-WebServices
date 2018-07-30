package ch.hearc.ig.micorr.rest.business;


public class Artefacts {
	
	private Artefact text;
	
	private Artefact country;
	
	private Artefact metal;
	
	private Artefact corrosion;
	
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
