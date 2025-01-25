package pl.kurs.homework.task1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name= "cars")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String producer;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "body_type", nullable = false)
    private BodyType bodyType;

    @OneToOne
    @JoinColumn(name = "engine_id")
    private Engine engine;

    public Car(String model, String producer, BodyType bodyType, Engine engine) {
        this.model = model;
        this.producer = producer;
        this.bodyType = bodyType;
        this.engine = engine;
    }
}
