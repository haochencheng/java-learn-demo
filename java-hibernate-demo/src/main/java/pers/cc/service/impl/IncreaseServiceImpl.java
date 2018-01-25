package pers.cc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pers.cc.entity.SerialNo;
import pers.cc.repository.SerialNoRepo;
import pers.cc.service.IncreaseService;

@Service("increaseService")
public class IncreaseServiceImpl implements IncreaseService {

    @Autowired
    private SerialNoRepo serialNoRepo;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void increase(Integer id) {
        SerialNo serialNo=serialNoRepo.findById(id).get();
        //更新
        serialNo.setSerialNo(serialNo.getSerialNo()+1);
        serialNoRepo.save(serialNo);
    }

    @Override
    public SerialNo findById(long id) {
        return serialNoRepo.findById(id).get();
    }


}
