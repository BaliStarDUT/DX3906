package top.hunaner.lol.service.storage.impl;


/**
 * 
 * 2016年9月5日 下午4:11:28
 */
public class StorageFileNotFoundException extends StorageException {

    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
