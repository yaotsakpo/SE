package packagetracking.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int invoiceID;
    @DateTimeFormat(pattern = "YYYY-mm-dd")
    private LocalDate invoiceDate;
    private double price;
    private String status;
    @OneToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private DeliveryRequest deliveryRequest;
}
