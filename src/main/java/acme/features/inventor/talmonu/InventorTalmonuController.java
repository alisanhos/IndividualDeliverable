package acme.features.inventor.talmonu;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.talmonus.Talmonu;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorTalmonuController extends AbstractController<Inventor, Talmonu>{
	
	  @Autowired
	  protected InventorTalmonuListMineService    listMineService;
	  
	  @Autowired
	  protected InventorTalmonuShowService    showService;
	  
	  @Autowired
	  protected InventorTalmonuCreateService createService;
	  
	  @Autowired
	  protected InventorTalmonuUpdateService updateService;
	  
	  @Autowired
	  protected InventorTalmonuDeleteService deleteService;
	  
	  @PostConstruct
	  protected void initialise() {
	        super.addCommand("list-mine", "list", this.listMineService);
	        super.addCommand("show", this.showService);
	        super.addCommand("create", this.createService);
	        super.addCommand("update", this.updateService);
	        super.addCommand("delete", this.deleteService);
	    }

}
