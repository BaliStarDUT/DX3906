package top.hunaner.lol.service.storage.impl;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 
 * 2016年9月5日 下午4:09:44
 */
@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "E:\\mycode\\DX3906\\assets";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
