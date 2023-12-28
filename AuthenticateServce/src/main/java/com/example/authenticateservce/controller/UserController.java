package com.example.authenticateservce.controller;

import com.example.authenticateservce.dto.UserDto;
import com.example.authenticateservce.dto.UserSaveDto;
import com.example.authenticateservce.entity.UserEntity;
import com.example.authenticateservce.model.GeoIP;
import com.example.authenticateservce.model.UserSearch;
import com.example.authenticateservce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserSaveDto dto) {
        return new ResponseEntity<>(userService.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable("id") Long id, @RequestBody UserSaveDto dto) {
        return new ResponseEntity<>(userService.update(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> delete(@PathVariable("id") Long id) {
        try {
            userService.delete(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<Page<UserDto>> findAll(UserSearch search, Pageable pageable) {
        return ResponseEntity.ok(userService.findAll(search, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable("id") Long id) {
        UserDto user = userService.findById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/location/{ip}")
    public ResponseEntity<GeoIP> getLocation(@PathVariable("ip") String ip) {
        return ResponseEntity.ok(userService.getAddressLocation(ip));
    }
}