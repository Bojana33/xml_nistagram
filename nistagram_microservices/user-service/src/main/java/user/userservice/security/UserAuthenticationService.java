package user.userservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import user.userservice.Model.User;
import user.userservice.Service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static user.userservice.Model.UserRole.*;

@Service
public class UserAuthenticationService
        implements AuthenticationProvider
{
    private UserServiceImpl userService;
    @Autowired
    public UserAuthenticationService(UserServiceImpl userService){
        this.userService = userService;
    }
    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException
    {
        System.out.println("test");
        Authentication retVal = null;
        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        User user = this.userService.findByUsernameAndPassword(auth.getName(),auth.getCredentials().toString());
        if (auth != null && user!= null) {
            String username = auth.getName();
            String password = auth.getCredentials().toString();
            System.out.println("username: " + username);
            System.out.println("password: " + password);
            if (user.getUserRole() == ADMIN) {
                grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                retVal = new UsernamePasswordAuthenticationToken(
                        username, password, grantedAuths
                );
            }
            else if (user.getUserRole() == USER){
                grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
                retVal = new UsernamePasswordAuthenticationToken(
                        username, password, grantedAuths
                );
            } else if (user.getUserRole() == AGENT){
                grantedAuths.add(new SimpleGrantedAuthority("ROLE_AGENT"));
                retVal = new UsernamePasswordAuthenticationToken(
                        username, password, grantedAuths
                );
            }
        }
        else
        {
            System.out.println("invalid login");
            retVal = new UsernamePasswordAuthenticationToken(
                    null, null, grantedAuths
            );
            System.out.println("bad Login");
        }
        System.out.println("return login info");
        return retVal;
    }
    @Override
    public boolean supports(Class<?> tokenType)
    {
        return tokenType.equals(UsernamePasswordAuthenticationToken.class);
    }
}

