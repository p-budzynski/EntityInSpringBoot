package pl.kurs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.kurs.basic.entity.ExchangeEvent;
import pl.kurs.basic.repository.ExchangeEventDao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

@SpringBootApplication
public class EntityInSpringBoot {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(EntityInSpringBoot.class, args);

        Timestamp now = Timestamp.from(Instant.now());
        ExchangeEvent exchangeEvent = new ExchangeEvent(now, "EUR", "PLN", new BigDecimal("10000.00"));
        ExchangeEvent exchangeEvent1 = new ExchangeEvent(now, "CHF", "PLN", new BigDecimal("10000.00"));
        ExchangeEvent exchangeEvent2 = new ExchangeEvent(now, "USD", "PLN", new BigDecimal("10000.00"));
        ExchangeEventDao exchangeEventDao = ctx.getBean(ExchangeEventDao.class);
        exchangeEventDao.save(exchangeEvent);
        exchangeEventDao.save(exchangeEvent1);
        exchangeEventDao.save(exchangeEvent2);



    }
}