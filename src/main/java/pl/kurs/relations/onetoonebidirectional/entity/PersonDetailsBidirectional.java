package pl.kurs.relations.onetoonebidirectional.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "person_details_bidirectional")
@NoArgsConstructor
@Getter
@Setter
public class PersonDetailsBidirectional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String pesel;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    @OneToOne(mappedBy = "personDetailsBidirectional")
    private PersonBidirectional personBidirectional;

    public PersonDetailsBidirectional(String pesel, String email, String phoneNumber) {
        this.pesel = pesel;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "PersonDetailsBidirectional{" +
                "id=" + id +
                ", pesel='" + pesel + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
