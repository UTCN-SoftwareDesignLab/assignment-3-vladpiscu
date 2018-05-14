package clinic;

import clinic.dto.ConsultationDto;
import clinic.entity.Consultation;
import clinic.entity.Message;
import clinic.entity.User;
import clinic.mapper.ConsultationMapper;
import clinic.service.ConsultationService;
import clinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ConsultationController {
    @Autowired
    ConsultationService consultationService;
    @Autowired
    ConsultationMapper consultationMapper;
    @Autowired
    PatientService patientService;
    @Autowired
    SimpMessagingTemplate messagingTemplate;

    @RequestMapping("/crud-consultations")
    String home(Model model) {
        addAttributes(model);
        model.addAttribute("newConsultation", new ConsultationDto());
        model.addAttribute("consultationDto", new ConsultationDto());
        model.addAttribute("patients", patientService.findAll());
        model.addAttribute("doctors", new ArrayList<User>());
        model.addAttribute("isSelected", new Boolean(false));
        model.addAttribute("isSelectedUpdate", new Boolean(false));
        return "consultation-form";
    }

    @RequestMapping("/details-consultation")
    String detailsHome(Model model) {
        model.addAttribute("consultations", consultationService.findAll());
        model.addAttribute("consultationDto", new ConsultationDto());
        return "consultation-detail";
    }

    @RequestMapping(value = "/details-consultation/update-details", params="action=update", method = RequestMethod.POST)
    public String updateDetail(@ModelAttribute("consultationDto") ConsultationDto selectedConsultation, BindingResult bindingResult, Model model){
        if(selectedConsultation.getId() == 0){
            bindingResult.addError(new ObjectError("consultationDto", "You can't update a consultation that doesn't exist."));
        }
        if(bindingResult.hasErrors()) {
            model.addAttribute("consultations", consultationService.findAll());
            model.addAttribute("errors", bindingResult.getAllErrors().stream().map(r -> r.getDefaultMessage()).collect(Collectors.toList()));
            addAttributes(model);
            return "consultation-detail";
        }
        Consultation consultation = consultationService.findById(selectedConsultation.getId());
        consultation.setDiagnostic(selectedConsultation.getDiagnostic());
        consultationService.update(consultation);
        return "redirect:/details-consultation";
    }

    @RequestMapping(value = "/crud-consultations/create-consultation", params="select=Select date", method = RequestMethod.POST)
    public String selectDate(@RequestParam Map<String, String> information, Model model){
        LocalDate selectedDate;
        if(information.get("selectedDate").length() > 0)
            selectedDate = LocalDate.parse(information.get("selectedDate"));
        else
            selectedDate = null;
        ConsultationDto consultation = new ConsultationDto();
        if(selectedDate == null) {
            ArrayList<String> errors = new ArrayList<>();
            errors.add("You need to select a date!");
            model.addAttribute("errors", errors);
            model.addAttribute("doctors", new ArrayList<User>());
            model.addAttribute("isSelected", new Boolean(false));
        }
        else{
            model.addAttribute("isSelected", new Boolean(true));
            consultation.setConsultationDate(selectedDate);
            model.addAttribute("doctors", consultationService.findAllAvailableDoctors(Date.valueOf(selectedDate)));
        }
        addAttributes(model);
        model.addAttribute("newConsultation", consultation);
        model.addAttribute("isSelectedUpdate", new Boolean(false));
        model.addAttribute("consultationDto", new ConsultationDto());
        return "consultation-form";
    }

    @RequestMapping(value = "/crud-consultations/create-consultation", params="create=Create", method = RequestMethod.POST)
    public String create(@ModelAttribute("newConsultation") @Valid ConsultationDto consultationDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {
            model.addAttribute("isSelected", new Boolean(true));
            model.addAttribute("errors", bindingResult.getAllErrors().stream().map(r -> r.getDefaultMessage()).collect(Collectors.toList()));
            addAttributes(model);
            model.addAttribute("consultationDto", new ConsultationDto());
            model.addAttribute("doctors", new ArrayList<User>());
            model.addAttribute("isSelectedUpdate", new Boolean(false));
            return "consultation-form";
        }
        Message message = new Message();
        message.setText("Patient "+ consultationDto.getPatient().getName() +" has an appointment with you on " + consultationDto.getConsultationDate().toString() + "!") ;
        messagingTemplate.convertAndSendToUser(consultationDto.getUser().getUsername(), "/queue/reply", message);
        consultationService.save(consultationMapper.toConsultation(consultationDto));
        return "redirect:/crud-consultations";
    }

    @RequestMapping(value = "/crud-consultations/update-delete-consultation", params="select=Select date", method = RequestMethod.POST)
    public String selectDateUpdate(@RequestParam Map<String, String> information, Model model){
        LocalDate selectedDate;
        if(information.get("selectedDate").length() > 0)
            selectedDate = LocalDate.parse(information.get("selectedDate"));
        else
            selectedDate = null;
        ConsultationDto consultation = new ConsultationDto();
        if(selectedDate == null) {
            ArrayList<String> errors = new ArrayList<>();
            errors.add("You need to select a date!");
            model.addAttribute("errors", errors);
            model.addAttribute("doctors", new ArrayList<User>());
            model.addAttribute("isSelectedUpdate", new Boolean(false));
        }
        else{
            model.addAttribute("isSelectedUpdate", new Boolean(true));
            consultation.setConsultationDate(selectedDate);
            model.addAttribute("doctors", consultationService.findAllAvailableDoctors(Date.valueOf(selectedDate)));
        }
        addAttributes(model);
        model.addAttribute("isSelected", new Boolean(false));
        model.addAttribute("newConsultation", new ConsultationDto());
        model.addAttribute("consultationDto", consultation);
        return "consultation-form";
    }

    @RequestMapping(value = "/crud-consultations/update-delete-consultation", params="action=update", method = RequestMethod.POST)
    public String update(@ModelAttribute("consultationDto") @Valid ConsultationDto selectedConsultation, BindingResult bindingResult, Model model){
        if(selectedConsultation.getId() == 0){
            bindingResult.addError(new ObjectError("consultationDto", "You can't update a consultation that doesn't exist."));
        }
        if(bindingResult.hasErrors()) {
            model.addAttribute("isSelected", new Boolean(true));
            model.addAttribute("errors", bindingResult.getAllErrors().stream().map(r -> r.getDefaultMessage()).collect(Collectors.toList()));
            addAttributes(model);
            model.addAttribute("newConsultation", new ConsultationDto());
            model.addAttribute("doctors", new ArrayList<User>());
            model.addAttribute("isSelectedUpdate", new Boolean(true));
            return "consultation-form";
        }
        Consultation consultation = consultationMapper.toConsultation(selectedConsultation);
        consultation.setId(selectedConsultation.getId());
        consultation.setDiagnostic(consultationService.findById(selectedConsultation.getId()).getDiagnostic());
        consultationService.update(consultation);
        return "redirect:/crud-consultations";
    }

    @RequestMapping(value = "/crud-consultations/update-delete-consultation", params="action=delete", method = RequestMethod.POST)
    public String delete(@ModelAttribute("consultationDto") @Valid ConsultationDto selectedConsultation, BindingResult bindingResult, Model model){
        if(selectedConsultation.getId() == 0){
            addAttributes(model);
            model.addAttribute("isSelectedUpdate", new Boolean(false));
            model.addAttribute("doctors", new ArrayList<User>());
            model.addAttribute("newConsultation", new ConsultationDto());
            bindingResult.addError(new ObjectError("consultationDto", "You can't delete a consultation that doesn't exist."));
            model.addAttribute("errors", bindingResult.getAllErrors().stream().map(r -> r.getDefaultMessage()).collect(Collectors.toList()));
            return "consultation-form";
        }
        consultationService.remove(selectedConsultation.getId());
        return "redirect:/crud-consultations";
    }

    @RequestMapping(value = "/crud-consultations/update-delete-consultation", params="action=check in", method = RequestMethod.POST)
    public String checkIn(@ModelAttribute("consultationDto") ConsultationDto selectedConsultation, BindingResult bindingResult, Model model){
        if(selectedConsultation.getId() == 0){
            bindingResult.addError(new ObjectError("consultationDto", "You can't update a consultation that doesn't exist."));
        }
        if(bindingResult.hasErrors()) {
            model.addAttribute("isSelected", new Boolean(true));
            model.addAttribute("errors", bindingResult.getAllErrors().stream().map(r -> r.getDefaultMessage()).collect(Collectors.toList()));
            addAttributes(model);
            model.addAttribute("newConsultation", new ConsultationDto());
            model.addAttribute("doctors", new ArrayList<User>());
            model.addAttribute("isSelectedUpdate", new Boolean(true));
            return "consultation-form";
        }
        Consultation consultation = consultationService.findById(selectedConsultation.getId());
        Message message = new Message();
        message.setText("The patient "+ consultation.getPatient().getName() +" has arrived!") ;
        messagingTemplate.convertAndSendToUser(consultation.getUser().getUsername(), "/queue/reply", message);
        return "redirect:/crud-consultations";
    }

    private void addAttributes(Model model){
        model.addAttribute("consultations", consultationService.findAll());
        model.addAttribute("patients", patientService.findAll());

    }

}
