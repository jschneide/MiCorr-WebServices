package ch.hearc.ig.micorr.webservices;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.hearc.ig.micorr.rest.business.Artefact;
import ch.hearc.ig.micorr.rest.business.Artefacts;


@Path("/artifacts")
public class JerseyService {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Artefacts getMiCorrArtefactList() {
		Artefacts list = new Artefacts();
		
		list.setArtefactList(new ArrayList<Artefact>());
		
		list.getArtefactList().add(new Artefact(1, "Test", "Test1", "Test2"));
		
		return list;
		
	}

}
