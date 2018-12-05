package com.address.book.controller;

import com.address.book.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AddressBookController {

    private AddressBookService addressBookService;

    
    @Autowired
    AddressBookController(AddressBookService addressBookService) {
        this.addressBookService=addressBookService;
    }

    @GetMapping("/address/gender/{gender}")
    public ResponseEntity<?> getAddressByGender(@PathVariable String gender ) {
       return new ResponseEntity<>(addressBookService.getAddressByGender(gender),HttpStatus.OK);
    }

    @GetMapping("/address/older")
    public ResponseEntity<?>  getOldestPerson() {
        return new ResponseEntity<>(addressBookService.getOldestPerson(),HttpStatus.OK);
    }

    @GetMapping("/address/name")
    public ResponseEntity<?> getAgeInDaysDeltaBetween(@RequestParam String firstName
            , @RequestParam String secondName){
        return new ResponseEntity<> (addressBookService.getAgeInDaysDeltaBetween(firstName,secondName), HttpStatus.OK);
    }
    @GetMapping("/address")
    public ResponseEntity<?> getAddressList(){
        return new ResponseEntity<> (addressBookService.getAddressList(), HttpStatus.OK);
    }


}
