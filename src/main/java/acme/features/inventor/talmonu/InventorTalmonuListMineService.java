package acme.features.inventor.talmonu;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.talmonus.Talmonu;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorTalmonuListMineService implements AbstractListService<Inventor, Talmonu>{
	
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
	public Collection<Talmonu> findMany(final Request<Talmonu> request) {
		assert request != null;
		
		Collection<Talmonu> result;
		Principal principal;

		principal = request.getPrincipal(); 
        result = this.repository.findMineTalmonu(principal.getUsername());
		return result;
	}

	@Override
	public void unbind(final Request<Talmonu> request, final Talmonu entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "theme");
		
		final String item = entity.getItem().getCode();
		model.setAttribute("item", item);
		
	}
	
}
