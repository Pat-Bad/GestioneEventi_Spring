package it.epicode.gestioneEventi.prenotazioni;

import it.epicode.gestioneEventi.eventi.Evento;
import it.epicode.gestioneEventi.eventi.EventoRepository;
import it.epicode.gestioneEventi.exceptions.PostiEsauritiException;
import it.epicode.gestioneEventi.responses.CreateResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import static it.epicode.gestioneEventi.prenotazioni.StatoPrenotazione.CONFERMATA;

@Service
@Validated
@RequiredArgsConstructor
public class PrenotazioneService {
    private final PrenotazioneRepository prenotazioneRepository;
    private final EventoRepository eventoRepository;

    //METODO SAVE
    @Transactional
    public CreateResponse save(@Valid PrenotazioneRequest request) {
        Evento evento = request.getEvento();
        if (evento.getPostiDisponibili() == 0)
            throw new PostiEsauritiException("Posti esauriti!");
        Prenotazione prenotazione = new Prenotazione();
        BeanUtils.copyProperties(request, prenotazione);
        prenotazione.setEvento(evento);
        prenotazioneRepository.save(prenotazione);

        //DEVO GESTIRE I POSTI DISPONIBILI, DEVONO DIMINUIRE A OGNI PRENOTAZIONE
        evento.setPostiDisponibili(evento.getPostiDisponibili() - 1);
        eventoRepository.save(evento);

        CreateResponse response = new CreateResponse();
        response.setId(prenotazione.getId());
        return response;
    }

    //METODO UPDATE
    @Transactional
    public Prenotazione update(Long id, @Valid PrenotazioneRequest request) {
        if(!prenotazioneRepository.existsById(id))
            throw new EntityNotFoundException("Prenotazione con id " + id + "non trovata!");
        Prenotazione prenotazione = prenotazioneRepository.findById(id).get();
        BeanUtils.copyProperties(request,prenotazione, "id");
        return prenotazioneRepository.save(prenotazione);
    }

    //METODO DELETE
    @Transactional
    public Prenotazione delete(Long id) {
        if(!prenotazioneRepository.existsById(id))
            throw new EntityNotFoundException("Prenotazione con id " + id + "non trovata!");
        Prenotazione prenotazione = prenotazioneRepository.findById(id).get();

        //SI PUO' CANCELLARE SOLO SE ERA CONFERMATA
        if (prenotazione.getStato() == CONFERMATA) {
            Evento evento = prenotazione.getEvento();
            evento.setPostiDisponibili(evento.getPostiDisponibili() + 1);
            eventoRepository.save(evento);
        }

        prenotazioneRepository.delete(prenotazione);
        return prenotazione;
    }

    public Prenotazione findById(Long id) {
        return prenotazioneRepository.findById(id).get();
    }
}
