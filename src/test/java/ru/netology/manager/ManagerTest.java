package ru.netology.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

public class ManagerTest {
    Book book1 = new Book(
            100,
            "Testing",
            1_000,
            "Petrov");

    Book book2 = new Book(
            101,
            "Testing and Programming",
            2_000,
            "Ivanov");

    Smartphone phone1 = new Smartphone(
            102,
            "Nokia",
            3_000,
            "All Phones");

    Smartphone phone2 = new Smartphone(
            103,
            "Nokia",
            4_000,
            "All Phones");
    @Test
    public void removeByIdInManager() {
        ProductRepository repo = new ProductRepository();
        Manager manager = new Manager(repo);

        manager.add(book1);
        manager.add(book2);
        manager.add(phone1);
        manager.add(phone2);

        Product[] expected = { book1, book2, phone2 };
        Product[] actual = repo.removeById(102);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchBy() {
        ProductRepository repo = new ProductRepository();
        Manager manager = new Manager(repo);

        manager.add(book1);
        manager.add(book2);
        manager.add(phone1);
        manager.add(phone2);

        Product[] expected = { book1, book2 };
        Product[] actual = manager.searchBy("Testing");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchNonProductText() {
        ProductRepository repo = new ProductRepository();
        Manager manager = new Manager(repo);
        manager.add(book1);
        manager.add(phone1);
        manager.add(book2);
        manager.add(phone2);

        Product[] expected = {};
        Product[] actual = manager.searchBy("Last Light");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchTwoProductsText() {
        ProductRepository repo = new ProductRepository();
        Manager manager = new Manager(repo);
        manager.add(book1);
        manager.add(phone1);
        manager.add(book2);
        manager.add(phone2);

        Product[] expected = { phone1, phone2 };
        Product[] actual = manager.searchBy("Nokia");

        Assertions.assertArrayEquals(expected, actual);
    }
}
