package packagetracking.model;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressID;
    private String Street;
    private String zipcode;
    private String State;
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private User user;

    public Address() {
    }

    public Address(String street, String zipcode, String state, User user) {
        Street = street;
        this.zipcode = zipcode;
        State = state;
        this.user = user;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
