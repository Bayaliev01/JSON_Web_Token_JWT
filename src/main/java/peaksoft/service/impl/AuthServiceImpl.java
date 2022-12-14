package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import peaksoft.config.jwt.JwtUtil;
import peaksoft.dto.AuthRequest;
import peaksoft.dto.AuthResponse;
import peaksoft.model.Role;
import peaksoft.model.User;
import peaksoft.repository.UserRepository;
import peaksoft.service.AuthService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;


    @Override
    public AuthResponse returnToken(AuthRequest authRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()));

        String generateToken = jwtUtil.generateToken(authentication);

        User user = userRepository.findByEmail(authRequest.getEmail());
        List<Role> roles = user.getAuth().getRoles();
        String role = String.valueOf(roles.get(0));

        return new AuthResponse(
                user.getEmail()
                , role,
                generateToken);
    }
}
