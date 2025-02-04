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

        Car car1 = new Car("Audi", "RS4", "V6", "CYZXCV12345678909");
        Car car2 = new Car("Audi", "RS6", "V8", "PUTREDA1234567890");

        Set<Car> cars1 = new HashSet<>();
        cars1.add(car1);
        cars1.add(car2);

        Driver driver = new Driver("Adam", "Małysz", cars1);

        DriverDao driverDao = ctx.getBean(DriverDao.class);

        driverDao.save(driver);

        Set<Car> cars2 = new HashSet<>();
        cars2.add(new Car("BMW", "M5CS", "V8", "WBANXQAZ098765432"));

        driver.setCars(cars2);
        driverDao.updateFirstWay(driver);

        driverDao.delete(driver);



    }
}