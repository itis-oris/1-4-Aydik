package ru.itis.pizza_fast.service;

import ru.itis.pizza_fast.model.Address;

public interface AddressService {
    Address getOrCreateAddress(String address);
}
