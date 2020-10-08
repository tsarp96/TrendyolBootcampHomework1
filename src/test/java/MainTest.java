import LanguagesAndExceptions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class MainTest {
    @Test
    public void check_flex_package_price_when_company_send_notification_to_group(){
        Language language_TR = new Turkish();
        Company company = new Company(language_TR);
        company.purchaseFlexEmailPackage();
        NotificationDTO notification = createNotificationWith("email");
        NotificationGroup group = company.createNotificationGroupWith(createUsersWith(3000));
        //When
        company.send(notification,group);
        //Then
        Assertions.assertEquals(37.5,company.calculateTotalCostOfPackages(company.getPackages()));
    }
    @Test
    public void check_fixed_package_price_when_company_send_notification_to_group(){
        //Given
        Language language_TR = new Turkish();
        Company company = new Company(language_TR);
        company.purchaseFixedSmsPackage();
        NotificationDTO notification = createNotificationWith("sms");
        NotificationGroup group = company.createNotificationGroupWith(createUsersWith(1650));
        //When
        company.send(notification,group);
        //Then
        Assertions.assertEquals(40,company.calculateTotalCostOfPackages(company.getPackages()));
    }
    @Test
    public void company_send_notification_when_in_blacklist_should_throw_exception(){
        //Given
        String exceptionMessage = null;
        Language language_TR = new Turkish();
        Company company = new Company(language_TR);
        Language language = company.getLanguage();
        User user = new User();
        //When
        company.purchaseFixedSmsPackage();
        company.setInBlackList(true);
        Throwable throwable = catchThrowable(() ->  company.send(createNotificationWith("sms"),user));
        //Then
        assertThat(throwable).isNotNull();
        assertThat(throwable).isInstanceOf(CompanyIsInBlackListException.class);
        assertThat(throwable).hasMessageContaining("Şirket kara listede !");
    }
    @Test
    public void company_can_not_have_packages_with_same_type_should_throw_exception(){
        //Given
        String exceptionMessage = null;
        Language language_TR = new Turkish();
        Company company = new Company(language_TR);
        Language turkish = company.getLanguage();
        company.purchaseFixedSmsPackage();
        //When
        Throwable throwable = catchThrowable(() -> company.purchaseFixedSmsPackage());
        //Then
        assertThat(throwable).isNotNull();
        assertThat(throwable).isInstanceOf(PackageAlreadyExistException.class);
        assertThat(throwable).hasMessageContaining("Bu paket zaten kullanımda !");
    }
    @Test
    public void company_send_notification_when_no_package_available_should_throw_exception(){
        //Given
        String exceptionMessage = null;
        Language language_TR = new Turkish();
        Company company = new Company(language_TR);
        Language turkish = company.getLanguage();
        User user = new User();
        //When
        Throwable throwable = catchThrowable(() -> company.send(createNotificationWith("sms"),user));
        //Then
        assertThat(throwable).isNotNull();
        assertThat(throwable).isInstanceOf(NoAvailablePackageException.class);
        assertThat(throwable).hasMessageContaining("Uygun paket bulunamadı !");
    }
    @Test
    public void after_one_month_fixed_package_should_renew(){
        //Given
        Language language_TR = new Turkish();
        Company company = new Company(language_TR);
        company.purchaseFixedSmsPackage();
        Package pack = company.getPackages().get(0);
        sendPreparedNotifications(company,145,"sms");
        LocalDate registrationTime = pack.getRegistrationTime();
        LocalDate oneMonthLater = registrationTime.plusMonths(1);
        Period period = Period.between(registrationTime, oneMonthLater);
        int diff = period.getMonths();
        //When
        if(diff >= 1){ pack.renewPackage(); }
        //Then
        Assertions.assertEquals(LocalDate.now(),pack.getRegistrationTime());
        Assertions.assertEquals(0,pack.getSpentUnit());
    }

    public void sendPreparedNotifications(Company company,int count,String type){
        NotificationDTO notification = createNotificationWith(type);
        User user = new User();
        for(int i=0; i<count; i++){
            company.send(notification,user);
        }
    }

    public NotificationDTO createNotificationWith(String type){
        NotificationDTO notification = new NotificationDTO();
        notification.TYPE = type;
        notification.MESSAGE = "sms notification";
        return notification;
    }

    public List<User> createUsersWith(int count){
        List<User> users = new ArrayList<>();
        for(int i=0;i<count;i++){
            User user = new User();
            users.add(user);
        }
        return users;
    }

}
