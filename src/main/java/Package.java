import java.time.LocalDate;

public abstract class Package {
    private double packagePrice;
    private int packageLimit;
    private int spentUnit = 0;
    private LocalDate registrationTime;
    private String type;
    private boolean isPaid;

    public Package(double packagePrice, int packageLimit, String type){
        this.registrationTime = LocalDate.now();
        this.packagePrice = packagePrice;
        this.packageLimit = packageLimit;
        this.type = type;
        this.isPaid = true;
    }

    public abstract double calculatePrice();

    public abstract void useUnit();

    public abstract void renewPackage(); // Can be applied monthly

    public void increaseSpentUnit(){
        setSpentUnit(getSpentUnit()+1);
    }

    public boolean isPackageLimitExceeded(){
        return spentUnit >= packageLimit;
    }

    public double getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(int packagePrice) {
        this.packagePrice = packagePrice;
    }

    public int getPackageLimit() {
        return packageLimit;
    }

    public void setPackageLimit(int packageLimit) {
        this.packageLimit = packageLimit;
    }

    public int getSpentUnit() {
        return spentUnit;
    }

    public void setSpentUnit(int spentUnit) {
        this.spentUnit = spentUnit;
    }

    public LocalDate getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(LocalDate registrationTime) {
        this.registrationTime = registrationTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPackagePrice(double packagePrice) {
        this.packagePrice = packagePrice;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
