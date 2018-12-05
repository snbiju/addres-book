package com.address.book.service;

import com.address.book.dao.AddressDAO;
import com.address.book.model.Address;
import com.address.book.model.AddressCount;
import com.address.book.model.Gender;
import com.address.book.process.AddressProcess;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AddressBookServiceTest {

    AddressDAO addressDAO;
    AddressProcess addressProcess;
    AddressBookService addressBookService;

    @Before
    public void initTests() {
        addressDAO = new AddressDAO();
        addressProcess= new AddressProcess(addressDAO);
        addressBookService = new AddressBookService(addressProcess);


    }
    @Test
    public void getAddressByGender() {
        AddressCount addressCount= addressBookService.getAddressByGender(Gender.Male.name());
        assertEquals(addressCount.getCount(),3);
    }

    @Test
    public void getOldestPerson() {
        Address address = addressBookService.getOldestPerson();
        assertNotNull(address);
        assertEquals(address.getFirstName(),"Wes");
        assertEquals(address.getLastName(),"Jackson");
    }

    @Test
    public void getAgeInDaysDeltaBetween() {
        AddressCount count= addressBookService.getAgeInDaysDeltaBetween("Sarah","Gemma");
        assertEquals(count.getCount(),4078);
    }

    @Test
    public void getAddressList() {
        List<Address> addressList = addressBookService.getAddressList();
        assertEquals(addressList.size(),5);
    }
}