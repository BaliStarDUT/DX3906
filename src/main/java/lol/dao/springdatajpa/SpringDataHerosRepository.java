package lol.dao.springdatajpa;

import org.springframework.data.repository.Repository;

import lol.dao.HerosRepository;
import lol.entity.Lolhero;

public interface SpringDataHerosRepository extends HerosRepository, Repository<Lolhero, Integer> {

}
