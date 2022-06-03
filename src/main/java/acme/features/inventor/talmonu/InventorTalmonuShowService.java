package acme.features.inventor.talmonu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.talmonus.Talmonu;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorTalmonuShowService implements AbstractShowService<Inventor, Talmonu>{
	
	@Autowired
	protected InventorTalmonuRepository repository;
	 
	@Override
	public boolean authorise(final Request<Talmonu> request) {
		assert request != null;

		boolean result;
		result = request.getPrincipal().hasRole(Inventor.class);
		
		return result;
	}

	@Override
	public Talmonu findOne(final Request<Talmonu> request) {
		assert request != null;
		
		Talmonu result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneTalmonu(id);
		
		return result;
	}

	@Override
	public void unbind(final Request<Talmonu> request, final Talmonu entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "creationMoment", "theme", "explanation", "startPeriod", "endPeriod", "expenditure", "furtherInfo");
		
		final String item = entity.getItem().getCode();
		model.setAttribute("item", item);
		
	}
	
}
