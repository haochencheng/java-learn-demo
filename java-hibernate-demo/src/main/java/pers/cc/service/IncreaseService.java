package pers.cc.service;

import pers.cc.entity.SerialNo;

public interface IncreaseService {

    void increase(Integer id);

    SerialNo findById(long id);
}
