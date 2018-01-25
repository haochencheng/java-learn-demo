package pers.cc.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pers.cc.entity.SerialNo;

import java.io.Serializable;


@Repository
@Transactional(propagation = Propagation.REQUIRES_NEW)
public interface SerialNoRepo extends CrudRepository<SerialNo,Serializable> {



}
