package it.epicode.gestioneEventi.prenotazioni;

import it.epicode.gestioneEventi.auth.AppUser;
import jakarta.validation.constraints.FutureOrPresent;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {

    List<Prenotazione> findByUtente(AppUser utente);
    List<Prenotazione> findByUtenteAndStato(AppUser utente, StatoPrenotazione statoPrenotazione);
    boolean existsByDataAndStato(@FutureOrPresent(message = "La data deve essere futura o quella corrente")
                                 LocalDate data, StatoPrenotazione statoPrenotazione);


}
