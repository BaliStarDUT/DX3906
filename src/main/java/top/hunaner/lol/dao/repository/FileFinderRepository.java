package top.hunaner.lol.dao.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by James Yang on 2017/7/11 0011 下午 2:15.
 */
@Repository
public class FileFinderRepository implements FileFinder {
    @PersistenceContext
    private EntityManager entityManager;
}
