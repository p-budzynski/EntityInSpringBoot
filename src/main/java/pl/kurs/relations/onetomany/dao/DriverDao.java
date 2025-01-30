package pl.kurs.relations.onetomany.dao;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.kurs.relations.onetomany.entity.Car;
import pl.kurs.relations.onetomany.entity.Driver;

import java.util.HashSet;
import java.util.Set;

@Repository
@RequiredArgsConstructor
@Transactional
public class DriverDao {
    private final EntityManager entityManager;

    public void save(Driver driver) {
        Set<Car> cars = driver.getCars();
        for (Car driverCar : cars) {
            if (driverCar != null && driverCar.getId() == null) {
                entityManager.persist(driverCar);
            }
        }
        entityManager.persist(driver);
    }

    public Driver get(Long id) {
        return entityManager.find(Driver.class, id);
    }

    public void update(Driver driver) {
        Driver managedDriver = entityManager.merge(driver);

        for (Car car : driver.getCars()) {
            if (car != null && car.getId() == null) {
                entityManager.persist(car);
            }
        }

        managedDriver.getCars().clear();
        managedDriver.getCars().addAll(driver.getCars());
    }

    public void delete(Long id) {
        Driver driver = get(id);
        if (driver != null) {
            for (Car car : new HashSet<>(driver.getCars())) {
                entityManager.remove(car);
            }
        }
        entityManager.remove(driver);
    }

    public void delete(Driver driver) {
        Driver driverToDelete = entityManager.merge(driver);
        for (Car car : new HashSet<>(driverToDelete.getCars())) {
            entityManager.remove(car);
        }
        entityManager.remove(driverToDelete);
    }

}
