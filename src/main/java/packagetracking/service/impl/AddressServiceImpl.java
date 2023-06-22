package packagetracking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import packagetracking.model.Address;
import packagetracking.model.Address;
import packagetracking.repository.AddressRepository;
import packagetracking.service.AddressService;

import java.util.List;

@Component
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Override
    public List<Address> getAllAddress(int userid) {
        return addressRepository.findAllByRequestUser(userid);
    }

    @Override
    public Address saveAddress(Address Address) {
        return addressRepository.save(Address);
    }

    @Override
    public Address getAddressById(Integer AddressId) {
        return addressRepository.getById(AddressId);
    }

    @Override
    public void deleteAddressById(Integer AddressId) {
        addressRepository.deleteById(AddressId);
    }

    @Override
    public List<Address> searchAddress(String searchString) {
        return addressRepository.findAllByStreetContainingOrZipcodeContainingOrStateContaining(searchString,searchString,searchString);
    }

    @Override
    public List<Address> getAll() {
        return addressRepository.findAll();
    }
    
}
