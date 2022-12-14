package peaksoft.service;

import peaksoft.dto.UserRequest;
import peaksoft.dto.UserResponse;

public interface UserService {
    UserResponse save(UserRequest userRequest);
}
