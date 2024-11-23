package pm.project.restaurante_app.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pm.project.restaurante_app.repository.IComidaRepository;
import pm.project.restaurante_app.utils.GenericResponse;

import static pm.project.restaurante_app.utils.Global.*;

@Service
@Transactional
public class ComidaService {
    private final IComidaRepository comidaRepository;

    public ComidaService(IComidaRepository comidaRepository) {
        this.comidaRepository = comidaRepository;
    }
    public GenericResponse listarPlatillosRecomendados(){
        return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, this.comidaRepository.listarPlatillosRecomendados());
    }
    public GenericResponse listarPlatillosPorCategoria(int idC){
        return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, this.comidaRepository.listarPlatillosPorCategoria(idC));
    }
}