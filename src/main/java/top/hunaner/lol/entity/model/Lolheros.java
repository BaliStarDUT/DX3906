package top.hunaner.lol.entity.model;

import top.hunaner.lol.entity.Lolhero;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Simple domain object representing a list of Lolheros. Mostly here to be used for the 'lolheros' {@link
 * org.springframework.web.servlet.view.xml.MarshallingView}.
 *
 * 2016年7月17日 下午3:50:32
 */
@XmlRootElement
public class Lolheros {
	private List<Lolhero> lolheros;
	
	@XmlElement
	public List<Lolhero> getLolheros() {
		if(lolheros == null){
			lolheros = new ArrayList<>();
		}
		return lolheros;
	}
	
	
}
