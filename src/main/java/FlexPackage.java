import java.time.LocalDate;

public class FlexPackage  extends  Package {
    private double pricePerUnit;
    private int flexUsageCount = 0;
    public FlexPackage(double PackagePrice,int quota, double pricePerUnit, String type){
        super(PackagePrice, quota, type);
        this.pricePerUnit = pricePerUnit;
    }

    public void increaseFlexUsageCount(){
        setFlexUsageCount(getFlexUsageCount() + 1);
    }

    @Override
    public double calculatePrice() {
        return getPackagePrice() + flexUsageCount * pricePerUnit;
    }

    @Override
    public void useUnit() {
        if(getSpentUnit() >= getPackageLimit()){
            increaseFlexUsageCount();
        }
        increaseSpentUnit();
    }

    @Override
    public void renewPackage() {
        setFlexUsageCount(0);
        setSpentUnit(0);
        setRegistrationTime(LocalDate.now());
    }

    public int getFlexUsageCount() {
        return flexUsageCount;
    }

    public void setFlexUsageCount(int flexUsageCount) {
        this.flexUsageCount = flexUsageCount;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
}
