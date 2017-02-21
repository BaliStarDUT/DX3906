package lol.dao;

import java.util.Collection;

import lol.entity.Lolhero;

/**
 * Repository class for <code>Heros</code> domain objects All method names are compliant with Spring Data naming
 * conventions so this interface can easily be extended for Spring Data See here: http://static.springsource.org/spring-data/jpa/docs/current/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
 * @author root
 *
 */
public interface HerosRepository{
	/**
	 * 
	 * @param type
	 * @return
	 */
	Collection<Lolhero> findByType(String type);
	Lolhero findById(int id);
	/**
	 * Retrieve all <code>Hero</code>s from the data store.
     * @return a <code>Collection</code> of <code>Hero</code>s
	 */
	Collection<Lolhero> findAll();
	/**
     * Save an <code>Hero</code> to the data store, either inserting or updating it.
	 * @param hero
	 */
	void save(Lolhero hero);
}
//@RepositoryRestResource(collectionResourceRel="heros",path="heros")
//public interface HerosRepository extends PagingAndSortingRepository<Lolhero,Integer>{
//	List<Lolhero> findByType(@Param("type") String type);
//	Collection<Lolhero> findAll();
//	<S> S save(Lolhero hero);
//}

