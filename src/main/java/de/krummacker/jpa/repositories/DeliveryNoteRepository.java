package de.krummacker.jpa.repositories;

import de.krummacker.jpa.model.DeliveryNote;
import org.springframework.data.repository.CrudRepository;

public interface DeliveryNoteRepository extends CrudRepository<DeliveryNote, Long> {
}
