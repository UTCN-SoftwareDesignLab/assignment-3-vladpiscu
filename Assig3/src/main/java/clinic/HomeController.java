package clinic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/home-admins")
    String adminHome() {
        return "admin-home";
    }

    @RequestMapping(value = "/home-admins/admin-action", params="action=Crud users")
    public String userCrudRedirect(){
        return "redirect:/crud-users";
    }
}
