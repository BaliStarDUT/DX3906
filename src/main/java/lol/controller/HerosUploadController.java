package lol.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import groovy.lang.Grab;
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
}
