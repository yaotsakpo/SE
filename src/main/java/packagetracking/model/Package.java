package packagetracking.model;

import javax.persistence.*;

@Entity
@Table(name = "packages")
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int packageID;
    private String trackingID;

    public Package() {
    }

    public Package(String trackingID) {
        this.trackingID = trackingID;
    }

    public String getTrackingID() {
        return trackingID;
    }

    public void setTrackingID(String trackingID) {
        this.trackingID = trackingID;
    }
}
