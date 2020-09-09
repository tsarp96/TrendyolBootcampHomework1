import java.time.LocalDate;
import java.time.LocalDateTime;

public class FixedPackage extends Package {
    public FixedPackage(int packagePrice, int quota, String type){
        super(packagePrice, quota, type);
    }

    @Override
    public double calculatePrice() {
        if(getSpentUnit() == 0){ return  getPackagePrice(); }
        return getPackagePrice() * (getSpentUnit()/getPackageLimit() + 1);
    }

    @Override
    public void useUnit() {
       increaseSpentUnit();
    }

    @Override
    public void renewPackage() {
        setSpentUnit(0);
        setRegistrationTime(LocalDate.now());
    }
}
