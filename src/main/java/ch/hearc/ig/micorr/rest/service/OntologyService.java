package ch.hearc.ig.micorr.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.QuerySolution;

import ch.hearc.ig.micorr.rest.business.Artefact;
import ch.hearc.ig.micorr.rest.query.OntologyQuery;


public class OntologyService {

	private List<QuerySolution> querySolutionList;

	private OntologyQuery query;

	public OntologyService() {
		query = new OntologyQuery();
	}

	public Artefact getProperties(String search) {

		querySolutionList = new ArrayList<>();

		// - Recherche si la donnée saisie existe dans l'ontologie
		querySolutionList = query.getPropertiesDataQuery(search);
		
		Artefact artefact = null;

		if (!querySolutionList.isEmpty()) {
			QuerySolution data = (QuerySolution) querySolutionList.get(0);
			
			artefact = new Artefact(data.getLiteral("?artefactId").getInt(), data.getLiteral("?artefactName").getString(), data.getResource("?artefactType").getLocalName());
		} else {
			artefact = new Artefact(0, search, "");
		}
		
		return artefact;
	}
	
	public Artefact getParents(Artefact artefact) {
		
		querySolutionList = new ArrayList<>();

		// - Recherche les soeurs du type de la données
		querySolutionList = query.getParentsDataQuery(artefact.getType());
		
		if (!querySolutionList.isEmpty()) {
			List<String> resSisters = new ArrayList<>();
			List<String> resParents = new ArrayList<>();
			
			for(int i = 0; i < querySolutionList.size(); i++) {
				resSisters.add(querySolutionList.get(i).getLiteral("?labelSister").getString());
				
				if(!resParents.contains(querySolutionList.get(i).getLiteral("?labelParent").getString())) {
					resParents.add(querySolutionList.get(i).getLiteral("?labelParent").getString());
				}
			}
			
			artefact.setSistersData(resSisters);
			artefact.setParentsData(resParents);
		}
		
		return artefact;
	}
		
}
