package pl.kurs.relations.onetomany;

import jakarta.transaction.Transactional;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.kurs.relations.onetomany.dao.DriverDao;
import pl.kurs.relations.onetomany.entity.Car;
import pl.kurs.relations.onetomany.entity.Driver;

import java.util.HashSet;
import java.util.Set;


@SpringBootApplication
public class OneToManyApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(OneToManyApplication.class, args);

        Car car1 = new Car("Audi", "RS4", "V6");
        Car car2 = new Car("Audi", "RS6", "V8");

        Set<Car> cars = new HashSet<>();
        cars.add(car1);
        cars.add(car2);

        Driver driver = new Driver("Adam", "Małysz", cars);

        DriverDao driverDao = ctx.getBean(DriverDao.class);

        driverDao.save(driver);

        cars.add(new Car("BMW", "M5CS", "V8"));

        driverDao.update(driver);

        driverDao.delete(driver);



    }
}