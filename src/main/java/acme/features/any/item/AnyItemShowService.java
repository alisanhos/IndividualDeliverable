package acme.features.any.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyItemShowService implements AbstractShowService<Any, Item>{
	
	//Internal State
	
	@Autowired
	protected AnyItemRepository repository;
		
	//AbstractShowService<Any, Item> interface
	
	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		
		boolean result;
		int itemId;
		Item item;
		
		itemId = request.getModel().getInteger("id");
		item = this.repository.findOneItemById(itemId);
		result = item.isPublished();
		
		return result; 
	}
	
	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;
		
		Item result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneItemById(id);
		
		return result;
	}
	
	@Override
	public void unbind (final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "name", "itemType", "code", "technology", "description", "retailPrice", "optionalLink");

		final String fullname = entity.getInventor().getUserAccount().getIdentity().getFullName();
		model.setAttribute("fullname", fullname);
	}

}
