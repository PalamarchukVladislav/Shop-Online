import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        // Setup user
        User userMisha = new User();
        userMisha.setUserName("Misha");
        userMisha.setAddress("Solomenskaya 1");
        userMisha.setEmail("someEmailForMisha@mail.com");
        userMisha.setVipStatus(true);

        User userVarya = new User();
        userVarya.setUserName("Varya");
        userVarya.setAddress("Solomenskaya 942");
        userVarya.setEmail("someEmailForVarya@mail.com");
        userVarya.setVipStatus(false);

        List<User> users = new ArrayList<>();
        users.add(userMisha);
        users.add(userVarya);

        // Setup apple product
        Goods apple = new Goods();
        apple.setName("Apple");
        apple.setPrice(100L);
        apple.setCode(4241512L);

        // Setup computer product
        Goods computer = new Goods();
        computer.setName("Dell latitude");
        computer.setPrice(15000L);
        computer.setCode(935211L);

        List<Goods> goods = new ArrayList<>();
        goods.add(apple);
        goods.add(computer);

        // Setup shopping cart
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.goods.add(apple);
        shoppingCart.goods.add(computer);

        // Setup shop
        Shop shop = new Shop();
        shop.setUsers(users);
        shop.setGoods(goods);


        // Отримати фінальну суму з корзини
        System.out.println("Фінальний рахунок по корзині: " + shoppingCart.getFinalPrice() + "\n");

        // Створити копію корзини
        System.out.println("Копія корзини: " + shoppingCart.copyCart().toString() + "\n");

        // Забрати усі товари, ціна на які вища за %ціна%
        shoppingCart.removeGoodsByPrice(200L);
        System.out.println("Без товарів де ціна вища за 200:");
        System.out.println(shoppingCart.toString() + "\n");

        // Отримати список усіх віп юзерів
        System.out.println("Cписок усіх віп юзерів:");
        System.out.println(shop.getAllVipUsers().toString() + "\n");

        // Створити корзину для юзера
        shop.createCartForUser(userVarya);

        // Отримати корзину для юзера
        System.out.println("Юзери з корзинами чи без:");
        System.out.println(shop.getShoppingCartForUser(userVarya));
        System.out.println(shop.getShoppingCartForUser(userMisha) + "\n"); // null

        // Отрмати усіх юзерів з корзиною
        System.out.println("Список юзерів з корзиною:");
        System.out.println(shop.getAllUsersWithCart() + "\n");

        // Отримати усі товари, якщо у назві є %слово% та ціна нижча за %ціна%
        System.out.println("Товари де у назві є 'latitude' та ціна на них менша за 16000:");
        System.out.println(shop.getGoodsByNameAndPrice(16000, "latitude") + "\n");

        // Отримати чек для корзини для визначеного юзера
        System.out.println("Чек для юзера Варя: ");
        System.out.println(shop.getCheckForUser(userVarya).toString());

        // Вивести чек у файл
        shop.writeCheckToFile(userVarya);

    }
}
