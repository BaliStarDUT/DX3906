package lol.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lol.dao.HerosRepository;
import lol.entity.Lolhero;

@Service
public class HerosServiceImpl implements HerosService {
	private HerosRepository herosRepository;
	@Autowired
	public HerosServiceImpl(HerosRepository herosRepository) {
		super();
		this.herosRepository = herosRepository;
	}

	@Override
	public Collection<Lolhero> findHeros() {
		return herosRepository.findAll();
	}

	@Override
	public void saveHero(Lolhero hero) {
		herosRepository.save(hero);
	}

}
