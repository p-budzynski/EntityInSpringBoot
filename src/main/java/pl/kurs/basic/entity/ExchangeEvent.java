package pl.kurs.basic.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@IdClass(ExchangeEventKey.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExchangeEvent {

    @Id
    private Timestamp date;
    @Id
    private String currencyFrom;
    @Id
    private String currencyTo;

    private BigDecimal amount;
}
