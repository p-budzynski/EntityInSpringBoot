package pl.kurs.relations.onetoonebidirectional.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.kurs.relations.onetoonebidirectional.entity.PersonBidirectional;

@Repository
@Transactional
@RequiredArgsConstructor
public class PersonBidirectionalDao {

    private final EntityManager entityManager;

    public PersonBidirectional get(Long id) {
        return entityManager.find(PersonBidirectional.class, id);
    }

    public void save(PersonBidirectional personBidirectional) {
        if (personBidirectional.getPersonDetailsBidirectional() != null && personBidirectional.getPersonDetailsBidirectional().getId() == null) {
            entityManager.persist(personBidirectional.getPersonDetailsBidirectional());
        }
        entityManager.persist(personBidirectional);
    }

    public void update(PersonBidirectional personBidirectional) {
        if (personBidirectional.getPersonDetailsBidirectional() != null) {
            entityManager.merge(personBidirectional.getPersonDetailsBidirectional());
        }
        entityManager.merge(personBidirectional);
    }

    public void delete(Long id) {
        PersonBidirectional personBidirectional = get(id);
        if (personBidirectional != null) {
            entityManager.remove(personBidirectional);
            entityManager.remove(personBidirectional.getPersonDetailsBidirectional());
        }
    }

    public void delete(PersonBidirectional personBidirectional) {
        PersonBidirectional toDelete = entityManager.merge(personBidirectional);
        entityManager.remove(toDelete);
        entityManager.remove(toDelete.getPersonDetailsBidirectional());
    }

}
