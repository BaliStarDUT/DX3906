package lol.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lol.dao.HerosRepository;
import lol.entity.Lolhero;
/**
 * Mostly used as a facade for all Heros controllers
 * Also a placeholder for @Transactional and @Cacheable annotations
 * @author root
 */
@Service
public class HerosServiceImpl implements HerosService {
	
	private HerosRepository herosRepository;
	
	@Autowired
	public HerosServiceImpl(HerosRepository herosRepository) {
		this.herosRepository = herosRepository;
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(value = "heros")
	public Collection<Lolhero> findHeros() {
		return herosRepository.findAll();
	}

	@Override
	@Transactional
	public void saveHero(Lolhero hero) {
		herosRepository.save(hero);
	}

}
