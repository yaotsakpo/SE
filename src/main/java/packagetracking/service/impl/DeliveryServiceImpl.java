package packagetracking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import packagetracking.model.DeliveryRequest;
import packagetracking.repository.DeliveryRequestRepository;
import packagetracking.service.DeliveryService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Component
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    DeliveryRequestRepository deliveryRequestRepository;

    @Override
    public List<DeliveryRequest> getAllDeliveryRequests(int userid) {
        return deliveryRequestRepository.findAllByRequestUser(userid);
    }

    @Override
    public List<DeliveryRequest> getAllPendingDelivery() {
        return deliveryRequestRepository.findAllByStatus("Pending");
    }

    @Override
    public List<DeliveryRequest> getAllCanceledDelivery() {
        return deliveryRequestRepository.findAllByStatus("Canceled");
    }

    @Override
    public List<DeliveryRequest> getAllProcessedDelivery() {
        return deliveryRequestRepository.findAllByStatus("Processed");
    }

    @Override
    public List<DeliveryRequest> getAllDeliveredDelivery() {
        return deliveryRequestRepository.findAllByStatus("Delivered");
    }

    @Override
    public List<DeliveryRequest> getAllShippedDelivery() {
        return deliveryRequestRepository.findAllByStatus("Shipped");
    }

    @Override
    public DeliveryRequest saveDeliveryRequest(DeliveryRequest DeliveryRequest) {
        return deliveryRequestRepository.save(DeliveryRequest);
    }

    @Override
    public DeliveryRequest getDeliveryRequestById(Integer DeliveryRequestId) {
        return (deliveryRequestRepository.findById(DeliveryRequestId)).orElse(null);
    }

    @Override
    public void deleteDeliveryRequestById(Integer DeliveryRequestId) {
        deliveryRequestRepository.deleteById(DeliveryRequestId);
    }

    @Override
    public List<DeliveryRequest> searchDeliveryRequests(String searchString) {
        return deliveryRequestRepository.findAllByStatusContainingOrRequestPackageTrackingIDContaining(searchString, searchString);
    }
}
