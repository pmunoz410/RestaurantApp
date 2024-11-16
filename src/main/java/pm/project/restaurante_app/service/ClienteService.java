package pm.project.restaurante_app.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pm.project.restaurante_app.entity.Cliente;
import pm.project.restaurante_app.repository.IClienteRepository;
import pm.project.restaurante_app.utils.GenericResponse;

import java.util.Optional;

import static pm.project.restaurante_app.utils.Global.*;

@Service
@Transactional
public class ClienteService {
    private final IClienteRepository clienteRepository;

    public ClienteService(IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public GenericResponse save(Cliente c){
        Optional<Cliente> opt = this.clienteRepository.findById(c.getId());
        int idf = opt.isPresent() ? opt.get().getId() : 0;
        if(idf == 0){
            if(clienteRepository.existByDoc(c.getNumDoc().trim()) == 1){
                return new GenericResponse(TIPO_RESULT, RPTA_WARNING, "Lo sentimos: " +
                        "Ya existe un cliente con ese mismo numero de documento, " +
                        "y si el problema persiste comuniquese con el soporte técnico", null);
            }else{
                //Guarda
                c.setId(idf);
                return new GenericResponse(TIPO_DATA, RPTA_OK, "Cliente registrado correctamente", this.clienteRepository.save(c));
            }
        }else{
            //Actualizar Registro
            if(clienteRepository.existByDocForUpdate(c.getNumDoc().trim(), c.getId()) == 1){
                return new GenericResponse(TIPO_RESULT, RPTA_WARNING, "Error: Ya existe un cliente con esos mismos datos" +
                        "verifique e intente de nuevo", null);
            }else{
                //Actualiza
                c.setId(idf);
                return new GenericResponse(TIPO_DATA, RPTA_OK, "Datos del cliente actualizado", this.clienteRepository.save(c));
            }
        }
    }
}