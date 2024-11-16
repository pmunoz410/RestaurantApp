package pm.project.restaurante_app.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import pm.project.restaurante_app.entity.Cliente;
import pm.project.restaurante_app.service.ClienteService;
import pm.project.restaurante_app.utils.GenericResponse;

@RestController
@RequestMapping("api/cliente")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public GenericResponse save(@Valid @RequestBody Cliente client){
        return this.clienteService.save(client);
    }

    @PutMapping("/{id}")
    public GenericResponse update(@PathVariable int id, @Valid @RequestBody Cliente client){
        client.setId(id);
        return this.clienteService.save(client);
    }
}