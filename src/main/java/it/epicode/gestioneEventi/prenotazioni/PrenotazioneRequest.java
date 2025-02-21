package it.epicode.gestioneEventi.prenotazioni;

import it.epicode.gestioneEventi.eventi.Evento;
import it.epicode.gestioneEventi.utenti.Utente;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrenotazioneRequest {
    @FutureOrPresent(message = "La data deve essere futura o quella corrente")
    private LocalDate data;
    @NotNull(message = "Il campo stato non può essere vuoto")
    private StatoPrenotazione stato;
    @NotNull
    private Utente utente;
    @NotNull
    private Evento evento;
}
