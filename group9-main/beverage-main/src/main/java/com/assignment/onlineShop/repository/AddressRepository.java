package com.assignment.onlineShop.repository;

import com.assignment.onlineShop.repository.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
