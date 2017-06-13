package top.hunaner.lol.service.storage.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * 
 * 2016年9月5日 下午4:09:44
 */
@ConfigurationProperties(prefix = "locale")
public class StorageProperties {

    /**
     * Folder location for storing files
     * 默认使用assets目录，需要配置locale.storageProperties.location
     */
    @Value("${locale.storageProperties.location}")
    private String location = "assets";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
