package spittr.web;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import spittr.Spittle;
import spittr.data.SpittleRepository;


@Controller
@RequestMapping("/spittles")
public class SpittleController {
    private SpittleRepository spittleRepository;

    @Autowired
    public SpittleController(SpittleRepository spittleRepository){
        this.spittleRepository = spittleRepository;
    }

    @RequestMapping(method=RequestMethod.GET)
    public String spittles(Model model) {
        List<Spittle> spittles = spittleRepository.findSpittles();
        //数据（属性）key="spittles"
        model.addAttribute("spittles", spittles);
        model.addAttribute("title", "Your Recent Spittles");

        //返回视图名称="spittles"
        return "spittles";
    }
    
}