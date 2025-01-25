package pl.kurs.homework.task1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.kurs.homework.task1.entity.BodyType;
import pl.kurs.homework.task1.entity.Car;
import pl.kurs.homework.task1.entity.Engine;
import pl.kurs.homework.task1.repository.CarDao;

@SpringBootApplication
public class CarApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(CarApplication.class, args);
        CarDao carDao = ctx.getBean(CarDao.class);

        Engine bmwEngine = new Engine(2997.0, 320.0, "N55");
        Car bmw = new Car("740Li", "BMW", BodyType.SEDAN, bmwEngine);

        carDao.save(bmw);

        bmwEngine.setDesignation("N55B30A");

        carDao.update(bmw);
    }
}
