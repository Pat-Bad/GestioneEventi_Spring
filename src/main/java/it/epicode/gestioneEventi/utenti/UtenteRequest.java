package it.epicode.gestioneEventi.utenti;

import it.epicode.gestioneEventi.auth.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtenteRequest {
    @NotBlank(message = "Il campo username non può essere vuoto")
    private String username;
    @NotNull
    private Role ruolo;
}
