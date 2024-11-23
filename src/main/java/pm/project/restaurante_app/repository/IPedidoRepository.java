package pm.project.restaurante_app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pm.project.restaurante_app.entity.Pedido;

import java.util.Optional;

public interface IPedidoRepository extends CrudRepository<Pedido, Integer> {
    @Query("SELECT P FROM Pedido P WHERE P.cliente.id=:idCli")
    Iterable<Pedido> devolverMisCompras(int idCli);
    @Query("SELECT P FROM Pedido P WHERE P.id=:idOrden AND P.cliente.id=:idCli")
    Optional<Pedido> findByIdAndClienteId(int idCli, int idOrden);
}