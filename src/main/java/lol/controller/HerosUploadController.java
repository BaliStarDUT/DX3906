package lol.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import lol.entity.LolheroForm;

/**
 *
 * @date 2016年6月1日 下午6:42:37
 * @author James Yang
 * @version 1.0
 * @since
 */
@Controller
public class HerosUploadController extends WebMvcConfigurerAdapter{
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/result").setViewName("result");
    }
	@RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
    @RequestMapping(value="/lolheros", method=RequestMethod.GET)
    public String showForm(LolheroForm lolheroForm) {
        return "form";
    }

    @RequestMapping(value="/lolheros", method=RequestMethod.POST)
    public String checkHeroInfo(@Valid LolheroForm lolheroForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "form";
        }

        return "redirect:/result";
    }
}
