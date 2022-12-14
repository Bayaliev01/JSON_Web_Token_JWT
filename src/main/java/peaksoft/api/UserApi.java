package peaksoft.api;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.AuthRequest;
import peaksoft.dto.AuthResponse;
import peaksoft.dto.UserRequest;
import peaksoft.dto.UserResponse;
import peaksoft.mapper.LoginConverts;
import peaksoft.service.AuthService;
import peaksoft.service.UserService;

//@PreAuthorize("hasAnyAuthority('')")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserApi {
    private final UserService userService;
    private final AuthService authService;
    private final LoginConverts converts;


    @PostMapping("/registration")
    public UserResponse registration(@RequestBody UserRequest userRequest) {
        return userService.save(userRequest);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) {
        return authService.returnToken(authRequest);
    }

}
