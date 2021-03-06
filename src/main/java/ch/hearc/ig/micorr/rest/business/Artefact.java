package ch.hearc.ig.micorr.rest.business;

import java.util.List;

/**
 * Classe contenant les propriétés d'un artefact.
 * 
 * @author Jérôme Schneider
 *
 */

public class Artefact {
	
	/**
	 * Id de l'artefact dans l'ontologie
	 */
	private Integer id;
	
	/**
	 * Nom complet de l'artefact (name + id)
	 */
	private String artefact;
	
	/**
	 * Nom de l'artefact
	 */
	private String name;
	
	/**
	 * Type de l'artefact
	 */
	private String type;
	
	/**
	 * Liste des parents d'un artefact
	 */
	private List<String> parentsData;
	
	/**
	 * Liste des enfants d'un artefact
	 */
	private List<String> childrenData;
	
	/**
	 * Liste des frères/soeurs d'un artefact
	 */
	private List<String> sistersData;
	
	/**
	 * Liste des assertions d'un artefact
	 */
	private List<String> assertions;
	
	public Artefact() {
		
	}

	public Artefact(Integer id, String artefact, String name, String type) {
		this.id = id;
		this.artefact = artefact;
		this.name = name;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getArtefact() {
		return artefact;
	}

	public void setArtefact(String artefact) {
		this.artefact = artefact;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getParentsData() {
		return parentsData;
	}

	public void setParentsData(List<String> parentsData) {
		this.parentsData = parentsData;
	}

	public List<String> getChildrenData() {
		return childrenData;
	}

	public void setChildrenData(List<String> childrenData) {
		this.childrenData = childrenData;
	}

	public List<String> getSistersData() {
		return sistersData;
	}

	public void setSistersData(List<String> sistersData) {
		this.sistersData = sistersData;
	}

	public List<String> getAssertions() {
		return assertions;
	}

	public void setAssertions(List<String> assertions) {
		this.assertions = assertions;
	}

	@Override
	public String toString() {
		return "Artefact [id=" + id + ", name=" + name + ", type=" + type + ", parentsData=" + parentsData
				+ ", sistersData=" + sistersData + ", assertions=" + assertions + "]";
	}

}
