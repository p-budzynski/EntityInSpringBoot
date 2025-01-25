package pl.kurs.relations.onetoonebidirectional.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "persons_bidirectional")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PersonBidirectional {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @OneToOne
    @JoinColumn(name = "person_details_id")
    private PersonDetailsBidirectional personDetailsBidirectional;

    public PersonBidirectional(String firstName, String lastName, PersonDetailsBidirectional personDetailsBidirectional) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personDetailsBidirectional = personDetailsBidirectional;
    }
}