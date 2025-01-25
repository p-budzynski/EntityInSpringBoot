package pl.kurs.homework.task1.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.kurs.homework.task1.entity.Car;

@Repository
@Transactional
@RequiredArgsConstructor
public class CarDao {
    private final EntityManager entityManager;

    public Car get(Long id) {
        return entityManager.find(Car.class, id);
    }

    public void save(Car car) {
        if (car.getEngine() != null && car.getEngine().getId() == null) {
            entityManager.persist(car.getEngine());
        }
        entityManager.persist(car);
    }

    public void update(Car car) {
        if (car.getEngine() != null) {
            entityManager.merge(car.getEngine());
        }
        entityManager.merge(car);
    }

    public void delete(Long id) {
        Car carToDelete = get(id);
        if (carToDelete != null) {
            entityManager.remove(carToDelete);
            entityManager.remove(carToDelete.getEngine());
        }
    }

    public void delete(Car car) {
        Car carToDelete = entityManager.merge(car);
        entityManager.remove(carToDelete);
        entityManager.remove(carToDelete.getEngine());
    }

}
