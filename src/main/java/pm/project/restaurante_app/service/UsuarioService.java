package pm.project.restaurante_app.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pm.project.restaurante_app.entity.Usuario;
import pm.project.restaurante_app.repository.IUsuarioRepository;
import pm.project.restaurante_app.utils.GenericResponse;

import java.util.Optional;

import static pm.project.restaurante_app.utils.Global.*;

@Service
@Transactional
public class UsuarioService {

    private final IUsuarioRepository usuarioRepository;

    public UsuarioService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public GenericResponse<Usuario> login(String email, String password) {
        Optional<Usuario> optionalUsuario = this.usuarioRepository.login(email, password);
        if (optionalUsuario.isPresent()) {
            return new GenericResponse<Usuario>(TIPO_AUTH, RPTA_OK, "Haz iniciado sesión correctamente.", optionalUsuario.get());
        } else {
            return new GenericResponse<Usuario>(TIPO_AUTH, RPTA_WARNING, "Las credenciales son incorrectas.", new Usuario());
        }
    }

    public GenericResponse guardarUsuario(Usuario user){
        Optional<Usuario> optionalUsuario = this.usuarioRepository.findById(user.getId());
        int idf = optionalUsuario.isPresent() ? optionalUsuario.get().getId() : 0;
        if(idf == 0){
            return new GenericResponse(TIPO_DATA, RPTA_OK, "Usuario Registrado Correctamente", this.usuarioRepository.save(user));
        }else{
            return new GenericResponse(TIPO_DATA, RPTA_OK, "Datos del usuario actualizados", this.usuarioRepository.save(user));
        }
    }
}