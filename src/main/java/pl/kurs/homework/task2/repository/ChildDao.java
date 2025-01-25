package pl.kurs.homework.task2.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.kurs.homework.task2.enity.Child;

@Repository
@Transactional
@RequiredArgsConstructor
public class ChildDao {
    private final EntityManager entityManager;

    public Child get(Long id) {
        return entityManager.find(Child.class, id);
    }

    public void save(Child child) {
        if (child.getMother() != null && child.getMother().getId() == null) {
            entityManager.persist(child.getMother());
        }
        if (child.getFather() != null && child.getFather().getId() == null) {
            entityManager.persist(child.getFather());
        }
        entityManager.persist(child);
    }

    public void update(Child child) {
        if (child.getMother() != null) {
            entityManager.merge(child.getMother());
        }
        if (child.getFather() != null) {
            entityManager.merge(child.getFather());
        }
        entityManager.merge(child);
    }

    public void delete(Long id) {
        Child child = get(id);
        if (child != null) {
            entityManager.remove(child);
            entityManager.remove(child.getMother());
            entityManager.remove(child.getFather());
        }
    }

    public void delete(Child child) {
        Child childToDelete = entityManager.merge(child);
        entityManager.remove(childToDelete);
        entityManager.remove(childToDelete.getMother());
        entityManager.remove(childToDelete.getFather());
    }


}
