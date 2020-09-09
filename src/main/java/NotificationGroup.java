import java.util.List;

public class NotificationGroup {
    private List<User> users;

    public NotificationGroup(List<User> users){
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
