package web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@Controller基于@Component注解，辅助实现组件扫描，有了这个注解，
//HomeController会被组件扫描器自动发现并声明为Spring应用上下文中的一个bean。
//实际上，这里把@Controller换成@Component，效果是一样的，只是“表意性”变差了。
@Controller
public class HomeController {
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String getHomeViewName() {
        return "home";
    }
}