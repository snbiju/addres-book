package com.address.book.dao;

import com.address.book.model.Address;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AddressDAOTest {


    AddressDAO addressDAO;
    @Before
    public void initTests() {
        addressDAO = new AddressDAO();

    }

    @Test
    public void testReadAllNotNull() {
        assertNotNull(addressDAO.read());
    }
    @Test
    public void readAllData() {
        List<Address> addressList = addressDAO.read();
        Address address=addressList.stream().findFirst().get();
        assertNotNull(address);
        assertEquals("Bill", address.getFirstName());
        assertEquals("McKnight", address.getLastName());

    }
}