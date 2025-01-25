package pl.kurs.relations.onetoone.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "persons")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @OneToOne
    @JoinColumn(name = "person_details_id")
    private PersonDetails personDetails;

    public Person(String firstName, String lastName, PersonDetails personDetails) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personDetails = personDetails;
    }
}