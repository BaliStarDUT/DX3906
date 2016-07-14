package lol.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import lol.entity.Lolhero;

public interface HerosRepository{
	Collection<Lolhero> findByType(String type);
	Lolhero findById(int id);
	Collection<Lolhero> findAll();
	void save(Lolhero hero);
}
//@RepositoryRestResource(collectionResourceRel="heros",path="heros")
//public interface HerosRepository extends PagingAndSortingRepository<Lolhero,Integer>{
//	List<Lolhero> findByType(@Param("type") String type);
//	Collection<Lolhero> findAll();
//	<S> S save(Lolhero hero);
//}

