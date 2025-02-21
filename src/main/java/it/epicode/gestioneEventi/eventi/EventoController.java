package it.epicode.gestioneEventi.eventi;

import it.epicode.gestioneEventi.responses.CreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/eventi")
@PreAuthorize("isAuthenticated()")
public class EventoController {
    private final EventoService eventoService;
    private final EventoRepository eventoRepository;

    //GET PER TUTTI GLI EVENTI
    @GetMapping("")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.OK)
    public List<Evento> findAll(){return eventoRepository.findAll();
    }

    //GET PER UNO SPECIFICO EVENTO
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.OK)
    public Evento findById(@PathVariable Long id){return eventoService.findById(id);
    }

    //POST PER SALVARE UN EVENTO
    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_ORGANIZZATORE')")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateResponse save(@RequestBody EventoRequest request){
        return eventoService.save(request);
    }

    //PUT PER MODIFICARE UN EVENTO
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ORGANIZZATORE')")
    @ResponseStatus(HttpStatus.OK)
    public Evento update(@PathVariable Long id, @RequestBody EventoRequest request){
        return eventoService.update(id, request);
    }

    //DELETE PER CANCELLARE UN EVENTO
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ORGANIZZATORE')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Evento delete(@PathVariable Long id){
        return eventoService.delete(id);
    }


}
