package it.epicode.gestioneEventi.utenti;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/utente")
public class UtenteController {
    private final UtenteService utenteService;
    private final UtenteRepository utenteRepository;

    //VOGLIO SOLO LA PUT PER FARGLI MODIFICARE LO USERNAME
    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.OK)
    public Utente update(@PathVariable Long id, @Valid UtenteRequest request){
        return utenteService.update(id, request);
}}
