package pl.kurs.relations.onetoone.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "person_details")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PersonDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String pesel;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    public PersonDetails(String pesel, String email, String phoneNumber) {
        this.pesel = pesel;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

}
