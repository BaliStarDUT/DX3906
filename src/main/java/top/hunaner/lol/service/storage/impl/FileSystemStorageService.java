package top.hunaner.lol.service.storage.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import top.hunaner.lol.service.storage.StorageService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;


/**
 * 
 * 2016年9月5日 下午4:08:22
 */
@Service
public class FileSystemStorageService implements StorageService {

    private static final Logger log = LoggerFactory.getLogger(FileSystemStorageService.class);

    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public boolean store(MultipartFile file) {
        long length = 0L;
        try {
            if (file.isEmpty())
                return false;
            length = Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
            log.debug("You successfully uploaded "+length +" bytes " + file.getOriginalFilename() + "!");
        } catch (IOException e) {
            log.error("Failed to upload " + file.getOriginalFilename() + "."+e.toString());
        }
        return length>0?true:false;
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            log.debug("Load all files from " + rootLocation.toAbsolutePath() + "!");
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(path -> this.rootLocation.relativize(path));
        } catch (IOException e) {
            log.debug("Load all files Exception:" + e.getMessage() + "!");
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            log.debug("Load file:"+ filename +" from "+ rootLocation.toAbsolutePath() + "!");
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }else {
                log.debug(filename +" doesn't exist from "+ rootLocation.toAbsolutePath() + "!");
                throw new StorageFileNotFoundException("Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            log.debug("Load File:"+filename +" failed from "+ rootLocation.toAbsolutePath() + "!"+e.getMessage());
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            log.debug("Create "+ rootLocation.toAbsolutePath() + "!");
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            log.debug("Init failed:"+ rootLocation.toAbsolutePath() + "!"+e.getMessage());
            throw new StorageException("Could not initialize storage", e);
        }
    }
}
