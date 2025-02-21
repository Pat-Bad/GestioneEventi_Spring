package it.epicode.gestioneEventi.prenotazioni;

import it.epicode.gestioneEventi.auth.AppUser;
import it.epicode.gestioneEventi.eventi.Evento;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrenotazioneRequest {

    @NotBlank(message = "Il campo non può essere vuoto")
    private Long utenteId;
    @NotBlank(message = "Il campo non può essere vuoto")
    private Long eventoId;
}
