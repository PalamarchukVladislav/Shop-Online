import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Immutable class
 */

public final class Check {

    private final String userName;
    private final String userAddress;
    private final List<Goods> goods;
    private final BigDecimal finalPrice;


    /**
     * Constructor performing Deep Copy
     */
    public Check(String userName, String userAddress, List<Goods> goods, BigDecimal finalPrice) {
        this.userName = userName;
        this.userAddress = userAddress;
        this.finalPrice = finalPrice;

        this.goods = new ArrayList<>(goods);
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
