package de.krummacker.jpa.repositories;

import de.krummacker.jpa.model.DeliveryNote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "deliverynotes", path = "deliverynotes")
public interface DeliveryNoteRepository extends CrudRepository<DeliveryNote, Long> {
}
