package peaksoft;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import peaksoft.model.Auth;
import peaksoft.model.Role;
import peaksoft.model.User;
import peaksoft.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.Collections;

@SpringBootApplication
@RequiredArgsConstructor
public class JsonWebTokenJwtApplication {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public static void main(String[] args) {
        SpringApplication.run(JsonWebTokenJwtApplication.class, args);
    }

    @PostConstruct
    public void init() {
        User user = new User();
        user.setName("admin");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setEmail("admin@gmail.com");

        Role role = new Role();
        role.setName("ADMIN");

        Auth auth = new Auth();
        auth.setEmail(user.getEmail());
        auth.setPassword(user.getPassword());
        auth.setRoles(Collections.singletonList(role));
        user.setAuth(auth);

        userRepository.save(user);

        User user2 = new User();
        user2.setName("Syimyk");
        user2.setPassword(passwordEncoder.encode("syimyk"));
        user2.setEmail("Syimyk@gmail.com");

        Role role2 = new Role();
        role2.setName("USER");

        Auth auth2 = new Auth();
        auth2.setEmail(user2.getEmail());
        auth2.setPassword(user2.getPassword());
        auth2.setRoles(Collections.singletonList(role2));
        user2.setAuth(auth2);
        userRepository.save(user2);

    }
}
