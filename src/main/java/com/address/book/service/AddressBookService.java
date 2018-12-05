package com.address.book.service;


import com.address.book.model.Address;
import com.address.book.model.AddressCount;
import com.address.book.process.AddressProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressBookService {

    private AddressProcess process;

    @Autowired
    public AddressBookService(AddressProcess process) {
        this.process=process;
    }

    public AddressCount getAddressByGender(String gender) {
       return process.getAddressByGender(gender);
    }

    public Address getOldestPerson() {
        return process.getOldestPerson();
    }

    public AddressCount getAgeInDaysDeltaBetween(String firstPerson,String secondPerson) {
        return process.getAgeInDaysDeltaBetween (firstPerson,secondPerson);
    }

    public List<Address> getAddressList() {
        return process.getAddressList();
    }

}
