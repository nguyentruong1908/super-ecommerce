package com.example.authenticateservce.service;

import com.example.authenticateservce.dto.UserDto;
import com.example.authenticateservce.dto.UserSaveDto;
import com.example.authenticateservce.entity.UserEntity;
import com.example.authenticateservce.model.GeoIP;
import com.example.authenticateservce.model.UserSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDto create(UserSaveDto dto);
    UserDto update(Long id, UserSaveDto dto);
    void delete(Long id);
    Page<UserDto> findAll(UserSearch search, Pageable pageable);
    UserDto findById(Long id);
    UserEntity findByUsername(String username);

    GeoIP getAddressLocation(String ip);
}
