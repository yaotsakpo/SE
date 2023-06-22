package packagetracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import packagetracking.model.CreditCard;
import packagetracking.model.DeliveryRequest;


import java.util.List;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {

    @Query("Select c from CreditCard c where c.user.userId = :userid")
    public List<CreditCard> findAllByRequestUser(@RequestParam int userid);

    @Query("Select c from CreditCard c where c.user.userId = :userid and c.preferredCard = true")
    public CreditCard findPreferredCard(@RequestParam int userid);

    List<CreditCard> findAllByIssueNameContaining(String issueName);
}
