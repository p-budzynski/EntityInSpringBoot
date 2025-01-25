package pl.kurs.homework.task2.enity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "children")
@NoArgsConstructor
@Getter
@Setter
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Integer age;

    @OneToOne
    @JoinColumn(name = "mother_id")
    private Mother mother;

    @OneToOne
    @JoinColumn(name = "father_id")
    private Father father;

    public Child(String firstName, String lastName, Integer age, Mother mother, Father father) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.mother = mother;
        this.father = father;
    }

    @Override
    public String toString() {
        return "Child id: " + id +
                ", first name: " + firstName +
                ", last name: " + lastName +
                ", age: " + age + "\n" + mother + "\n" + father;
    }
}
