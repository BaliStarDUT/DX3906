package top.hunaner.lol.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.hunaner.lol.dao.HerosRepository;
import top.hunaner.lol.entity.Lolhero;


/**
 * Mostly used as a facade for all Heros controllers
 * Also a placeholder for @Transactional and @Cacheable annotations
 * @author root
 */
@Service
public class HerosServiceImpl implements HerosService {
	private static final Logger log = LoggerFactory.getLogger(HerosServiceImpl.class);

	private HerosRepository herosRepository;
	
	@Autowired
	public HerosServiceImpl(HerosRepository herosRepository) {
		this.herosRepository = herosRepository;
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(value = "heros")
	public Collection<Lolhero> getAll() {
		Collection<Lolhero> lolheroes = herosRepository.findAll();
		log.debug("getAllHeros success:"+lolheroes.size());
		return lolheroes;
	}

	@Override
	@Transactional
	public Lolhero saveHero(Lolhero hero) {
		Lolhero lolhero = herosRepository.save(hero);
		log.debug("saveHero success:"+lolhero.toString());
		return lolhero;
	}

}
