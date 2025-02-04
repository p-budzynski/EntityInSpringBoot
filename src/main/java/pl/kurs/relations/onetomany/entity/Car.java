package pl.kurs.relations.onetomany.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

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

    @Column(nullable = false, unique = true, length = 17)
    @NotBlank
    @Size(min = 17, max = 17)
    @Pattern(regexp = "^[A-Za-z0-9]{17}$")
    private String vin;

    //@ManyToOne
    //@JoinColumn(name = "driver_id")
    //private Driver driver;

    public Car(String producer, String model, String engineType, String vin) {
        this.producer = producer;
        this.model = model;
        this.engineType = engineType;
        this.vin = vin;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Car car = (Car) object;
        return Objects.equals(vin, car.vin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vin);
    }
}
