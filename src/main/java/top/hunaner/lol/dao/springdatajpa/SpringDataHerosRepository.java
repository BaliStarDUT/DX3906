package top.hunaner.lol.dao.springdatajpa;

import org.springframework.data.repository.Repository;
import top.hunaner.lol.dao.HerosRepository;
import top.hunaner.lol.entity.Lolhero;


/**
 * 
 * @author root
 *
 */
public interface SpringDataHerosRepository extends HerosRepository, Repository<Lolhero, Integer> {

}
