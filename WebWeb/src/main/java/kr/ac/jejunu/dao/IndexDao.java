package kr.ac.jejunu.dao;

import kr.ac.jejunu.model.Enroll;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by SeungChan on 2017-06-15.
 */

public interface IndexDao extends CrudRepository<Enroll, Integer> {

}
