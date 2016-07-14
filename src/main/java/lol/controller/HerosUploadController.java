package lol.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lol.entity.Lolhero;
import lol.entity.LolheroForm;
import lol.service.HerosService;

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
	public static final String ROOT = "upload-dir";
	private HerosService herosService;
	@Autowired
	public void setHerosService(HerosService herosService) {
		this.herosService = herosService;
	}
	@Autowired
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}
	private ResourceLoader resourceLoader;

	@RequestMapping("/")
    public String index() {
        return "index";
    }
	
    @RequestMapping(value="/lolheros", method=RequestMethod.GET)
    public String showForm(Model model) {
//    	model.addAttribute("files", Files.
//    			.walk(Paths.get(ROOT))
//    			.filter(path -> !path.equals(Paths.get(ROOT)))
//				.map(path -> Paths.get(ROOT).relativize(path))
//				.map(path -> linkTo(methodOn(HerosUploadController.class).getFile(path.toString())).withRel(path.toString()))
//				.collect(Collectors.toList()));
    	model.addAttribute("lolheroForm",new LolheroForm());
        return "form";
    }
    @RequestMapping(method = RequestMethod.POST, value = "/upload")
	public String handleFileUpload(@RequestParam("heroheadpic") MultipartFile file,
								   RedirectAttributes redirectAttributes) {

		if (!file.isEmpty()) {
			try {
				Files.copy(file.getInputStream(), Paths.get(ROOT, file.getOriginalFilename()));
				redirectAttributes.addFlashAttribute("message",
						"You successfully uploaded " + file.getOriginalFilename() + "!");
			} catch (IOException|RuntimeException e) {
				redirectAttributes.addFlashAttribute("message", "Failued to upload " + file.getOriginalFilename() + " => " + e.getMessage());
			}
		} else {
			redirectAttributes.addFlashAttribute("message", "Failed to upload " + file.getOriginalFilename() + " because it was empty");
		}

		return "redirect:/";
	}
    @RequestMapping(value="/lolheros", method=RequestMethod.POST)
    public String checkHeroInfo(@Valid LolheroForm lolheroForm, BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()) {
            return "form";
        }else{
        	Lolhero hero = new Lolhero(lolheroForm.getNameCn(),lolheroForm.getNameEn() ,
        			lolheroForm.getNickname(),lolheroForm.getStory(),lolheroForm.getType()) ;
        	this.herosService.saveHero(hero);
        }
        model.addAttribute("lolheroForm",lolheroForm);
        return "result";
    }
    @RequestMapping(method = RequestMethod.GET, value = "/{filename:.+}")
	@ResponseBody
	public ResponseEntity<?> getFile(@PathVariable String filename) {

		try {
			return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(ROOT, filename).toString()));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
    @RequestMapping(value="/jdbc", method=RequestMethod.GET)
    public String checkHeroInfo(Model model) {
        model.addAttribute("lolheroForm","heroList");
        return "redirect:/result";
    }
    
}
