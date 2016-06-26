package lol.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import lol.entity.Lolhero;

@RepositoryRestResource(collectionResourceRel="heros",path="heros")
public interface HerosRepository extends PagingAndSortingRepository<Lolhero,Integer>{
	List<Lolhero> findByType(@Param("type") String type);
}
