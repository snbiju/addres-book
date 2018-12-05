package com.address.book.process;

import com.address.book.dao.AddressDAO;
import com.address.book.model.Address;
import com.address.book.model.AddressCount;
import com.address.book.util.DataNotFoundException;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddressProcess {

    AddressDAO addressDAO;
    private List<Address> addressList;
    @Autowired
    public AddressProcess(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
        addressList= addressDAO.read();
    }

    public AddressCount getAddressByGender(String gender) {
        AddressCount count = new AddressCount();
        addressList.stream().filter(e->gender.equals( e.getGender().name())).count();
        count.setCount((int)addressList.stream().filter(e->gender.equals( e.getGender().name())).count());
        if(count.getCount()==0){
            throw new DataNotFoundException("Data Not Found");
        }
       return count;
    }

    public Address getOldestPerson() {
        Comparator<Address> nameComparator = (a1, a2) -> a1.getBirthday().compareTo(a2.getBirthday());
        List<Address> oldestList = addressList.stream().sorted(nameComparator).collect(Collectors.toList());
        if(oldestList.isEmpty()){
            throw new DataNotFoundException("Data Not Found");
        }
        return oldestList.get(0);
    }

    public AddressCount getAgeInDaysDeltaBetween(String firstPerson,String secondPerson) {
        AddressCount count = new AddressCount();
        List<Address> filterList = addressList.stream()
                .filter(e->e.getFirstName().equalsIgnoreCase(firstPerson)|| e.getFirstName().equalsIgnoreCase(secondPerson))
                .collect(Collectors.toList());
        if(filterList.isEmpty() || filterList.size()==1){
            throw new DataNotFoundException("Data Not Found");
        }
                count.setCount(Math.abs(Days.daysBetween(filterList.get(0).getBirthday(), filterList.get(1).getBirthday()).getDays()));
        return count;
    }

    public List<Address> getAddressList() {
        return addressList;
    }
}
