package pl.kurs.relations.onetomany.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "drivers")
@NoArgsConstructor
@Getter
@Setter
public class Driver  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_driver")
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "driver_id", referencedColumnName = "id_driver")
    private Set<Car> cars = new HashSet<>();

    public Driver(String firstName, String lastName, Set<Car> cars) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cars = cars;
    }

    //kontr. bezp. i param., gettery/setery, toString
}
