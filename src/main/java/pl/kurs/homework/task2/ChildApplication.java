package pl.kurs.homework.task2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.kurs.homework.task2.enity.Child;
import pl.kurs.homework.task2.enity.Father;
import pl.kurs.homework.task2.enity.Mother;
import pl.kurs.homework.task2.repository.ChildDao;

@SpringBootApplication
public class ChildApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(ChildApplication.class, args);
        ChildDao childDao = ctx.getBean(ChildDao.class);

        Mother mother = new Mother("Katarzyna", "Wielka", 44);
        Father father = new Father("Karol", "Wielki", 49);
        Child child = new Child("Kazimierz", "Wielki", 17, mother, father);

        mother.setFather(father);
        father.setMother(mother);

        childDao.save(child);

        System.out.println("-----------------------------------------------");
        System.out.println(child);

        child.setLastName("Mały");
        mother.setLastName("Mała");

        childDao.update(child);

        System.out.println(child);

        childDao.delete(1L);


    }
}
