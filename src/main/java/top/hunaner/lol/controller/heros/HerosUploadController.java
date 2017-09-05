package top.hunaner.lol.controller.heros;



import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import top.hunaner.lol.entity.Lolhero;
import top.hunaner.lol.entity.LolheroForm;
import top.hunaner.lol.entity.model.Lolheros;
import top.hunaner.lol.service.HerosService;
import top.hunaner.lol.service.storage.StorageService;
import top.hunaner.lol.service.storage.impl.StorageFileNotFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


/**
 *
 * @date 2016年6月1日 下午6:42:37
 * @author James Yang
 * @version 1.0
 * @since
 */
@Controller
@RequestMapping("/lolheros")
public class HerosUploadController{
	private static final Logger log = LoggerFactory.getLogger(HerosUploadController.class);

	private HerosService herosService;
	
	@Autowired
	public void setHerosService(HerosService herosService) {
		this.herosService = herosService;
	}
	
	private StorageService storageService;

    @Autowired
    public void setStorageService(StorageService storageService) {
		this.storageService = storageService;
	}
    
	//关于系统文件的获取需要用到ResourceLoader
	private final ResourceLoader resourceLoader;

	@Autowired
	public HerosUploadController(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}
	/**
	 * 首页显示欢迎信息
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET)
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

	@RequestMapping(value="/all",method=RequestMethod.GET)
	public ModelAndView  getAllView(ModelAndView modelAndView){
		List<Lolhero> herosList = (List<Lolhero>) this.herosService.findHeros();
		modelAndView.addObject("herosList",herosList);
		modelAndView.addObject("msg","获取成功");
		modelAndView.setViewName("result");
        return modelAndView;
	}
	@RequestMapping(value = "/data/all",method = RequestMethod.GET)
	public ResponseEntity<Collection<Lolhero>> getAll(){
		return new ResponseEntity<Collection<Lolhero>>(herosService.findHeros(), HttpStatus.OK);
	}

    @RequestMapping(value="/new", method=RequestMethod.GET)
    public String showNewHeroForm(Model model) {
    	model.addAttribute("lolheroForm",new LolheroForm());
        return "heros/heros_upload_resource";
    }
    
    @RequestMapping(value="/data/new", method=RequestMethod.POST)
    public ResponseEntity<Long> saveHeroInfo(@Valid LolheroForm lolheroForm,
    		@RequestParam(value = "heroheadpic",required = true) MultipartFile picFile,
    		@RequestParam(value = "herosound",required = true) MultipartFile soundFile,
    		BindingResult bindingResult,Model model) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<Long>(HttpStatus.BAD_REQUEST);
		}
		boolean picstore = storageService.store(picFile);
		boolean soundstore = storageService.store(soundFile);
		Lolhero hero = new Lolhero(lolheroForm.getNameCn(),lolheroForm.getNameEn() ,
				lolheroForm.getNickname(),lolheroForm.getStory(),lolheroForm.getType(),picFile.getOriginalFilename(),soundFile.getOriginalFilename());
		return new ResponseEntity<Long>(this.herosService.saveHero(hero),HttpStatus.OK);
    }
    
    @RequestMapping(value = "/files",method=RequestMethod.GET)
    public String listUploadedFiles(Model model) throws IOException {
        model.addAttribute("files", storageService
                .loadAll()
                .map(path ->
                        MvcUriComponentsBuilder
                                .fromMethodName(HerosUploadController.class, "serveFile", path.getFileName().toString())
                                .build().toString())
                .collect(Collectors.toList()));

        return "heros/heros_resources";
    }
    
    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
    
    @RequestMapping(value = "/files/{filename:.+}",method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
                .body(file);
    }

    
    @RequestMapping(value = { "/heros.json", "/heros.xml"})
    public @ResponseBody
	Lolheros showResourcesVetList() {
        // Here we are returning an object of type 'Lolheros' rather than a collection of Lolhero objects
        // so it is simpler for JSon/Object mapping
    	Lolheros lolheros = new Lolheros();
    	lolheros.getLolheros().addAll(this.herosService.findHeros());
        return lolheros;
    }
   
    @RequestMapping(value="/jdbc", method=RequestMethod.GET)
    public String checkHeroInfo(Model model) {
        model.addAttribute("lolheroForm","heroList");
        return "redirect:/result";
    }
}
