package pl.kurs.homework.task2.enity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "fathers")
@NoArgsConstructor
@Getter
@Setter
public class Father {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Integer age;

    @OneToOne(mappedBy= "father")
    private Child child;

    @OneToOne
    @JoinColumn(name = "wife_id")
    private Mother mother;

    public Father(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Father id: " + id +
                ", first name: " + firstName +
                ", last name: " + lastName +
                ", age: " + age;
    }
}
