package com.assignment.onlineShop.service;

import com.assignment.onlineShop.repository.AddressRepository;
import com.assignment.onlineShop.repository.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address save(Address address) {
        return addressRepository.save(address);
    }

    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    public Address findById(Long id) {
        return addressRepository.findById(id).get();
    }

    public void delete(Long id) {
        addressRepository.deleteById(id);
    }
}
