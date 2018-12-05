package com.address.book.model;

public class AddressCount {
    int count;

    public AddressCount(int count) {
        this.count = count;
    }

    public AddressCount() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "AddressCount{" +
                "count=" + count +
                '}';
    }
}
