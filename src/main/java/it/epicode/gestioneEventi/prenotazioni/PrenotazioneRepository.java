package it.epicode.gestioneEventi.prenotazioni;


import it.epicode.gestioneEventi.utenti.Utente;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {

    List<Prenotazione> findByUtente(Utente utente);
    List<Prenotazione> findByUtenteAndStato(Utente utente, StatoPrenotazione statoPrenotazione);
    boolean existsByDataAndStato(@FutureOrPresent(message = "La data deve essere futura o quella corrente")
                                 LocalDate data, StatoPrenotazione statoPrenotazione);


}
