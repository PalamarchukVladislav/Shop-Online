import lombok.Builder;

@Builder
public class User {

    private String userName;
    private String address;
    private String email;
    private String vipStatus;

    public String getUserName() {
        return userName;
    }


    public String getAddress() {
        return address;
    }


    public String getVipStatus() {
        return vipStatus;
    }


    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", vipStatus=" + vipStatus +
                '}';
    }

}
