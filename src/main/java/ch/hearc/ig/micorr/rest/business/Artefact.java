package ch.hearc.ig.micorr.rest.business;

import java.util.List;

public class Artefact {
	
	private Integer id;
	
	private String name;
	
	private String type;
	
	private List<String> parentsData;
	
	private List<String> sistersData;
	
	public Artefact() {
		
	}

	public Artefact(Integer id, String name, String type, List<String> parentsData, List<String> sistersData) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.parentsData = parentsData;
		this.sistersData = sistersData;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<String> getSistersData() {
		return sistersData;
	}

	public void setSistersData(List<String> sistersData) {
		this.sistersData = sistersData;
	}

	@Override
	public String toString() {
		return "Artefact [id=" + id + ", name=" + name + ", type=" + type + ", parentsData=" + parentsData
				+ ", sistersData=" + sistersData + "]";
	}

}
