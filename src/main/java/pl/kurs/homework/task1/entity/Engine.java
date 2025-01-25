package pl.kurs.homework.task1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "engines")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Engine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double capacity;

    private Double horsepower;

    private String designation;

    public Engine(Double capacity, Double horsepower, String designation) {
        this.capacity = capacity;
        this.horsepower = horsepower;
        this.designation = designation;
    }
}
