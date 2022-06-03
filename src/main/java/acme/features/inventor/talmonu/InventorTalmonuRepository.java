package acme.features.inventor.talmonu;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Configuration;
import acme.entities.items.Item;
import acme.entities.talmonus.Talmonu;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorTalmonuRepository extends AbstractRepository {
	
	@Query("select x from Talmonu x where x.item.inventor.userAccount.username = :username")
	Collection<Talmonu> findMineTalmonu(String username);
	
	@Query("select x from Talmonu x where x.id = :id")
	Talmonu findOneTalmonu(int id);
	
	@Query("select x from Talmonu x where x.code = :code")
	Talmonu findTalmonuByCode(String code);
	
	@Query("select c from Configuration c")
	Configuration findConfiguration();

	@Query("select i from Item i where i.code = :code")
	Item findOneItemByCode(String code);
	
	@Query("select i.code from Item i where i.published = 1 and i.itemType = 0 and i.inventor.userAccount.username = :username")
	List<String> findAllPossibleItemCodes(String username);
	
	@Query("select x.item.code from Talmonu x where x.item.inventor.userAccount.username = :username")
	List<String> findAllTakenItemCodes(String username);
}
