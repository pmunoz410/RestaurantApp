package pm.project.restaurante_app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pm.project.restaurante_app.entity.Usuario;

import java.util.Optional;

public interface IUsuarioRepository extends CrudRepository<Usuario, Integer> {
    @Query("SELECT u FROM Usuario u WHERE u.email=:correo AND u.clave=:password")
    Optional<Usuario> login(String correo, String password);
}