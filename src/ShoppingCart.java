import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * - получить общую сумму
 * - создать копию корзины
 * - убрать все товары, цена которых выше %цена%
 */

public class ShoppingCart {

    List<Goods> goods = new ArrayList<>();

    public Long getFinalPrice(){
        return goods.stream().mapToLong(Goods::getPrice).sum();
    }

    public List<Goods> copyCart(){
        return new ArrayList<>(goods);
    }

    public void removeGoodsByPrice(long price){

        IntStream.range(0, goods.size()).filter(i -> goods.get(i).getPrice() > price)
                .forEachOrdered(i -> goods.remove(i));
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "goods=" + goods +
                '}';
    }
}
