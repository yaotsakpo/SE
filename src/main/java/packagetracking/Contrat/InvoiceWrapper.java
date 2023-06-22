package packagetracking.Contrat;

public class InvoiceWrapper {
    private double price;
    private String description;
    private int requestID;

    public InvoiceWrapper() {
    }

    public InvoiceWrapper(double price, String description, int requestID) {
        this.price = price;
        this.description = description;
        this.requestID = requestID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    @Override
    public String toString() {
        return "InvoiceWrapper{" +
                "price=" + price +
                ", description='" + description + '\'' +
                ", requestID=" + requestID +
                '}';
    }
}
