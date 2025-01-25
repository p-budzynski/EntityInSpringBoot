package pl.kurs.relations.onetoone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.kurs.EntityInSpringBoot;
import pl.kurs.relations.onetoone.entity.Person;
import pl.kurs.relations.onetoone.entity.PersonDetails;
import pl.kurs.relations.onetoone.repository.PersonDao;

@SpringBootApplication
public class OneToOneApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(OneToOneApplication.class, args);

        PersonDao personDao = ctx.getBean(PersonDao.class);

        PersonDetails personDetails = new PersonDetails("123456789", "aaaa@aaaa.pl", "56575843993");
        Person person = new Person("Adam", "Nowak", personDetails);
        personDao.save(person);

        personDetails.setPesel("987654321");
        person.setLastName("Kowalski");

        personDao.update(person);

//        personDao.delete(1L);

        personDao.delete(person);
    }
}
