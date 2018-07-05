package ch.hearc.ig.micorr.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.QuerySolution;

import ch.hearc.ig.micorr.rest.business.Artefact;
import ch.hearc.ig.micorr.rest.query.OntologyQuery;

public class OntologyService {
	
	private List<Artefact> artefactList;
	
	private List<QuerySolution> querySolutionList;
	
	private OntologyQuery query;
	
	public OntologyService() {
		query = new OntologyQuery();
		artefactList = new ArrayList<>();
	}

	public List<Artefact> getResearchProperties (String text) {

		querySolutionList = new ArrayList<>();
		
		querySolutionList = query.getPropertiesDataQuery(text);
		
		convertQuerySolutionToArtefact(querySolutionList);
		
		return artefactList;
    }
	
	private void convertQuerySolutionToArtefact(List<QuerySolution> list){
		Artefact artefact = null;
		
		for(QuerySolution q: list) {
			System.out.println(q.getLiteral("?artefactId").getInt());
			System.out.println(q.getLiteral("?artefactName").getString());
			System.out.println(q.getResource("?artefactType").getNameSpace());
			System.out.println(q.getResource("?artefactType").getLocalName());
			System.out.println(q.getResource("?artefactType").getURI());
			artefact = new Artefact(q.getLiteral("?artefactId").getInt(), q.getLiteral("?artefactName").getString(), q.getResource("?artefactType").getLocalName(), "");
			this.artefactList.add(artefact);
		}
		
	}

    

}
