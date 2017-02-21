package lol.service;

import java.util.Collection;

import lol.entity.Lolhero;

public interface HerosService {
	Collection<Lolhero> findHeros();
	void saveHero(Lolhero hero);
}
