package pl.kurs.homework.task3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.kurs.homework.task3.dao.AuthorDao;
import pl.kurs.homework.task3.entity.Author;
import pl.kurs.homework.task3.entity.Book;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class AuthorApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(AuthorApplication.class, args);

        Set<Book> books1 = new HashSet<>();
        books1.add(new Book("Wiedźmin: Ostatnie życzenie", "SuperNOWA", "Fantasy", "1234567890"));
        books1.add(new Book("Wiedźmin: Krew elfów", "SuperNOWA", "Fantasy", "3456789012"));

        Author author = new Author("Andrzej", "Sapkowski", "Polska", books1);

        AuthorDao authorDao = ctx.getBean(AuthorDao.class);

        authorDao.save(author);

        Set<Book> books2 = new HashSet<>();
        books2.add(new Book("Wiedźmin: Chrzest ognia", "SuperNOWA", "Fantasy", "5678901234"));
        books2.add(new Book("ZMIENIONY TYTUŁ", "SuperNOWA", "Fantasy", "3456789012"));

        author.setBooks(books2);
        author = authorDao.updateFirstWay(author);

        Set<Book> books3 = new HashSet<>();
        books3.add(new Book("Wiedźmin: Miecz przeznaczenia", "SuperNOWA", "Fantasy", "2345678901"));
        books3.add(new Book("Wiedźmin: Czas pogardy", "SuperNOWA", "Fantasy", "4567890123"));
        books3.add(new Book("ZMIENIONY TYTUŁ", "SuperNOWA", "Fantasy", "5678901234"));

        author.setBooks(books3);
        author = authorDao.updateSecondWay(author);

        authorDao.delete(author);

    }
}
