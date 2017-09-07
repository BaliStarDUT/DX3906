package top.hunaner.lol.service;


import top.hunaner.lol.entity.Lolhero;

import java.util.Collection;

public interface HerosService {
	Collection<Lolhero> getAll();
	Lolhero saveHero(Lolhero hero);
}
