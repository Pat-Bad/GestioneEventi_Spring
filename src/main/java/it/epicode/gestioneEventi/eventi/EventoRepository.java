package it.epicode.gestioneEventi.eventi;


import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    boolean existsByData(LocalDate dataEvento);

    int getPostiDisponibili(Evento evento);
}
