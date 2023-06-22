package packagetracking.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "deliveryRequests")
public class DeliveryRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int requestID;
    @DateTimeFormat(pattern = "YYYY-mm-dd")
    private LocalDate requestDate;
    private String status;
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private User validator;
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private User requestUser;
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private Address deliveryAddress;
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private Address pickupAddress;
    @OneToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private Package requestPackage;
    private double weight;
    private double height;
    @OneToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private Invoice invoice;

    public DeliveryRequest() {
    }

    public DeliveryRequest(LocalDate requestDate, String status, User requestUser, Address deliveryAddress, Address pickupAddress, double weight, double height) {
        this.requestDate = requestDate;
        this.status = status;
        this.requestUser = requestUser;
        this.deliveryAddress = deliveryAddress;
        this.pickupAddress = pickupAddress;
        this.weight = weight;
        this.height = height;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getValidator() {
        return validator;
    }

    public void setValidator(User validator) {
        this.validator = validator;
    }

    public User getRequestUser() {
        return requestUser;
    }

    public void setRequestUser(User requestUser) {
        this.requestUser = requestUser;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Address getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(Address pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public Package getRequestPackage() {
        return requestPackage;
    }

    public void setRequestPackage(Package requestPackage) {
        this.requestPackage = requestPackage;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
