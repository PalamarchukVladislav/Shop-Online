import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * - получить всех вип-юзеров - создать корзину для пользователя - получить корзину для пользователя - получить всех
 * юзеров с корзиной - получить все товары, название которых содержит %слово%, и цена которых ниже %цена% - получить чек
 * для корзины для определённого пользователя (нужно сделать через 1 стрим) - вывести чек в файл
 */

public class Shop {

    private List<User> users = new ArrayList<>();

    private List<Goods> goods = new ArrayList<>();

    private Map<ShoppingCart, User> activeShoppingCart = new HashMap<>(); // TODO change key and value

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    public List<User> getAllVipUsers() {
        return users.stream()
                .filter(user -> user.getVipStatus().equals("VIP"))
                .collect(Collectors.toList());
    }

    public void createCartForUser(User user, List<Goods> goods) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setGoods(goods);
        activeShoppingCart.put(shoppingCart, user);
    }

    public ShoppingCart getShoppingCartForUser(User user) {
        Optional<ShoppingCart> result = activeShoppingCart.entrySet().stream()
                .filter(set -> set.getValue().equals(user))
                .map(Entry::getKey)
                .findFirst();

        return result.orElseGet(ShoppingCart::new);
    }

    public List<User> getAllUsersWithCart() {
        return new ArrayList<>(activeShoppingCart.values());
    }

    public List<Goods> getGoodsByNameAndPrice(long price, String keyWord) {

        return goods.stream()
                .filter(productByPrice -> productByPrice.getPrice().compareTo(BigDecimal.valueOf(price)) < 0)
                .filter(productByName -> productByName.getName().contains(keyWord))
                .collect(Collectors.toList());
    }


    public Check getCheckForUser(User user) {

        ShoppingCart shoppingCart = activeShoppingCart.entrySet().stream()
                .filter(set -> set.getValue().equals(user))
                .map(Entry::getKey)
                .findFirst().get();

        return new Check(user.getUserName(), user.getAddress(), shoppingCart.getGoods(), shoppingCart.getFinalPrice());

    }

    public void writeCheckToFile(String name) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("check for: " + name))) {
            writer.write(String.valueOf(getCheckForUser(getUser(name).get())));
        } catch (IOException e) {
            System.out.println();
        }

    }

    private Optional<User> getUser(String name) {
        for (User value : users) {
            if (value.getUserName().equals(name)) {
                return Optional.of(value);
            }
        }
        return Optional.empty();
    }
}
