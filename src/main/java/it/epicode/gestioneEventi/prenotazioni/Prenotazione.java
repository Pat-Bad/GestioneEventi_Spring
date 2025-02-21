package it.epicode.gestioneEventi.prenotazioni;

import it.epicode.gestioneEventi.auth.AppUser;
import it.epicode.gestioneEventi.eventi.Evento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
    private AppUser utente;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private StatoPrenotazione stato;}
