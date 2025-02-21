package it.epicode.gestioneEventi.prenotazioni;

import it.epicode.gestioneEventi.eventi.Evento;
import it.epicode.gestioneEventi.utenti.Utente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private LocalDate data;
    private StatoPrenotazione stato;
    @ManyToOne
    @JoinColumn(name="id_utente")
    private Utente utente;
    @ManyToOne
    @JoinColumn(name="id_evento")
    private Evento evento;

    }

