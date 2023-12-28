package com.example.authenticateservce.service.impl;

import com.example.authenticateservce.config.authorize.CustomUserDetails;
import com.example.authenticateservce.config.jwt.JwtAuthenticationFilter;
import com.example.authenticateservce.config.jwt.JwtTokenProvider;
import com.example.authenticateservce.dto.AddressDto;
import com.example.authenticateservce.dto.AddressSaveDto;
import com.example.authenticateservce.dto.UserDto;
import com.example.authenticateservce.dto.UserSaveDto;
import com.example.authenticateservce.entity.AddressEntity;
import com.example.authenticateservce.entity.UserEntity;
import com.example.authenticateservce.model.AddressSearch;
import com.example.authenticateservce.model.LoginRequest;
import com.example.authenticateservce.model.UserSearch;
import com.example.authenticateservce.repository.AddressRepository;
import com.example.authenticateservce.service.AddressService;
import com.example.authenticateservce.service.AuthRestService;
import com.example.authenticateservce.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Primary
@Service
@RequiredArgsConstructor
public class AuthRestServiceImpl implements AuthRestService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public String login(LoginRequest loginRequest) {
        try {
            // Xác thực từ username và password.
            UserEntity user = userService.findByUsername(loginRequest.getUsername());
            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getUsername(),
                                user.getPassword()));
                // Nếu không xảy ra exception tức là thông tin hợp lệ
                // Set thông tin authentication vào Security Context
                SecurityContextHolder.getContext().setAuthentication(authentication);

                // Trả về jwt cho người dùng.
                String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
                return jwt;
            } else {
                return "Mật khẩu không đúng";
            }
        } catch (Exception e) {
            return "Tài khoản không tồn tại";
        }
    }

    @Override
    @Transactional
    public String register(UserSaveDto dto) {
        UserSearch searchUsername = new UserSearch();
        searchUsername.setUsername(dto.getUsername());
        UserSearch searchEmail = new UserSearch();
        searchEmail.setEmail(dto.getEmail());
        UserSearch searchPhone = new UserSearch();
        searchEmail.setPhone(dto.getPhone());
        List<UserDto> listUsername = userService.findAll(searchUsername, Pageable.unpaged()).getContent();
        List<UserDto> listEmail = userService.findAll(searchEmail, Pageable.unpaged()).getContent();
        List<UserDto> listPhone = userService.findAll(searchPhone, Pageable.unpaged()).getContent();
        if (listUsername.isEmpty()) {
            if (listEmail.isEmpty()) {
                if (listPhone.isEmpty()) {
                    userService.create(dto);
                    return "Đăng kí thành công";
                }
                return "Số điện thoại đã tồn tại";
            }
            return "Email đã tồn tại";
        }
        return "Username đã tốn tại";
    }

    @Override
    public UserDto getCurrentUser(HttpServletRequest request) {
        String userToken = jwtAuthenticationFilter.getJwtFromRequest(request);
        String username = tokenProvider.getUserIdFromJWT(userToken);
        UserEntity user = userService.findByUsername(username);
        return modelMapper.map(user, UserDto.class);
    }
}
