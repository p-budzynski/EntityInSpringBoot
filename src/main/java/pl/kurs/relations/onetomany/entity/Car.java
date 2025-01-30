package pl.kurs.relations.onetomany.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cars")
@NoArgsConstructor
@Getter
@Setter
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_car")
    private Long id;

    @Column(nullable = false)
    private String producer;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String engineType;

    //@ManyToOne
    //@JoinColumn(name = "driver_id")
    //private Driver driver;

    public Car(String producer, String model, String engineType) {
        this.producer = producer;
        this.model = model;
        this.engineType = engineType;
    }
}
