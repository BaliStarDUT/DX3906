package top.hunaner.lol.controller.heros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import top.hunaner.lol.service.storage.StorageService;

/**
 * Created by James Yang on 2017/7/17 0017 下午 7:20.
 */
@Controller(value = "heros")
public class HerosAssetsController {
    private StorageService storageService;

    @Autowired
    public void setStorageService(StorageService storageService) {
        this.storageService = storageService;
    }


}
