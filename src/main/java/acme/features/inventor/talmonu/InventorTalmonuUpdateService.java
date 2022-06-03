package acme.features.inventor.talmonu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Configuration;
import acme.entities.talmonus.Talmonu;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;
import main.spamDetector;

@Service
public class InventorTalmonuUpdateService implements AbstractUpdateService<Inventor, Talmonu> {
	
	// Internal State 
	
	@Autowired
	protected InventorTalmonuRepository repository;
		
	//AbstractUpdateService<Inventor, Talmonu> interface
	
	@Override
	public boolean authorise(final Request<Talmonu> request) {
		assert request != null;
		final java.util.Collection<Talmonu> mines = this.repository.findMineTalmonu(request.getPrincipal().getUsername());
		final Talmonu talmonu = this.repository.findOneTalmonu(request.getModel().getInteger("id"));
		
		final boolean result;
		result = request.getPrincipal().hasRole(Inventor.class)&&mines.contains(talmonu);
		return result;
	}
	
	@Override
	public void bind(final Request<Talmonu> request, final Talmonu entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationMoment", "theme", "explanation", "startPeriod", "endPeriod", "endPeriod", "expenditure", "furtherInfo");

	}
	
	@Override
	public void unbind(final Request<Talmonu> request, final Talmonu entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,"code",  "creationMoment", "theme", "explanation", "startPeriod", "endPeriod", "endPeriod", "expenditure", "furtherInfo");
		final String item = entity.getItem().getCode();
		model.setAttribute("item", item);
		
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
	public void validate(final Request<Talmonu> request, final Talmonu entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("theme")) {
        	final Configuration configuration = this.repository.findConfiguration();
        	final String[] sp = configuration.getWeakSpamTerms().split(",");
        	final List<String> softSpam = new ArrayList<String>(Arrays.asList(sp));
        	final Double softThreshold = configuration.getWeakSpamThreshold();
        	final String[] hp = configuration.getStrongSpamTerms().split(",");
        	final List<String> hardSpam = new ArrayList<String>(Arrays.asList(hp));
        	final Double hardThreshold = configuration.getStrongSpamThreshold();
        	errors.state(request, !spamDetector.isSpam(entity.getTheme(), softSpam, softThreshold, hardSpam, hardThreshold), "theme", "inventor.talmonu.form.error.spam");
        }
		
		if(!errors.hasErrors("explanation")) {
        	final Configuration configuration = this.repository.findConfiguration();
        	final String[] sp = configuration.getWeakSpamTerms().split(",");
        	final List<String> softSpam = new ArrayList<String>(Arrays.asList(sp));
        	final Double softThreshold = configuration.getWeakSpamThreshold();
        	final String[] hp = configuration.getStrongSpamTerms().split(",");
        	final List<String> hardSpam = new ArrayList<String>(Arrays.asList(hp));
        	final Double hardThreshold = configuration.getStrongSpamThreshold();
        	errors.state(request, !spamDetector.isSpam(entity.getExplanation(), softSpam, softThreshold, hardSpam, hardThreshold), "explanation", "inventor.talmonu.form.error.spam");
        }
		
		
		if(!errors.hasErrors("startPeriod")) {
        	final Date startPeriod = entity.getStartPeriod();
        	final Calendar calendar = Calendar.getInstance();
        	calendar.setTime(entity.getCreationMoment()); // Aquí no tendremos en cuenta la fecha de actualización, sino de creación
        	calendar.add(Calendar.MONTH, 1);
        	calendar.add(Calendar.SECOND, -1); // Un mes menos un segundo
        	errors.state(request, startPeriod.after(calendar.getTime()), "startPeriod", "inventor.talmonu.form.error.start-period-not-enough");
        }
		
		if(!errors.hasErrors("endPeriod") && entity.getStartPeriod()!=null) {
        	final Date startPeriod = entity.getStartPeriod();
        	final Date endPeriod = entity.getEndPeriod();
        	final Date moment = new Date(startPeriod.getTime() + 604799999); 
        	errors.state(request, endPeriod.after(moment), "endPeriod", "inventor.talmonu.form.error.end-period-one-week-before-start-period");
        }
		
		if(!errors.hasErrors("expenditure")) {
            final String acceptedCurrencies = this.repository.findConfiguration().getAcceptedCurrencies();
            final String[] currencies = acceptedCurrencies.split(",");
            boolean isCorrect = false;
            final String c = entity.getExpenditure().getCurrency();
            for (final String currency : currencies) {
                if (c.equals(currency)) {
                    isCorrect = true;
                }
            }
            errors.state(request, isCorrect, "expenditure", "inventor.talmonu.form.error.incorrect-currency");
        }
        
        if(!errors.hasErrors("expenditure")) {
            errors.state(request, entity.getExpenditure().getAmount() >= 0.0, "expenditure", "inventor.talmonu.form.error.negative-budget");
        }
		
	}
		
	@Override
	public void update(final Request<Talmonu> request, final Talmonu entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
