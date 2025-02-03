package pl.kurs.homework.task3.dao;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.kurs.homework.task3.entity.Author;
import pl.kurs.homework.task3.entity.Book;

import java.util.Set;

@Repository
@RequiredArgsConstructor
@Transactional
public class AuthorDao {
    private final EntityManager entityManager;

    public void save(Author author) {
        Set<Book> books = author.getBooks();
        for (Book book : books) {
            if (book != null && book.getId() == null) {
                entityManager.persist(book);
            }
        }
        entityManager.persist(author);
    }

    public void update(Author author) {
        Author managedAuthor = get(author.getId());

        for (Book book : managedAuthor.getBooks()) {
            entityManager.remove(book);
        }

        for (Book book : author.getBooks()) {
            if (book.getId() == null) {
                entityManager.persist(book);
            } else {
                entityManager.merge(book);
            }
            managedAuthor.getBooks().add(book);
        }

        entityManager.merge(managedAuthor);
    }

    public Author get(Long id) {
        return entityManager.find(Author.class, id);
    }

    public void delete(Long id) {
        Author author = get(id);
        if (author != null) {
            for (Book book : author.getBooks()) {
                entityManager.remove(book);
            }
        }
        entityManager.remove(author);
    }

    public void delete(Author author) {
        Author authorToDelete = entityManager.merge(author);
        for (Book book : authorToDelete.getBooks()) {
            entityManager.remove(book);
        }
        entityManager.remove(authorToDelete);
    }

}
