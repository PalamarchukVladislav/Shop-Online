import java.math.BigDecimal;

/**
 * Our Class for product
 */

public class Goods {

    private String name;
    private Long code;
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", code=" + code +
                ", price=" + price +
                '}';
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
