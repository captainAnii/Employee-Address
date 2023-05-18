package com.geekster.EmployeeAddress.controller;
import com.geekster.EmployeeAddress.model.Address;
import com.geekster.EmployeeAddress.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @GetMapping
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
        Address address = addressRepository.findById(id).orElse(null);
        if (address != null) {
            return ResponseEntity.ok(address);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        Address savedAddress = addressRepository.save(address);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAddress);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address addressDetails) {
        Address address = addressRepository.findById(id).orElse(null);
        if (address != null) {
            address.setStreet(addressDetails.getStreet());
            address.setCity(addressDetails.getCity());
            address.setState(addressDetails.getState());
            address.setZipcode(addressDetails.getZipcode());
            Address updatedAddress = addressRepository.save(address);
            return ResponseEntity.ok(updatedAddress);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        Address address = addressRepository.findById(id).orElse(null);
        if (address != null) {
            addressRepository.delete(address);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}