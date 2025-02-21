package it.epicode.gestioneEventi.utenti;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Validated
public class UtenteService {
    private final UtenteRepository utenteRepository;


}
