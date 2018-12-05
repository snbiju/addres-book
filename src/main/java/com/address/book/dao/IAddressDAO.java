package com.address.book.dao;

import com.address.book.model.Address;

import java.util.List;

public interface IAddressDAO {
    List<Address> read();
}

