package pm.project.restaurante_app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pm.project.restaurante_app.entity.Cliente;

public interface IClienteRepository extends CrudRepository<Cliente, Integer> {
    @Query(value = "(SELECT EXISTS(SELECT id FROM cliente WHERE num_doc=:dni))", nativeQuery = true)
    int existByDoc(String dni);
    @Query(value = "SELECT EXISTS(SELECT C.* FROM cliente C WHERE C.num_doc=:dni AND NOT (C.id=:id))", nativeQuery = true)
    int existByDocForUpdate(String dni, int id);
}