package ch.hearc.ig.micorr.rest.business;

public class Artefact {
	
	private Integer id;
	
	private String name;
	
	private String type;
	
	private String familyLink;
	
	public Artefact() {
		
	}

	public Artefact(Integer id, String name, String type, String familyLink) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.familyLink = familyLink;
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

	public String getFamilyLink() {
		return familyLink;
	}

	public void setFamilyLink(String familyLink) {
		this.familyLink = familyLink;
	}

	@Override
	public String toString() {
		return "Artefact [id=" + id + ", name=" + name + ", type=" + type + ", familyLink=" + familyLink + "]";
	}
	
}
