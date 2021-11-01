package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.UserRepository;
import org.launchcode.codingevents.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class AuthenticationController {
    //needs a userRepository instance since it will be dealing with user objects
    @Autowired
    UserRepository userRepository;

    private static final String userSessionKey = "user";
    //looks for data with the key user in the user's session.  If it fiinds one it attempts to retrieve the
    // corresponding User object from teh data base.  If not, nuull is returned.
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
    //uses an HttpSession object to store the key/value pair
    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());
    }
}
