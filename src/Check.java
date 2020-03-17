import java.math.BigDecimal;
import java.util.List;

public class Check { // TODO immutable

    private String userName;
    private String userAddress;
    private List<Goods> goods;
    private BigDecimal finalPrice;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    @Override
    public String toString() {
        return "Check{" +
                "userName='" + userName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", goods=" + goods +
                ", FinalPrice=" + finalPrice +
                '}';
    }
}
