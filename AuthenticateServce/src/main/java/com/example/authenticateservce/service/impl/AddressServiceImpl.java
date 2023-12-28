package com.example.authenticateservce.service.impl;

import com.example.authenticateservce.dto.AddressDto;
import com.example.authenticateservce.dto.AddressSaveDto;
import com.example.authenticateservce.entity.AddressEntity;
import com.example.authenticateservce.model.AddressSearch;
import com.example.authenticateservce.repository.AddressRepository;
import com.example.authenticateservce.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jakarta.validation.ValidationException;
import org.springframework.transaction.annotation.Transactional;

@Primary
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public AddressDto create(AddressSaveDto dto) {
        var entity = modelMapper.map(dto, AddressEntity.class);
        entity = addressRepository.save(entity);
        return modelMapper.map(entity, AddressDto.class);
    }

    @Override
    @Transactional
    public AddressDto update(Long id, AddressSaveDto dto) {
        var entity = addressRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Address not found !"));
        entity.setNationalName(dto.getNationalName());
        entity.setProvince(dto.getProvince());
        entity.setDistrict(dto.getDistrict());
        entity.setWards(dto.getWards());
        entity.setAddressDetail(dto.getAddressDetail());
        entity = addressRepository.save(entity);
        AddressDto addressDto = modelMapper.map(entity, AddressDto.class);
        return addressDto;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var entity = addressRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Address not found !"));
        addressRepository.delete(entity);
    }

    @Override
    public Page<AddressDto> findAll(Pageable pageable, AddressSearch search) {
        Specification<AddressEntity> specification = (root, query, criteriaBuilder) -> criteriaBuilder.and();
        if (search.getNationalName() != null) {
            specification = specification.and(((root, query, criteriaBuilder)
                    -> criteriaBuilder.equal(root.get("nationalName"), search.getNationalName())));
        }
        if (search.getProvince() != null) {
            specification = specification.and(((root, query, criteriaBuilder)
                    -> criteriaBuilder.equal(root.get("province"), search.getProvince())));
        }
        if (search.getDistrict() != null) {
            specification = specification.and(((root, query, criteriaBuilder)
                    -> criteriaBuilder.equal(root.get("district"), search.getDistrict())));
        }
        if (search.getWards() != null) {
            specification = specification.and(((root, query, criteriaBuilder)
                    -> criteriaBuilder.equal(root.get("wards"), search.getWards())));
        }
        if (search.getAddressDetail() != null) {
            specification = specification.and(((root, query, criteriaBuilder)
                    -> criteriaBuilder.equal(root.get("addressDetail"), search.getAddressDetail())));
        }
        return addressRepository.findAll(specification, pageable).map(objectEntity -> modelMapper.map(objectEntity, AddressDto.class));
    }

    @Override
    public AddressDto findById(Long id) {
        return modelMapper.map(addressRepository.findById(id).orElseThrow(), AddressDto.class);
    }

}
