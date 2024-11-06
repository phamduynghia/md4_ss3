package com.ra.demo3.service.customer;


import com.ra.demo3.model.dto.request.CustomerRequestDTO;
import com.ra.demo3.model.dto.response.CustomerResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
Page<CustomerResponseDTO> findAll(Pageable pageable);
CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO);

}
