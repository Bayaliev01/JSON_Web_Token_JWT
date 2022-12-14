package peaksoft.mapper;


import org.springframework.stereotype.Component;
import peaksoft.dto.AuthResponse;
import peaksoft.model.Auth;
import peaksoft.model.Role;
import peaksoft.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class LoginConverts {

    public AuthResponse loginView(String token,
                                  Auth auth) {
        var authResponse = new AuthResponse();
        if (auth != null) {
            setAuthorite(authResponse, auth.getRoles());
        }
        authResponse.setToken(token);
        return authResponse;
    }

    private void setAuthorite(AuthResponse authResponse, List<Role> roles) {
        Set<String> authorities = new HashSet<>();
        for (Role role : roles) {
            authorities.add(role.getName());
        }
    }
}
