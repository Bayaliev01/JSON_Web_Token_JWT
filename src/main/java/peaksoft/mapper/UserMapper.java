package peaksoft.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peaksoft.dto.UserRequest;
import peaksoft.dto.UserResponse;
import peaksoft.model.Auth;
import peaksoft.model.User;
import peaksoft.repository.RoleRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final RoleRepository repository;

    public User mapToEntity(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());

        Auth auth = new Auth();
        auth.setEmail(userRequest.getEmail());
        auth.setPassword(userRequest.getPassword());
        auth.setRoles(Collections.singletonList(repository.findByName("USER")));

        user.setAuth(auth);
        return user;
    }

    public UserResponse mapToResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail());
    }

    public List<UserResponse> mapToResponse(List<User> users) {
        List<UserResponse> userResponseList = new ArrayList<>();
        for (User user : users) {
            userResponseList.add(mapToResponse(user));
        }
        return userResponseList;
    }

}

