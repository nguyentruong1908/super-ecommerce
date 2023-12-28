package com.example.authenticateservce.service.impl;

import com.example.authenticateservce.dto.AddressDto;
import com.example.authenticateservce.dto.AddressSaveDto;
import com.example.authenticateservce.dto.UserDto;
import com.example.authenticateservce.dto.UserSaveDto;
import com.example.authenticateservce.entity.AddressEntity;
import com.example.authenticateservce.entity.UserEntity;
import com.example.authenticateservce.model.GeoIP;
import com.example.authenticateservce.model.UserSearch;
import com.example.authenticateservce.model.UserStatus;
import com.example.authenticateservce.repository.UserRepository;
import com.example.authenticateservce.service.AddressService;
import com.example.authenticateservce.service.RawDBDemoGeoIPLocationService;
import com.example.authenticateservce.service.UserService;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import jakarta.persistence.criteria.Join;
import jakarta.validation.ValidationException;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Primary
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AddressService addressService;
    private final ModelMapper modelMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    private RawDBDemoGeoIPLocationService locationService;

    @Override
    @Transactional
    public UserDto create(UserSaveDto dto) {
        var entity = modelMapper.map(dto, UserEntity.class);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        AddressDto addressDto = addressService.create(dto.getAddress());
        entity.setAddress(modelMapper.map(addressDto, AddressEntity.class));
        entity.setStatus(UserStatus.ACTIVE);
        entity = userRepository.save(entity);
        return modelMapper.map(entity, UserDto.class);
    }

    @Override
    @Transactional
    public UserDto update(Long id, UserSaveDto dto) {
        var entity = userRepository.findById(id)
                .orElseThrow(() -> new ValidationException("User not found !"));
        entity.setUsername(dto.getUsername());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setEmail(dto.getEmail());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPhone(dto.getPhone());
        entity.setRoles(dto.getRoles());
        entity.setGender(dto.getGender());
        entity.setAvatarUrl(dto.getAvatarUrl());
        AddressEntity addressEntity = entity.getAddress();
        addressEntity.setNationalName(dto.getAddress().getNationalName());
        addressEntity.setProvince(dto.getAddress().getProvince());
        addressEntity.setDistrict(dto.getAddress().getDistrict());
        addressEntity.setWards(dto.getAddress().getWards());
        addressEntity.setAddressDetail(dto.getAddress().getAddressDetail());
        addressService.update(entity.getAddress().getId(), modelMapper.map(addressEntity, AddressSaveDto.class));
        entity = userRepository.save(entity);
        return modelMapper.map(entity, UserDto.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var entity = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        userRepository.delete(entity);
    }

    @Override
    public Page<UserDto> findAll(UserSearch search, Pageable pageable) {
        Specification<UserEntity> specification = (root, query, criteriaBuilder) -> criteriaBuilder.and();

        if (search.getSearchQuery() != null) {
            specification = specification.and(((root, query, criteriaBuilder)
                    -> criteriaBuilder.like(root.get("firstName"), "%" + search.getSearchQuery() + "%")));
            specification = specification.or(((root, query, criteriaBuilder)
                    -> criteriaBuilder.like(root.get("lastName"), "%" + search.getSearchQuery() + "%")));
            specification = specification.or(((root, query, criteriaBuilder)
                    -> criteriaBuilder.like(root.get("username"), "%" + search.getSearchQuery() + "%")));
            specification = specification.or(((root, query, criteriaBuilder)
                    -> criteriaBuilder.like(root.get("email"), "%" + search.getSearchQuery() + "%")));
            specification = specification.or(((root, query, criteriaBuilder)
                    -> criteriaBuilder.like(root.get("phone"), "%" + search.getSearchQuery() + "%")));
        }

        if (search.getUsername() != null) {
            specification = specification.and(((root, query, criteriaBuilder)
                    -> criteriaBuilder.equal(root.get("username"), search.getUsername())));
        }

        if (search.getEmail() != null) {
            specification = specification.and(((root, query, criteriaBuilder)
                    -> criteriaBuilder.equal(root.get("email"), search.getEmail())));
        }

        if (search.getPhone() != null) {
            specification = specification.and(((root, query, criteriaBuilder)
                    -> criteriaBuilder.equal(root.get("phone"), search.getPhone())));
        }

        if (search.getRole() != null) {
            specification = specification.and(((root, query, criteriaBuilder)
                    -> criteriaBuilder.equal(root.get("role"), search.getRole())));
        }

        if (search.getGender() != null) {
            specification = specification.and(((root, query, criteriaBuilder)
                    -> criteriaBuilder.equal(root.get("gender"), search.getGender())));
        }

        if (search.getStatus() != null) {
            specification = specification.and(((root, query, criteriaBuilder)
                    -> criteriaBuilder.equal(root.get("status"), search.getStatus())));
        }

        if (search.getNationalName() != null) {
            specification = specification.and((root, query, criteriaBuilder) -> {
                Join<AddressEntity, UserEntity> nationalName = root.join("address");
                return criteriaBuilder.equal(nationalName.get("nationalName"), search.getNationalName());
            });
            if (search.getProvince() != null) {
                specification = specification.and((root, query, criteriaBuilder) -> {
                    Join<AddressEntity, UserEntity> nationalName = root.join("address");
                    return criteriaBuilder.equal(nationalName.get("province"), search.getProvince());
                });
                if (search.getDistrict() != null) {
                    specification = specification.and((root, query, criteriaBuilder) -> {
                        Join<AddressEntity, UserEntity> nationalName = root.join("address");
                        return criteriaBuilder.equal(nationalName.get("district"), search.getDistrict());
                    });
                    if (search.getWards() != null) {
                        specification = specification.and((root, query, criteriaBuilder) -> {
                            Join<AddressEntity, UserEntity> nationalName = root.join("address");
                            return criteriaBuilder.equal(nationalName.get("wards"), search.getWards());
                        });
                    }
                }
            }
        }

        return userRepository.findAll(specification, pageable).map(objectEntity -> modelMapper.map(objectEntity, UserDto.class));
    }

    @Override
    public UserDto findById(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserEntity findByUsername(String username) {
        Specification<UserEntity> specification = ((root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("username"), username));
        return userRepository.findAll(specification).get(0);
    }

    @Override
    public GeoIP getAddressLocation(String ip) {
//            URL url = new URL("http://checkip.amazonaws.com/");
//            try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
//                String ipAddress = br.readLine();
//                return locationService.getLocation(ipAddress);
//            }
        try {
            locationService = new RawDBDemoGeoIPLocationService();
            GeoIP geoIP = locationService.getLocation(ip);
            return geoIP;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (GeoIp2Exception e) {
            throw new RuntimeException(e);
        }
//            RestTemplate restTemplate = new RestTemplate();
//            HttpHeaders headers = new HttpHeaders();
//            HttpEntity requestEntity = new HttpEntity(headers);
//            String address = restTemplate.exchange("https://revgeocode.search.hereapi.com/v1/revgeocode?at=" + geoIP.getLatitude() + "," + geoIP.getLongitude() + "&apiKey=nANzDmP2cZM0R5RC8Fo0RwhzCNmh0peD1hXBKMXH-NQ", HttpMethod.GET, requestEntity, String.class).getBody();
//
//            JSONObject jsonObject= new JSONObject(address);
    }
}
