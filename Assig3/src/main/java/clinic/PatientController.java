package clinic;

import clinic.dto.PatientDto;
import clinic.dto.UserDto;
import clinic.entity.Patient;
import clinic.entity.User;
import clinic.mapper.PatientMapper;
import clinic.mapper.UserMapper;
import clinic.service.PatientService;
import clinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
public class PatientController {
    @Autowired
    PatientService patientService;
    @Autowired
    PatientMapper patientMapper;

    @RequestMapping("/crud-patients")
    String home(Model model) {
        addAttributes(model);
        model.addAttribute("newPatient", new PatientDto());
        model.addAttribute("patientDto", new PatientDto());
        return "patients-form";
    }

    @RequestMapping(value = "/crud-patients/create-patient", method = RequestMethod.POST)
    public String create(@ModelAttribute("newPatient") @Valid PatientDto patientDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {
            model.addAttribute("patientDto", new PatientDto());
            addAttributes(model);
            model.addAttribute("errors", bindingResult.getAllErrors().stream().map(r -> r.getDefaultMessage()).collect(Collectors.toList()));
            return "patients-form";
        }
        patientService.save(patientMapper.toPatient(patientDto));
        return "redirect:/crud-patients";
    }

    @RequestMapping(value = "/crud-patients/update-delete-patient", params="action=update", method = RequestMethod.POST)
    public String update(@ModelAttribute("patientDto") @Valid PatientDto selectedPatient, BindingResult bindingResult, Model model){
        if(selectedPatient.getId() == 0){
            bindingResult.addError(new ObjectError("patientDto", "You can't update a patient that doesn't exist."));
        }
        if(bindingResult.hasErrors()) {
            addAttributes(model);
            model.addAttribute("newPatient", new PatientDto());
            model.addAttribute("errors", bindingResult.getAllErrors().stream().map(r -> r.getDefaultMessage()).collect(Collectors.toList()));
            return "patients-form";
        }
        Patient patient = patientMapper.toPatient(selectedPatient);
        patient.setId(selectedPatient.getId());
        patientService.update(patient);
        return "redirect:/crud-patients";
    }

    @RequestMapping(value = "/crud-patients/update-delete-patient", params="action=delete", method = RequestMethod.POST)
    public String delete(@ModelAttribute("patientDto") @Valid PatientDto selectedPatient, BindingResult bindingResult, Model model){
        if(selectedPatient.getId() == 0){
            addAttributes(model);
            model.addAttribute("newPatient", new PatientDto());
            bindingResult.addError(new ObjectError("patientDto", "You can't delete a patient that doesn't exist."));
            model.addAttribute("errors", bindingResult.getAllErrors().stream().map(r -> r.getDefaultMessage()).collect(Collectors.toList()));
            return "patients-form";
        }
        patientService.remove(selectedPatient.getId());
        return "redirect:/crud-patients";
    }

    private void addAttributes(Model model){
        model.addAttribute("patients", patientService.findAll());
    }
}
