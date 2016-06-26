package lol.controller;

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
@Grab("thymeleaf-spring4")
@Controller
public class HerosUploadController extends WebMvcConfigurerAdapter{
//	@Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/result").setViewName("result");
//    }
	@Autowired
	JdbcTemplate jdbcTemplate;
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
         //Uses JdbcTemplate's batchUpdate operation to bulk load data
      jdbcTemplate.update("INSERT INTO heros(nameCn, nameEn,nickname) VALUES (?,?,?)", new Object[]{ lolheroForm.getNameCn(),lolheroForm.getNameEn(),lolheroForm.getNickname()});

//      log.info("Querying for hero records where nameCn = 'Josh':");
      jdbcTemplate.query(
              "SELECT * FROM heros", 
              (rs, rowNum) -> new LolheroForm(rs.getLong("id"), rs.getString("nameCn"), rs.getString("nameEn"))
      ).forEach(heros -> log.info(heros.toString()));
        model.addAttribute("lolheroForm",lolheroForm);
        return "redirect:/result";
    }
}
