package it.epicode.gestioneEventi.eventi;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventoRequest {
    @NotBlank(message = "Il campo titolo non può essere vuoto")
    private String titolo;
    @NotBlank(message = "Il campo descrizione non può essere vuoto")
    private String descrizione;
    @FutureOrPresent(message = "La data non puo essere nel passato")
    private LocalDate data;
    @NotBlank(message = "Il campo luogo non può essere vuoto")
    private String luogo;
    @Positive(message = "Il numero di posti disponibili deve essere maggiore di 1")
    @Min(value = 10, message = "Il numero di posti disponibili deve essere almeno 10")
    private int postiDisponibili;
}
