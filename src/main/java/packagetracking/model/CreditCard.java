package packagetracking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "creditCards")
public class CreditCard{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardID;
    private String cardNumber;
    private String issueName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expiryDate;
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private User user;
    private boolean preferredCard;

    public CreditCard() {
    }

    public CreditCard(String cardNumber, String issueName, LocalDate expiryDate) {
        this.cardNumber = cardNumber;
        this.issueName = issueName;
        this.expiryDate = expiryDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getIssueName() {
        return issueName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isPreferredCard() {
        return preferredCard;
    }

    public void setPreferredCard(boolean preferredCard) {
        this.preferredCard = preferredCard;
    }

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", issueName='" + issueName + '\'' +
                ", expiryDate=" + expiryDate +
                '}';
    }
}
