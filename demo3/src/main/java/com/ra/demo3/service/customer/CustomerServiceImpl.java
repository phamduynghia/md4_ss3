package com.ra.demo3.service.customer;

import com.ra.demo3.model.dto.request.CustomerRequestDTO;
import com.ra.demo3.model.dto.response.CustomerResponseDTO;
import com.ra.demo3.model.entity.Customer;
import com.ra.demo3.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @Override
    public Page<CustomerResponseDTO> findAll(Pageable pageable) {
        Page<Customer> customers = customerRepository.findAll(pageable);
        return customers.map(item -> CustomerResponseDTO.builder()
                .id(item.getId())
                .fullName(item.getFullName())
                .email(item.getEmail())
                .birthday(item.getBirthday()).build());
    }

    @Override
    public CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO) {
        Customer customer = Customer.builder()
                .fullName(customerRequestDTO.getFullName())
                .email(customerRequestDTO.getEmail())
                .password(customerRequestDTO.getPassword())
                .birthday(customerRequestDTO.getBirthday())
                .build();
        Customer customerNew = customerRepository.save(customer);
        return CustomerResponseDTO.builder()
                .id(customerNew.getId())
                .fullName(customerNew.getFullName())
                .email(customerNew.getEmail())
                .birthday(customerNew.getBirthday()).build();
    }
}