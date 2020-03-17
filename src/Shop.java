import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.IO;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    private List<User> users = new ArrayList<>();

    private List<Goods> goods = new ArrayList<>();

    private Map<User, ShoppingCart> activeShoppingCart = new HashMap<>();

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
                .filter(user -> user.getVipStatus().equals("VIP"))
                .collect(Collectors.toList());
    }

    public void createCartForUser(User user){
        activeShoppingCart.put(user, new ShoppingCart());
    }

    public ShoppingCart getShoppingCartForUser(User user){
        Optional<ShoppingCart> result = Optional.ofNullable(activeShoppingCart.get(user));


        return result.orElse(null);
    }

    public List<User> getAllUsersWithCart(){
        return new ArrayList<>(activeShoppingCart.keySet());
    }

    public List<Goods> getGoodsByNameAndPrice(long price, String keyWord){

        return goods.stream()
                .filter(productByPrice -> productByPrice.getPrice().compareTo(BigDecimal.valueOf(price)) < 0)
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
                .map(result -> result.getValue().getGoods()).findFirst().get());

        check.setFinalPrice(shoppingCart.getFinalPrice());

        return check;
    }

    public void writeCheckToFile(User user) {

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("check for: " + user.getUserName()))) {
            writer.write(String.valueOf(getCheckForUser(user)));
        }catch (IOException e){
            System.out.println(e);
        }

    }

}
