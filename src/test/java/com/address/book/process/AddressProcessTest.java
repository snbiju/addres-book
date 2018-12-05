package com.address.book.process;

import com.address.book.dao.AddressDAO;
import com.address.book.model.Address;
import com.address.book.model.AddressCount;
import com.address.book.model.Gender;
import com.address.book.util.DataNotFoundException;
import com.address.book.util.InvalidRequestException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AddressProcessTest {


    @Rule
    public ExpectedException exception=ExpectedException.none();

    private AddressProcess addressProcess;
    @Before
    public void initTests() {
        AddressDAO addressDAO = new AddressDAO();
        addressProcess = new AddressProcess(addressDAO);

    }
    @Test
    public void getAddressByGender() {
        AddressCount addressCount=addressProcess.getAddressByGender(Gender.Male.name());
        assertEquals(addressCount.getCount(),3);
    }
    @Test
    public void getAddressByInvalidGender() {
        exception.expect(DataNotFoundException.class);
       AddressCount addressCount=addressProcess.getAddressByGender("");
       assertNull(addressCount);

    }

    @Test
    public void getOldestPerson() {
        Address address=addressProcess.getOldestPerson();
        assertNotNull(address);
        assertEquals(address.getFirstName(),"Wes");
        assertEquals(address.getLastName(),"Jackson");
    }


    @Test
    public void getAgeInDaysDeltaBetween() {
        AddressCount addressCount= addressProcess.getAgeInDaysDeltaBetween("Sarah","Gemma");
        assertEquals(addressCount.getCount(),4078);
    }

    @Test
    public void getAgeInDaysDeltaBetweenInvalidData() {
        exception.expect(DataNotFoundException.class);
        AddressCount addressCount= addressProcess.getAgeInDaysDeltaBetween("","");
        assertNull(addressCount);

    }

    @Test
    public void getAddressList() {
        List<Address> addressList=addressProcess.getAddressList();
        assertEquals(addressList.size(),5);
    }
}