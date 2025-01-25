package pl.kurs.homework.task2.enity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "mothers")
@NoArgsConstructor
@Getter
@Setter
public class Mother {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Integer age;

    @OneToOne(mappedBy= "mother")
    private Child child;

    @OneToOne
    @JoinColumn(name = "husband_id")
    private Father father;

    public Mother(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Mother id: " + id +
                ", first name: " + firstName +
                ", last name: " + lastName +
                ", age: " + age;
    }
}
