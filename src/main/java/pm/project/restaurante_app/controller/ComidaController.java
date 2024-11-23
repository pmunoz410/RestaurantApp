package pm.project.restaurante_app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pm.project.restaurante_app.service.ComidaService;
import pm.project.restaurante_app.utils.GenericResponse;

@RestController
@RequestMapping("api/comida")
public class ComidaController {
    private final ComidaService comidaService;

    public ComidaController(ComidaService comidaService) {
        this.comidaService = comidaService;
    }

    @GetMapping
    public GenericResponse listarPlatillosRecomendados(){
        return this.comidaService.listarPlatillosRecomendados();
    }
    @GetMapping("/{idC}")
    public GenericResponse listarPlatillosPorCategoria(@PathVariable int idC){
        return this.comidaService.listarPlatillosPorCategoria(idC);
    }
}