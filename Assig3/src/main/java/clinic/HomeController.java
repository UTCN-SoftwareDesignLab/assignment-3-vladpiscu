package clinic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/home-admins")
    String adminHome() {
        return "admin-home";
    }

    @RequestMapping("/home-secretary")
    String secretaryHome() {
        return "secretary-home";
    }

    @RequestMapping("/home-doctor")
    String doctorHome() {
        return "doctor-home";
    }

    @RequestMapping(value = "/home-doctor/doctor-action", params="action=Update diagnostics")
    public String updateDiagnosticRedirect(){
        return "redirect:/details-consultation";
    }

    @RequestMapping(value = "/home-secretary/secretary-action", params="action=Crud patients")
    public String patientCrudRedirect(){
        return "redirect:/crud-patients";
    }

    @RequestMapping(value = "/home-secretary/secretary-action", params="action=Crud consultations")
    public String consultationCrudRedirect(){
        return "redirect:/crud-consultations";
    }

    @RequestMapping(value = "/home-admins/admin-action", params="action=Crud users")
    public String userCrudRedirect(){
        return "redirect:/crud-users";
    }
}
