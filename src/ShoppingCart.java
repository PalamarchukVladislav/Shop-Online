import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * - получить общую сумму
 * - создать копию корзины
 * - убрать все товары, цена которых выше %цена%
 */

public class ShoppingCart {

    private List<Goods> goods = new ArrayList<>();

    public BigDecimal getFinalPrice(){

        return goods.stream()
                .map(Goods::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public ShoppingCart copyCart(){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setGoods(goods);
        return shoppingCart;
    }

    public void removeGoodsByPrice(Long price){

        for (int i = 0; i < goods.size(); i++)
            if (goods.get(i).getPrice().compareTo(BigDecimal.valueOf(price)) < 0) {
                goods.remove(i);
            }
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
