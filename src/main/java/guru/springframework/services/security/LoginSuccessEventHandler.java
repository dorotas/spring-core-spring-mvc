package guru.springframework.services.security;

import guru.springframework.domain.User;
import guru.springframework.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class LoginSuccessEventHandler implements ApplicationListener<LoginSuccessEvent> {

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(LoginSuccessEvent loginSuccessEvent) {
        Authentication authentication = (Authentication) loginSuccessEvent.getSource();
        System.out.println("Login Event Success for: " + authentication.getPrincipal());
        updateUserAccount(authentication);
    }

    public void updateUserAccount(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findByUserName(userDetails.getUsername());
        if (user != null) {
            user.setFailedLoginAttempts(0);
            System.out.println("Valid username and password, updating failed attempts");
            userService.saveOrUpdate(user);
        }
    }
}
