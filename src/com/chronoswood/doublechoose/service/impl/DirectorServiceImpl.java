package com.chronoswood.doublechoose.service.impl;

import com.chronoswood.doublechoose.dao.DirectorDao;
import com.chronoswood.doublechoose.model.Director;
import com.chronoswood.doublechoose.service.DirectorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DirectorServiceImpl implements DirectorService {

    private DirectorDao directorDao;

    public DirectorServiceImpl(DirectorDao directorDao) {
        this.directorDao = directorDao;
    }

    @Override
    public int addDirector(Director director) {
        return directorDao.addDirector(director);
    }
}
