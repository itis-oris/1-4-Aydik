package ru.itis.pizza_fast.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.pizza_fast.model.Address;
import ru.itis.pizza_fast.repository.AddressRepository;
import ru.itis.pizza_fast.service.AddressService;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public Address getOrCreateAddress(String addressStr) {
        return addressRepository.findByAddress(addressStr)
                .orElseGet(() -> addressRepository.save(Address.builder()
                        .address(addressStr)
                        .build()));
    }
}
