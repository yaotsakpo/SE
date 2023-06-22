package packagetracking.Contrat;


public class DeliveryRequestWrapper {

    int deliveryAddressID;
    int pickupAddressID;
    double weight;
    double height;

    public DeliveryRequestWrapper() {
    }

    public DeliveryRequestWrapper(int deliveryAddressID, int pickupAddressID, double weight, double height) {
        this.deliveryAddressID = deliveryAddressID;
        this.pickupAddressID = pickupAddressID;
        this.weight = weight;
        this.height = height;
    }

    public int getDeliveryAddressID() {
        return deliveryAddressID;
    }

    public void setDeliveryAddressID(int deliveryAddressID) {
        this.deliveryAddressID = deliveryAddressID;
    }

    public int getPickupAddressID() {
        return pickupAddressID;
    }

    public void setPickupAddressID(int pickupAddressID) {
        this.pickupAddressID = pickupAddressID;
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

    @Override
    public String toString() {
        return "DeliveryRequestWrapper{" +
                "deliveryAddressID=" + deliveryAddressID +
                ", pickupAddressID=" + pickupAddressID +
                ", weight=" + weight +
                ", height=" + height +
                '}';
    }
}
