package kr.ac.jejunu;

import java.util.List;

import kr.ac.jejunu.dao.IndexDao;
import kr.ac.jejunu.model.Enroll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by SeungChan on 2017-06-15.
 */

@RestController
public class HelloRestController {
    @Autowired
    private IndexDao indexDao;

    @RequestMapping("/add")
    public Enroll add(Enroll enroll) {

        Enroll enrollData = indexDao.save(enroll);

        return enrollData;
    }

    @RequestMapping("/list")
    public List<Enroll> list(Model model) {

        List<Enroll> enrollList = (List<Enroll>) indexDao.findAll();

        return enrollList;
    }

    @RequestMapping("/")
    public String index() {
        return "helloworld!";
    }
}
