package com.example.authenticateservce.service;

import com.example.authenticateservce.dto.AddressDto;
import com.example.authenticateservce.dto.AddressSaveDto;
import com.example.authenticateservce.dto.UserDto;
import com.example.authenticateservce.dto.UserSaveDto;
import com.example.authenticateservce.model.AddressSearch;
import com.example.authenticateservce.model.LoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public interface AuthRestService {
    String login(LoginRequest loginRequest);
    String register(UserSaveDto dto);
    UserDto getCurrentUser(HttpServletRequest request);
}
