package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.dto.UserRequest;
import peaksoft.dto.UserResponse;
import peaksoft.mapper.UserMapper;
import peaksoft.repository.UserRepository;
import peaksoft.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse save(UserRequest userRequest) {
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        return userMapper.mapToResponse(
                userRepository.save(
                        userMapper.mapToEntity(userRequest)));
    }
}
