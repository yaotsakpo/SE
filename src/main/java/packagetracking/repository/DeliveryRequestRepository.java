package packagetracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import packagetracking.model.DeliveryRequest;

import java.util.List;

@Repository
public interface DeliveryRequestRepository extends JpaRepository<DeliveryRequest,Integer> {

    @Query("Select d from DeliveryRequest d where d.requestUser.userId = :userid")
    public List<DeliveryRequest> findAllByRequestUser(@RequestParam int userid);

    @Query("Select d from DeliveryRequest d where d.status = :status")
    public List<DeliveryRequest> findAllByStatus(@RequestParam String status);

    List<DeliveryRequest> findAllByStatusContainingOrRequestPackageTrackingIDContaining(String status,String trackingID);

}
