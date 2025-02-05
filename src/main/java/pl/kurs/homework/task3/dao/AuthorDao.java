package pl.kurs.homework.task3.dao;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.kurs.homework.task3.entity.Author;
import pl.kurs.homework.task3.entity.Book;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

    public Author updateFirstWay(Author author) {
        Author managedAuthor = get(author.getId());
        Set<Book> updateBooks = new HashSet<>();

        for (Book book : managedAuthor.getBooks()) {
            entityManager.remove(book);
        }
        managedAuthor.getBooks().clear();

        entityManager.flush();
        entityManager.merge(managedAuthor);

        for (Book book : author.getBooks()) {
            if (book.getId() == null) {
                entityManager.persist(book);
            } else {
                entityManager.merge(book);
            }
            updateBooks.add(book);
        }

        managedAuthor.getBooks().addAll(updateBooks);
        entityManager.merge(managedAuthor);

        return managedAuthor;
    }

    public Author updateSecondWay(Author author) {
        Author managedAuthor = get(author.getId());

        Set<Book> booksToRemove = managedAuthor.getBooks().stream()
                .filter(book -> !author.getBooks().contains(book))
                .collect(Collectors.toSet());

        booksToRemove.forEach(entityManager::remove);
        managedAuthor.getBooks().removeAll(booksToRemove);

        entityManager.flush();

        for (Book book : author.getBooks()) {
            if (managedAuthor.getBooks().contains(book)) {
                Book existingBook = managedAuthor.getBooks().stream()
                        .filter(b -> b.equals(book))
                        .findFirst()
                        .orElseThrow();

                existingBook.setTitle(book.getTitle());
                existingBook.setPublisher(book.getPublisher());
                existingBook.setType(book.getType());
            } else {
                if (book.getId() == null) {
                    entityManager.persist(book);
                }
                managedAuthor.getBooks().add(book);
            }
        }
        entityManager.merge(managedAuthor);

        return managedAuthor;
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
