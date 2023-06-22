package packagetracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import packagetracking.model.Address;
import packagetracking.model.CreditCard;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query("Select a from Address a where a.user.userId = :userid")
    public List<Address> findAllByRequestUser(@RequestParam int userid);

    public List<Address> findAllByStreetContainingOrZipcodeContainingOrStateContaining(String Street, String zipcode, String State);

}
