package kr.ac.jejunu;

import kr.ac.jejunu.dao.IndexDao;
import kr.ac.jejunu.model.Enroll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by SeungChan on 2017-06-15.
 */
@Controller
public class HelloController {

    @Autowired
    private IndexDao indexDao;

    @RequestMapping("/index")
    public  String index(Model model) {
        List<Enroll> list = (List<Enroll>) indexDao.findAll(); //mysql table에 있는 제목, 내용을 List인 list에 담기

        model.addAttribute("list", list); //"list"에 list 오브젝트를 담고  html에 뿌려주기
        return "index";
    }

    @RequestMapping(value = "/enrollpage", method = RequestMethod.POST)
    public String enrollPageUploader(@RequestParam String title, @RequestParam String content) {
        Enroll enroll = new Enroll();
        enroll.setTitle(title);
        enroll.setContent(content); //등록페이지에서 작성한 제목과 내용을 넣음

        indexDao.save(enroll); //저장
        return "redirect:/index"; //index.html 다시 실행
    }

    @RequestMapping("/enrollpage")
    public String enrollPage() {
        return "/content/enrollpage"; //등록하기 버튼 눌렀을 시 등록페이지로 연결
    }

    @RequestMapping(value = "/revisepage", method = RequestMethod.GET)
    public String revisePage(@RequestParam String id, Model model) {
        Enroll enroll = indexDao.findOne(Integer.parseInt(id)); //받아온 id값에 맞는 칼럼 찾기

        model.addAttribute("revise", enroll); //그 id, title, content에 맞는 오브젝트를 "reivse"에 담고 html에 뿌려주기

        return "/content/revisepage";
    }

    @RequestMapping(value = "/detailpage", method = RequestMethod.GET)
    public String detailPageLoad(@RequestParam String id, Model model) {
        Enroll enroll = indexDao.findOne(Integer.parseInt(id)); //받아온 id값에 맞는 칼럼 찾기

        model.addAttribute("detail", enroll); //그 id, title, content에 맞는 오브젝트를 "detail"에 담고 html에 뿌려주기

        return "/content/detailpage";
    }

    @RequestMapping(value = "/deleteContent", method = RequestMethod.POST)
    public String deleteContent(@RequestParam String detail_id, Model model) {
        indexDao.delete(Integer.parseInt(detail_id)); //넘겨받은 id값에 맞는 칼럼을 삭제

        return "redirect:/index"; //다시 게시판으로
    }

    @RequestMapping(value = "/reviseContent", method = RequestMethod.POST)
    public String reviseContent(@RequestParam String revise_id, @RequestParam String revise_title, @RequestParam String revise_content, Model model) {
        Enroll enroll = indexDao.findOne(Integer.parseInt(revise_id)); //id에 맞는 칼럼값 찾아주기

        enroll.setTitle(revise_title); //찾은 title을 수정된 title로 set
        enroll.setContent(revise_content); //찾은 content를 수정된 content로 set

        indexDao.save(enroll); //저장해주고
        return "redirect:/index";
    }


}
