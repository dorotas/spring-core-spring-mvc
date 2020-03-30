package guru.springframework.services.security;

import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoginAspect {

    @Autowired
    private LoginFailureEventPublisher failureEventPublisher;

    @Autowired
    private LoginSuccessEventPublisher successEventPublisher;

    @Pointcut("execution(* org.springframework.security.authentication.AuthenticationProvider.authenticate(..))")
    public void doAuthenticate() {

    }

    @Before("guru.springframework.services.security.LoginAspect.doAuthenticate() && args(authentication)")
    public void logBefore(Authentication authentication) {
        System.out.println("This is before the Authentication Method: authentication: " + authentication.isAuthenticated());
    }

    @AfterReturning(value = "guru.springframework.services.security.LoginAspect.doAuthenticate()", returning = "authentication")
    public void logAfterAuthenticate(Authentication authentication) {
        System.out.println("This is after the Authentication Method: authentication: " + authentication.isAuthenticated());

        successEventPublisher.publish(new LoginSuccessEvent(authentication));
    }

    @AfterThrowing("guru.springframework.services.security.LoginAspect.doAuthenticate() && args(authentication)")
    public void logAuthenticationException(Authentication authentication) {
        String userDetails = (String) authentication.getPrincipal();
        System.out.println("Login failed for user: " + userDetails);

        failureEventPublisher.publish(new LoginFailureEvent(authentication));
    }
}
