package peaksoft.service;

import peaksoft.dto.AuthRequest;
import peaksoft.dto.AuthResponse;

public interface AuthService {

    AuthResponse returnToken(AuthRequest authRequest);
}
