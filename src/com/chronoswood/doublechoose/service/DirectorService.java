package com.chronoswood.doublechoose.service;

import com.chronoswood.doublechoose.model.Director;

import java.util.List;

public interface DirectorService {
    int updateDirectorInfo(Director director);
    List<Director> queryDirector(int offset, int amount);
    int addDirector(Director director);
    Director queryDirectorByUsername(String userName);
    Director getDirectorById(Integer id);
}
