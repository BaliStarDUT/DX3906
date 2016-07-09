package lol.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import groovy.lang.Grab;
import lol.Application;
import lol.entity.LolheroForm;

/**
 *
 * @date 2016年6月1日 下午6:42:37
 * @author James Yang
 * @version 1.0
 * @since
 */
@Controller
public class HerosUploadController{
	private static final Logger log = LoggerFactory.getLogger(HerosUploadController.class);
	@RequestMapping("/")
    public String index() {
        return "index";
    }
    @RequestMapping(value="/lolheros", method=RequestMethod.GET)
    public String showForm(Model model) {
    	model.addAttribute("lolheroForm",new LolheroForm());
        return "form";
    }

    @RequestMapping(value="/lolheros", method=RequestMethod.POST)
    public String checkHeroInfo(@Valid LolheroForm lolheroForm, BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        model.addAttribute("lolheroForm",lolheroForm);
        return "redirect:/result";
    }
    @RequestMapping(value="/jdbc", method=RequestMethod.GET)
    public String checkHeroInfo(Model model) {
    	
//    	log.info("Creating tables");
////    	jdbcTemplate.execute("DROP TABLE heros IF EXISTS");
////    	jdbcTemplate.execute("CREATE TABLE heros(" +
////    	              "id SERIAL, nameCn VARCHAR(255), nameEn VARCHAR(255))");
//    	       // Split up the array of whole names into an array of first/last names
//    	       List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
//    	               .map(name -> name.split(" "))
//    	               .collect(Collectors.toList());
//    	        // Use a Java 8 stream to print out each tuple of the list
//    	       splitUpNames.forEach(name -> log.info(String.format("Inserting heros record for %s %s", name[0], name[1])));
//    	       // Uses JdbcTemplate's batchUpdate operation to bulk load data
//    	       jdbcTemplate.batchUpdate("INSERT INTO heros(nameCn, nameEn) VALUES (?,?)", splitUpNames);
//    	
//    	       log.info("Querying for hero records where nameCn = 'Josh':");
//    	       jdbcTemplate.query(
//    	               "SELECT id, nameCn, nameEn FROM heros WHERE nameCn = ?", new Object[] { "Josh" },
//    	               (rs, rowNum) -> new LolheroForm(rs.getLong("id"), rs.getString("nameCn"), rs.getString("nameEn"))
//    	        ).forEach(customer -> log.info(customer.toString()));
        model.addAttribute("lolheroForm","");
        return "redirect:/result";
    }
    
}
