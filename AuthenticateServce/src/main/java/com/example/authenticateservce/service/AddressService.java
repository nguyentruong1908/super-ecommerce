package com.example.authenticateservce.service;

import com.example.authenticateservce.dto.AddressDto;
import com.example.authenticateservce.dto.AddressSaveDto;
import com.example.authenticateservce.model.AddressSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface AddressService {
    AddressDto create(AddressSaveDto dto);
    AddressDto update(Long id, AddressSaveDto dto);
    void delete(Long id);
    Page<AddressDto> findAll(Pageable pageable, AddressSearch search);
    AddressDto findById(Long id);
}
