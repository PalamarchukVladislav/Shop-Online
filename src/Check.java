import java.util.List;

public class Check {

    private String userName;
    private String userAddress;
    private List<Goods> goods;
    private Long FinalPrice;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    public void setFinalPrice(Long finalPrice) {
        FinalPrice = finalPrice;
    }

    @Override
    public String toString() {
        return "Check{" +
                "userName='" + userName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", goods=" + goods +
                ", FinalPrice=" + FinalPrice +
                '}';
    }
}
