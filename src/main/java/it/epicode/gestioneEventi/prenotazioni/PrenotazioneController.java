package it.epicode.gestioneEventi.prenotazioni;

import it.epicode.gestioneEventi.responses.CreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/prenotazioni")
public class PrenotazioneController {
    private final PrenotazioneService prenotazioneService;
    private final PrenotazioneRepository prenotazioneRepository;

    //GET PER TUTTE LE PRENOTAZIONI
    @GetMapping("")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.OK)
    public List<Prenotazione> findAll(){return prenotazioneRepository.findAll();
    }

    //GET PER UNA SPECIFICA PRENOTAZIONE
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.OK)
    public Prenotazione findById(@PathVariable Long id){return prenotazioneService.findById(id);
    }

    //POST PER SALVARE UNA PRENOTAZIONE
    @PostMapping("")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateResponse save(@RequestBody PrenotazioneRequest request){
        return prenotazioneService.save(request);
    }

    //PUT PER MODIFICARE UNA PRENOTAZIONE
    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.OK)
    public Prenotazione update(@PathVariable Long id, @RequestBody PrenotazioneRequest request){
        return prenotazioneService.update(id, request);
    }

    //DELETE PER CANCELLARE UNA PRENOTAZIONE
    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Prenotazione delete(@PathVariable Long id){
        return prenotazioneService.delete(id);
    }

}
