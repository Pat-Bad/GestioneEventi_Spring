package it.epicode.gestioneEventi.utenti;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Validated
public class UtenteService {
    private final UtenteRepository utenteRepository;

    @Transactional
    public Utente update(Long id, @Valid UtenteRequest request) {
        if(!utenteRepository.existsById(id))
            throw new EntityNotFoundException("Utente con id " + id + "non trovato!");
        Utente utente = utenteRepository.findById(id).get();
        utente.setUsername(request.getUsername());
        return utenteRepository.save(utente);
    }}
