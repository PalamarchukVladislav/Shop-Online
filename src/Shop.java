import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * - получить всех вип-юзеров
 * - создать корзину для пользователя
 * - получить корзину для пользователя
 * - получить всех юзеров с корзиной
 * - получить все товары, название которых содержит %слово%, и цена которых ниже %цена%
 * - получить чек для корзины для определённого пользователя (нужно сделать через 1 стрим)
 * - вывести чек в файл
 */

public class Shop {

    List<User> users = new ArrayList<>();

    List<Goods> goods = new ArrayList<>();

    Map<User, ShoppingCart> activeShoppingCart = new HashMap<>();

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    public Map<User, ShoppingCart> getActiveShoppingCart() {
        return activeShoppingCart;
    }

    public List<User> getAllVipUsers(){
        return users.stream()
                .filter(User::getVipStatus)
                .collect(Collectors.toList());
    }

    public void createCartForUser(User user){
        activeShoppingCart.put(user, new ShoppingCart());
    }

    public ShoppingCart getShoppingCartForUser(User user){
       return activeShoppingCart.get(user);
    }

    public Set<User> getAllUsersWithCart(){
        return activeShoppingCart.keySet();
    }

    public List<Goods> getGoodsByNameAndPrice(long price, String keyWord){

        return goods.stream()
                .filter(productByPrice -> productByPrice.getPrice() < price)
                .filter(productByName -> productByName.getName().contains(keyWord))
                .collect(Collectors.toList());
    }

    public Check getCheckForUser(User user){
        Check check = new Check();

        ShoppingCart shoppingCart = new ShoppingCart();

        check.setUserName(user.getUserName());
        check.setUserAddress(user.getAddress());

        check.setGoods(getActiveShoppingCart().entrySet().stream()
                .filter(result -> result.getKey().equals(user))
                .map(result -> result.getValue().goods).findFirst().get());

        check.setFinalPrice(shoppingCart.getFinalPrice());

        return check;
    }

    public void writeCheckToFile(User user) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter("check for: " + user.getUserName()));
        writer.write(String.valueOf(getCheckForUser(user)));

        writer.close();

    }

}
