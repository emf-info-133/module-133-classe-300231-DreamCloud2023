package doudix.ch.ctrlrest2.models;

import org.springframework.data.jpa.repository.JpaRepository;
import doudix.ch.ctrlrest2.models.Banissement;

public interface BanissementRepository extends JpaRepository<Banissement, Long> {
}
