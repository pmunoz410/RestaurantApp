package pm.project.restaurante_app.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pm.project.restaurante_app.repository.ICategoriaRepository;
import pm.project.restaurante_app.utils.GenericResponse;

import static pm.project.restaurante_app.utils.Global.*;

@Service
@Transactional
public class CategoriaService {
    private final ICategoriaRepository categoriaRepository;

    public CategoriaService(ICategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public GenericResponse listarCategoriasActivas() {
        return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, this.categoriaRepository.listarCategoriasActivas());
    }
}