package it.epicode.gestioneEventi.eventi;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    boolean existsByData(LocalDate dataEvento);

}
