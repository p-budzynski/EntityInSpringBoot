package pl.kurs.basic.entity;

import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ExchangeEventKey {

    private Timestamp date;
    private String currencyFrom;
    private String currencyTo;
}
