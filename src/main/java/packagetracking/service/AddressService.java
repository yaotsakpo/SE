package packagetracking.service;

import org.springframework.stereotype.Service;
import packagetracking.model.Address;

import java.util.List;

@Service
public interface AddressService {

    public abstract List<Address> getAllAddress(int userid);
    public abstract Address saveAddress(Address Address);
    public abstract Address getAddressById(Integer AddressId);
    public abstract void deleteAddressById(Integer AddressId);
    public abstract List<Address> searchAddress(String searchString);
    public abstract List<Address> getAll();
}
