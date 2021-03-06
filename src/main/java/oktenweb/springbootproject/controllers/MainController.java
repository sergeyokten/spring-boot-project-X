package oktenweb.springbootproject.controllers;

import oktenweb.springbootproject.dao.ContactDAO;
import oktenweb.springbootproject.models.Contact;
import oktenweb.springbootproject.models.User;
import oktenweb.springbootproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class MainController {


    @Autowired
    private ContactDAO contactDAO;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "hello");

        return "index";
    }


    @PostMapping("/save")
    public String save(
            Contact contact,
            @RequestParam MultipartFile image,
            Errors errors
    ) throws IOException {


        String path = System.getProperty("user.home")
                + File.separator
                + "images"
                + File.separator
                + image.getOriginalFilename();

        image.transferTo(new File(path));
        contact.setAvatar(image.getOriginalFilename());
        contactDAO.save(contact);
        System.out.println(contact);
        return "redirect:/";
    }


    @GetMapping("/showAllContacts")
    public String showAllContacts(Model model) {

        List<Contact> contacts = contactDAO.findAll();
        model.addAttribute("contacts", contacts);
        return "contactList";
    }

    @GetMapping("/contactDetail/{id}")
    public String resolveSingleContact(@PathVariable/*("id")*/ int id,
                                       Model model) {
        Contact contact = contactDAO.findById(id).get();
        model.addAttribute("contact", contact);


        return "singleContact";
    }

    @PostMapping("/updateContact")
    public String updateContact(
            Contact contact
    ) {
        contactDAO.save(contact);
        return "redirect:/showAllContacts";
    }


    @PostMapping("/successURL")
    public String successURL() {
        return "redirect:/showAllContacts";
    }


    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/saveUser")
    public String saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return "redirect:/login";
    }
}
