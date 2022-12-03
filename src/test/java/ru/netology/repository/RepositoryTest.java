package ru.netology.repository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.manager.Manager;
import ru.netology.repository.NotFoundException;
import ru.netology.repository.ProductRepository;



public class RepositoryTest {

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
            "Motorola",
            3_000,
            "All Phones");

    Smartphone phone2 = new Smartphone(
            103,
            "Nokia",
            4_000,
            "All Phones");

    @Test
    public void findAll() {
        ProductRepository repo = new ProductRepository();

        repo.addProduct(book1);
        repo.addProduct(book2);
        repo.addProduct(phone1);
        repo.addProduct(phone2);

        Product[] expected = { book1, book2, phone1, phone2 };
        Product[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void findTwo() {
        ProductRepository repo = new ProductRepository();

        repo.addProduct(book1);
        repo.addProduct(book2);

        Product[] expected = { book1, book2 };
        Product[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void findOne() {
        ProductRepository repo = new ProductRepository();

        repo.addProduct(book1);

        Product[] expected = { book1 };
        Product[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void empty() {
        ProductRepository repo = new ProductRepository();

        Product[] expected = { };
        Product[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void removeById() {
        ProductRepository repo = new ProductRepository();

        repo.addProduct(book1);
        repo.addProduct(book2);
        repo.addProduct(phone1);
        repo.addProduct(phone2);

        Product[] expected = { book2, phone1, phone2 };
        Product[] actual = repo.removeById(book1.getId());
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void setProductById() {
        Product product = new Product(100, "Testing", 1_000);

        product.setId(100);
        product.setName("Testing");
        product.setPrice(1_000);

        int expected = 100;
        int actual = product.getId();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void setProductByName() {
        Product product = new Product(100, "Testing", 1_000);

        product.setId(100);
        product.setName("Testing");
        product.setPrice(1_000);

        String expected = "Testing";
        String actual = product.getName();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void setProductByPrice() {
        Product product = new Product(100, "Testing", 1_000);

        product.setId(100);
        product.setName("Testing");
        product.setPrice(1_000);

        int expected = 1_000;
        int actual = product.getPrice();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void setBookByAuthor() {
        Book book = new Book(101, "Testing and Programming", 2_000, "Ivanov");

        book.setAuthor("Ivanov");

        String expected = "Ivanov";
        String actual = book.getAuthor();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void setSmartphoneByManufacturer() {
        Smartphone phone = new Smartphone(102,"Motorola", 3_000, "All Phones" );

        phone.setManufacturer("All Phones");

        String expected = "All Phones";
        String actual = phone.getManufacturer();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void removeProductByNotFoundId() {
        ProductRepository repo = new ProductRepository();

        repo.addProduct(book1);
        repo.addProduct(book2);
        repo.addProduct(phone1);
        repo.addProduct(phone2);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(200);
        });
    }
    }
