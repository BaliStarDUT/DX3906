package top.hunaner.lol.service.storage.impl;
/**
 * 
 * 2016年9月5日 下午4:10:48
 */
public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
