import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * - получить общую сумму - создать копию корзины - убрать все товары, цена которых выше %цена%
 */

public class ShoppingCart {

    private List<Goods> goods = new ArrayList<>();

    public BigDecimal getFinalPrice() {

        return goods.stream()
                .map(Goods::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public ShoppingCart copyCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setGoods(goods);
        return shoppingCart;
    }

    public List<Goods> removeGoodsByPrice(Long price) {

        List<Goods> tempList = new ArrayList<>();

        for (Goods good : goods) {
            if (good.getPrice().compareTo(new BigDecimal(price)) < 0) {
                tempList.add(good);
            }
        }

        return tempList;

    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "goods=" + goods +
                '}';
    }

    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }
}
