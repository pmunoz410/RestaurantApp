package pm.project.restaurante_app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pm.project.restaurante_app.service.CategoriaService;
import pm.project.restaurante_app.utils.GenericResponse;

@RestController
@RequestMapping("api/categoria")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public GenericResponse listarCategoriasActivas() {
        return this.categoriaService.listarCategoriasActivas();
    }
}