package packagetracking.service;

import org.springframework.stereotype.Service;
import packagetracking.model.DeliveryRequest;

import java.util.List;

@Service
public interface DeliveryService {
    public abstract List<DeliveryRequest> getAllDeliveryRequests(int userid);
    public abstract List<DeliveryRequest> getAllPendingDelivery();

    public abstract List<DeliveryRequest> getAllCanceledDelivery();

    public abstract List<DeliveryRequest> getAllProcessedDelivery();

    public abstract List<DeliveryRequest> getAllDeliveredDelivery();

    public abstract List<DeliveryRequest> getAllShippedDelivery();
    public abstract DeliveryRequest saveDeliveryRequest(DeliveryRequest DeliveryRequest);
    public abstract DeliveryRequest getDeliveryRequestById(Integer DeliveryRequestId);
    public abstract void deleteDeliveryRequestById(Integer DeliveryRequestId);
    public abstract List<DeliveryRequest> searchDeliveryRequests(String searchString);
}
