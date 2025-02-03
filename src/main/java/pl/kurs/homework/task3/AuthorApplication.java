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

        Set<Book> books = new HashSet<>();
        books.add(new Book("Wiedźmin: Ostatnie życzenie", "SuperNOWA", "Fantasy"));
        books.add(new Book("Wiedźmin: Miecz przeznaczenia", "SuperNOWA", "Fantasy"));
        books.add(new Book("Wiedźmin: Krew elfów", "SuperNOWA", "Fantasy"));

        Author author = new Author("Andrzej", "Sapkowski", "Polska", books);

        AuthorDao authorDao = ctx.getBean(AuthorDao.class);

        authorDao.save(author);

        books.add(new Book("Wiedźmin: Czas pogardy", "SuperNOWA", "Fantasy"));
        books.add(new Book("Wiedźmin: Chrzest ognia", "SuperNOWA", "Fantasy"));

        authorDao.update(author);



//        authorDao.delete(author);


    }
}
