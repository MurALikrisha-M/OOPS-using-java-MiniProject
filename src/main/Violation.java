public class Violation {
    private int id;
    private String vehicleId;
    private String violationType;
    private double fineAmount;
    private boolean isPaid;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getVehicleId() { return vehicleId; }
    public void setVehicleId(String vehicleId) { this.vehicleId = vehicleId; }

    public String getViolationType() { return violationType; }
    public void setViolationType(String violationType) { this.violationType = violationType; }

    public double getFineAmount() { return fineAmount; }
    public void setFineAmount(double fineAmount) { this.fineAmount = fineAmount; }

    public boolean isPaid() { return isPaid; }
    public void setPaid(boolean isPaid) { this.isPaid = isPaid; }
}
