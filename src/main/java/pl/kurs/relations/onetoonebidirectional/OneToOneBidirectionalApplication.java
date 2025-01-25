package pl.kurs.relations.onetoonebidirectional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.kurs.relations.onetoonebidirectional.entity.PersonBidirectional;
import pl.kurs.relations.onetoonebidirectional.entity.PersonDetailsBidirectional;
import pl.kurs.relations.onetoonebidirectional.repository.PersonBidirectionalDao;


@SpringBootApplication
public class OneToOneBidirectionalApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(OneToOneBidirectionalApplication.class, args);

        PersonBidirectionalDao personDao = ctx.getBean(PersonBidirectionalDao.class);
        PersonDetailsBidirectional personDetails = new PersonDetailsBidirectional("123456789", "aaaa@aaaa.pl", "56575843993");
        PersonBidirectional person = new PersonBidirectional("Adam", "Nowak", personDetails);
        personDao.save(person);

        personDetails.setPesel("987654321");
        person.setLastName("Kowalski");

        System.out.println(personDao.get(1L).toString());
//        personDao.update(person);

//        personDao.delete(1L);

//        personDao.delete(person);
    }
}
