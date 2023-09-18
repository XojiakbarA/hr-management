package uz.pdp.hrmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.hrmanagement.service.UserService;

@Controller
@RequestMapping("auth/verifyEmail")
public class VerifyEmailController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String verifyEmail(@RequestParam String code, Model model) {
        model.addAttribute("code", code);
        return "password";
    }

    @PostMapping
    public String verifyEmail(@RequestParam String password, @RequestParam String code) {
        try {
            userService.verifyEmail(password, code);
        } catch (MailAuthenticationException e) {
            System.err.println(e.getMessage());
            return "error";
        }
        return "success";
    }
}
