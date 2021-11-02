package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.UserRepository;
import org.launchcode.codingevents.models.User;
import org.launchcode.codingevents.models.dto.LoginFormDTO;
import org.launchcode.codingevents.models.dto.RegisterFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AuthenticationController {

    @Autowired
    UserRepository userRepository;

    private static final String userSessionKey = "user";

    public User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            return null;
        }

        return user.get();
    }

    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());
    }

//    In the method above, model.addAttribute(new RegisterFormDTO()) will pass a RegisterFormDTO object in with the label registerFormDTO.
    @GetMapping("/register")
    public String displayRegistrationForm(Model model) {
        model.addAttribute(new RegisterFormDTO());
        model.addAttribute("title", "Register");
        return "register";
    }

//    This post mapping Defines the handler method at the route /register that takes a valid RegisterFormDTO object,
//    associated errors, and a Model.
//    In addition, the method needs an HttpServletRequest object. This object represents the incoming request, and will be provided by Spring.
    @PostMapping("/register")
    public String processRegistrationForm(@ModelAttribute @Valid RegisterFormDTO registerFormDTO,
                                          Errors errors, HttpServletRequest request,
                                          Model model) {
//Return the user to the form if an validation errors occur.
        if (errors.hasErrors()) {
            model.addAttribute("title", "Register");
            return "register";
        }
// Retrieve the user with the given username from the database.
        User existingUser = userRepository.findByUsername(registerFormDTO.getUsername());

//        If a user with the given username already exists, register a custom error with the errors
        //        object and return the user to the form. See the note on using errors.rejectValue below.
        if (existingUser != null) {
            errors.rejectValue("username", "username.alreadyexists", "A user with that username already exists");
            model.addAttribute("title", "Register");
            return "register";
        }

//        Compare the two passwords submitted. If they do not match, register a custom error and return the user to the form.
        String password = registerFormDTO.getPassword();
        String verifyPassword = registerFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("title", "Register");
            return "register";
        }
//At this point, we know that a user with the given username does NOT already exist, and the rest of the form data is valid. So we create a new user object,
        // store it in the database, and then create a new session for the user.
        User newUser = new User(registerFormDTO.getUsername(), registerFormDTO.getPassword());
        userRepository.save(newUser);
        setUserInSession(request.getSession(), newUser);

//        Finally, redirect the user to the home page.
        return "redirect:";
    }

    @GetMapping("/login")
    public String displayLoginForm(Model model){
        model.addAttribute(new LoginFormDTO());
        model.addAttribute("title", "Log In");
        return "login";
    }

    @PostMapping("/login")
    public String processLoginForm (@ModelAttribute @Valid LoginFormDTO loginFormDTO, Errors errors,
                                    HttpServletRequest request, Model model) {
        if (errors.hasErrors()){
            model.addAttribute("title", "Log In");
            return "login";
        }

        //retrieves User object with given password from database
        User theUser = userRepository.findByUsername(loginFormDTO.getUsername());

        //if that user does not exist then we register a custom error and return to the form
        if (theUser == null) {
            errors.rejectValue("username", "user.invalid", "The given username does not exist");
            model.addAttribute("title", "Log In");
            return "login";
        }

        //retrieves the submitted password from the DTO
        String password = loginFormDTO.getPassword();

        //if the password is incorrect then we register a custom error and return the form
        if (!theUser.isMatchingPassword(password)) {
            errors.rejectValue("password", "password.invalid", "Invalid password");
            model.addAttribute("title", "Log In");
            return "login";
        }
        //create a new session for user
        setUserInSession(request.getSession(), theUser);

        //return to home page
        return "redirect:";
    }

}
