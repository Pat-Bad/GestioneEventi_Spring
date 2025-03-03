package it.epicode.gestioneEventi.eventi;

import it.epicode.gestioneEventi.auth.AppUser;
import it.epicode.gestioneEventi.auth.AppUserRepository;
import it.epicode.gestioneEventi.responses.CreateResponse;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class EventoService {
    private final EventoRepository eventoRepository;
    private final AppUserRepository appUserRepository;

    //METODO SAVE
    @Transactional
    public CreateResponse save(@Valid EventoRequest request) {
            LocalDate dataEvento = request.getData();
            if (eventoRepository.existsByData(dataEvento)) {
                throw new EntityExistsException("In questa data è già prenotato un evento");
            }

            AppUser organizzatore = appUserRepository.findById(request.getOrganizzatoreId())
                    .orElseThrow(() -> new EntityNotFoundException("Organizzatore non trovato"));

            Evento evento = new Evento();
            BeanUtils.copyProperties(request, evento);
            evento.setOrganizzatore(organizzatore);
            eventoRepository.save(evento);

            CreateResponse response = new CreateResponse();
            response.setId(evento.getId());
            return response;

    }
    private AppUser getAuthenticatedUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return appUserRepository.findByUsername(username).get();
    }

    //METODO UPDATE
    @Transactional
    public Evento update(Long id, @Valid EventoRequest request) {
        AppUser organizzatore = appUserRepository.findById(request.getOrganizzatoreId()).get();
        if(!eventoRepository.existsById(id))
            throw new EntityNotFoundException("Evento con id " + id + "non trovato!");
        Evento evento = eventoRepository.findById(id).get();
        BeanUtils.copyProperties(request,evento, "id");
        return eventoRepository.save(evento);
    }

    //METODO DELETE
    @Transactional
    public Evento delete(Long id) {
        if(!eventoRepository.existsById(id))
            throw new EntityNotFoundException("Evento con id " + id + "non trovato!");
        Evento evento = eventoRepository.findById(id).get();
        eventoRepository.delete(evento);
        return evento;
    }


    public Evento findById(Long id) {
            return eventoRepository.findById(id).get();
    }
}
