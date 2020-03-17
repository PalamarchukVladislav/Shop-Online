import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

    private Map<User, ShoppingCart> activeShoppingCart = new HashMap<>();

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    public Map<User, ShoppingCart> getActiveShoppingCart() {
        return activeShoppingCart;
    } // TODO change key and value

    public List<User> getAllVipUsers() {
        return users.stream()
                .filter(user -> user.getVipStatus().equals("VIP"))
                .collect(Collectors.toList());
    }

    public void createCartForUser(User user) {
        activeShoppingCart.put(user, new ShoppingCart());
    }

    public ShoppingCart getShoppingCartForUser(User user) {
        Optional<ShoppingCart> result = Optional.ofNullable(activeShoppingCart.get(user));

        return result.orElse(null);
    }

    public List<User> getAllUsersWithCart() {
        return new ArrayList<>(activeShoppingCart.keySet());
    }

    public List<Goods> getGoodsByNameAndPrice(long price, String keyWord) {

        return goods.stream()
                .filter(productByPrice -> productByPrice.getPrice().compareTo(BigDecimal.valueOf(price)) < 0)
                .filter(productByName -> productByName.getName().contains(keyWord))
                .collect(Collectors.toList());
    }

    public Check getCheckForUser(User user) { // TODO one stream and why final result always 0
        Check check = new Check();

        ShoppingCart shoppingCart = activeShoppingCart.get(user);

        check.setUserName(user.getUserName());
        check.setUserAddress(user.getAddress());

        check.setGoods(getActiveShoppingCart().entrySet().stream()
                .filter(result -> result.getKey().equals(user))
                .map(result -> result.getValue().getGoods()).findFirst().get());

        check.setFinalPrice(shoppingCart.getFinalPrice());

        return check;
    }

    public void writeCheckToFile(String name) {

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("check for: " + name))) {
                writer.write(String.valueOf(getCheckForUser(getUser(name).get()))); //TODO fix exception
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
