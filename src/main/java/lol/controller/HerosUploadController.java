package lol.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

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
import lol.entity.model.Lolheros;
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
	//资源文件的保存目录
	public static final String ROOT = "/home/james/Videos/web/lol/Air/assets/images/champions/";
	private HerosService herosService;
	@Autowired
	public void setHerosService(HerosService herosService) {
		this.herosService = herosService;
	}
	//关于系统文件的获取需要用到ResourceLoader
//	private ResourceLoader resourceLoader;
//	@Autowired
//	public void setResourceLoader(ResourceLoader resourceLoader) {
//		this.resourceLoader = resourceLoader;
//	}
	/**
	 * 首页显示欢迎信息
	 * @param model
	 * @return
	 * @throws IOException
	 */
//	@RequestMapping("/")
    public String index(Model model) throws IOException {
//		List<Path> pathList = Files.walk(Paths.get(ROOT)).collect(Collectors.toList());
//		List<Path> fileNameList = new ArrayList();
//		for(Path path:pathList){
//			fileNameList.add(path.getFileName());
//		}
//		pathList.get(0).
//		model.addAttribute("files", pathList);
//		model.addAttribute("paths", pathList);
        return "index";
    }
	
    @RequestMapping(value="/lolheros/new", method=RequestMethod.GET)
    public String showNewHeroForm(Model model) {
    	model.addAttribute("lolheroForm",new LolheroForm());
        return "form";
    }
    @RequestMapping(value="/lolheros/new", method=RequestMethod.POST)
    public String saveHeroInfo(@Valid LolheroForm lolheroForm, 
    		BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()) {
            return "form";
        }else{
        	Lolhero hero = new Lolhero(lolheroForm.getNameCn(),lolheroForm.getNameEn() ,
        			lolheroForm.getNickname(),lolheroForm.getStory(),lolheroForm.getType()) ;
        	this.herosService.saveHero(hero);
        }
        List<Lolhero> herosList = (List<Lolhero>) this.herosService.findHeros();
        model.addAttribute("herosList",herosList);
        model.addAttribute("msg","获取成功");
        return "result";
    }
    @RequestMapping(value = { "/lolheros/heros.json", "/lolheros/heros.xml"})
    public @ResponseBody  Lolheros showResourcesVetList() {
        // Here we are returning an object of type 'Lolheros' rather than a collection of Lolhero objects
        // so it is simpler for JSon/Object mapping
    	Lolheros lolheros = new Lolheros();
    	lolheros.getLolheros().addAll(this.herosService.findHeros());
        return lolheros;
    }
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
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
   
    /**
     * 用于获取ROOT目录下的文件资源
     * @param filename
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/image/{filename:.+}")
	@ResponseBody
	public ResponseEntity<?> getFile(@PathVariable String filename) {
//		try {
//			return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(ROOT, filename).toString()));
//		} catch (Exception e) {
			return ResponseEntity.notFound().build();
//		}
	}
    @RequestMapping(value="/jdbc", method=RequestMethod.GET)
    public String checkHeroInfo(Model model) {
        model.addAttribute("lolheroForm","heroList");
        return "redirect:/result";
    }
    
}
