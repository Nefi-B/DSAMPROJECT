package com.assignment.onlineShop.controller;

import com.assignment.onlineShop.repository.entity.Address;
import com.assignment.onlineShop.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;

    @Autowired
    AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public List<Address> getAllAddress() {
        return addressService.findAll();
    }

    @PostMapping
    public Address createAddress(@RequestBody Address address) {
        return addressService.save(address);
    }

    @GetMapping("/getById")
    public Address getAddressById(@RequestParam("id") Long id) {
        return addressService.findById(id);
    }

    @PutMapping
    public Address updateAddress(@RequestBody Address address) {
        return addressService.save(address);
    }

    @DeleteMapping
    public void deleteAddressById(@RequestParam("id") Long id) {
        addressService.delete(id);
    }
}
