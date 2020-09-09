import LanguagesAndExceptions.*;
import PackagePreferences.*;

import java.util.ArrayList;
import java.util.List;

public class Company{
    private List<Package> packages;
    private boolean inBlackList;
    private double totalCostOfPackages;
    private Language language;

    public Company(String language) {
        this.packages = new ArrayList<>();
        this.totalCostOfPackages = 0;
        this.inBlackList = false;
        setLanguage(language);
    }

    public void setLanguage(String languageCode){
        if(languageCode.equals("TR")){ language = new Turkish(); }
        if(languageCode.equals("EN")){ language = new English(); }
    }
    public boolean isPackageAlreadyExistWith(String type){
       if(getProperPackagesAccordingToNotification(type).isEmpty()){ return false; }
       return true;
    }
    public List<Package> getProperPackagesAccordingToNotification(String Type){
        List<Package> packs = new ArrayList<>();
        for (Package pack: packages) { if (pack.getType().equals(Type)) { packs.add(pack); } }
        return packs;
    }
    public boolean validatePackageWith(String type){
        if(isPackageAlreadyExistWith(type)){ throw new PackageAlreadyExistException(language); }
        if(isInBlackList()){ throw new CompanyIsInBlackListException(language); }
        return true;
    }
    public void useUnitFrom(List<Package> packs){
        for (Package pack :
                packs) {
            pack.useUnit();
        }
    }
    public void send(NotificationDTO notification,User user){
        List<Package> packs = getProperPackagesAccordingToNotification(notification.TYPE);
        if(packs.isEmpty()){ throw new NoAvailablePackageException(language); }
        if(isInBlackList()){ throw new CompanyIsInBlackListException(language); }
        //TODO : Lets assume users will be informed here with notification then:
        useUnitFrom(packs);
    }
    public NotificationGroup createNotificationGroupWith(List<User> users){
        return new NotificationGroup(users);
    }
    public void send(NotificationDTO notification, NotificationGroup group){
        for (User user:
                group.getUsers()) {
            send(notification,user);
        }
    }
    public void purchaseFixedSmsPackage(){
        Package fixedSms = new FixedPackage(FixedSms.PACKAGE_PRICE,FixedSms.QUOTA,FixedSms.TYPE);
        if(validatePackageWith(FixedSms.TYPE)){
            packages.add(fixedSms);
        }
    }
    public void purchaseFlexSmsPackage(){
        Package flexSms = new FlexPackage(FlexSms.PACKAGE_PRICE,FlexSms.QUOTA,FlexSms.PRICE_PER_SMS,FlexSms.TYPE);
        if(validatePackageWith(FlexSms.TYPE)){
            packages.add(flexSms);
        }
    }
    public void purchaseFixedEmailPackage(){
        Package fixedEmail = new FixedPackage(FixedEmail.PACKAGE_PRICE,FixedEmail.QUOTA,FixedEmail.TYPE);
        if(validatePackageWith(FixedEmail.TYPE)){
            packages.add(fixedEmail);
        }
    }
    public void  purchaseFlexEmailPackage(){
        Package flexEmail = new FlexPackage(FlexEmail.PACKAGE_PRICE,FlexEmail.QUOTA,
                FlexEmail.PRICE_PER_EMAIL,FlexEmail.TYPE);
        if(validatePackageWith(FlexEmail.TYPE)){
            packages.add(flexEmail);
        }
    }
    public double calculateTotalCostOfPackages(List<Package> packages){
        double result = 0;
        for (Package pack:
             packages) {
            result += pack.calculatePrice();
        }
        return result;
    }
    public List<Package> getPackages() {
        return packages;
    }
    public void setPackages(List<Package> packages) {
        this.packages = packages;
    }
    public boolean isInBlackList() {
        return inBlackList;
    }
    public void setInBlackList(boolean inBlackList) {
        this.inBlackList = inBlackList;
    }
    public double getTotalCostOfPackages() {
        return totalCostOfPackages;
    }
    public void setTotalCostOfPackages(double totalCostOfPackages) {
        this.totalCostOfPackages = totalCostOfPackages;
    }
    public Language getLanguage() {
        return language;
    }
    public void setLanguage(Language language) {
        this.language = language;
    }
}
