package pm.project.restaurante_app.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pm.project.restaurante_app.entity.Categoria;

public interface ICategoriaRepository extends CrudRepository<Categoria, Integer> {
    @Query("SELECT C FROM Categoria C WHERE C.vigencia = true")
    Iterable<Categoria> listarCategoriasActivas();

    /*
    @Query("SELECT c FROM Categoria c WHERE c.id = :id")
    Categoria findById(@Param("id") int id);

    @Modifying
    @Query("update Categoria c set c.vigencia = false where c.id = :id")
    void desactivar(@Param("id") Integer id);

    @Modifying
    @Query("update Categoria c set c.vigencia = true where c.id = :id")
    void activar(@Param("id") Integer id);
    */
}