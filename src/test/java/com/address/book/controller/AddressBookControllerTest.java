package com.address.book.controller;


import com.address.book.model.Address;
import com.address.book.model.AddressCount;
import com.address.book.model.Gender;
import com.address.book.service.AddressBookService;
import com.address.book.util.DataNotFoundException;
import org.joda.time.DateTime;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest
public class AddressBookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressBookService addressBookService;

    @Rule
    public ExpectedException exception=ExpectedException.none();


    @Test
    public void getMaleCountFromAddressBook() throws Exception {
        AddressCount count = new AddressCount();
        count.setCount(3);
        when(this.addressBookService.getAddressByGender("Male")).thenReturn(count);
        this.mockMvc.perform(get("/address/gender/{gender}", "Male"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("count").value(3));

    }

    @Test
    public void getOldestPersonInchesAddressBook() throws Exception {
        Address address = new Address();
        address.setFirstName("Wes");
        address.setLastName("Jackson");
        when(this.addressBookService.getOldestPerson()).thenReturn(address);
        this.mockMvc.perform(get("/address/older"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstName").value("Wes"))
                .andExpect(jsonPath("lastName").value("Jackson"));
    }


    @Test
    public void getAgeInDaysDeltaBetween() throws Exception{

        AddressCount count = new AddressCount();
        count.setCount(4078);
        when(this.addressBookService.getAgeInDaysDeltaBetween("Sarah","Gemma")).thenReturn(count);
        this.mockMvc.perform(get("/address/name?firstName=Sarah&secondName=Gemma"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("count").value(4078));
    }

    @Test
    public void getListOfAddresses() throws Exception{
        List<Address> addressList = new ArrayList<>();
        Address address = new Address("Bill","McKnight", Gender.Male,new DateTime());
        addressList.add(address);
        when(this.addressBookService.getAddressList()).thenReturn(addressList);
        MvcResult result=this.mockMvc.perform(get("/address"))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals("correct response returns 200", 200, result.getResponse().getStatus());

        System.out.println(result.getResponse().getHeaderNames());

    }
    @Test
    public void getAgeInDaysDeltaBetweenInvalidData() throws Exception {
        when(this.addressBookService.getAgeInDaysDeltaBetween(any(),any())).thenThrow(DataNotFoundException.class);
        this.mockMvc.perform(get("/address/name?firstName=Any&secondName=Gemma"))
                .andExpect(status().isNotFound());

    }

}
