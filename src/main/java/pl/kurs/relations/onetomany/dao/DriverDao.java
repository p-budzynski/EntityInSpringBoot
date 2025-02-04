package pl.kurs.relations.onetomany.dao;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.kurs.relations.onetomany.entity.Car;
import pl.kurs.relations.onetomany.entity.Driver;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

    public void updateFirstWay(Driver driver) {
        Driver managedDriver = get(driver.getId());
        Set<Car> updateCars = new HashSet<>();

        for (Car car : managedDriver.getCars()) {
            entityManager.remove(car);
        }
        managedDriver.getCars().clear();
        entityManager.merge(managedDriver);

        for (Car car : driver.getCars()) {
            if (car.getId() == null) {
                entityManager.persist(car);
            } else {
                car = entityManager.merge(car);
            }
            updateCars.add(car);
        }
        managedDriver.getCars().addAll(updateCars);
        entityManager.merge(managedDriver);
    }

    public void updateSecondWay(Driver driver) {
        Driver managedDriver = get(driver.getId());

        Set<Car> carsToRemove = managedDriver.getCars().stream()
                .filter(car -> !driver.getCars().contains(car))
                .collect(Collectors.toSet());

        for (Car car : carsToRemove) {
            entityManager.remove(car);
        }

        managedDriver.getCars().removeAll(carsToRemove);
        entityManager.merge(managedDriver);

        for (Car car : driver.getCars()) {
            if (car.getId() == null && !managedDriver.getCars().contains(car)) {
                entityManager.persist(car);
                managedDriver.getCars().add(car);
            }
        }

        entityManager.merge(managedDriver);
    }

    public void delete(Long id) {
        Driver driver = get(id);
        if (driver != null) {
            for (Car car : driver.getCars()) {
                entityManager.remove(car);
            }
        }
        entityManager.remove(driver);
    }

    public void delete(Driver driver) {
        Driver driverToDelete = entityManager.merge(driver);
        for (Car car : driverToDelete.getCars()) {
            entityManager.remove(car);
        }
        entityManager.remove(driverToDelete);
    }

}
