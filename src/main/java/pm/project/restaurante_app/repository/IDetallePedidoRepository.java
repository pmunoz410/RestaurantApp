package pm.project.restaurante_app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pm.project.restaurante_app.entity.DetallePedido;

public interface IDetallePedidoRepository extends CrudRepository<DetallePedido, Integer> {
    @Query("SELECT DP FROM DetallePedido DP WHERE DP.pedido.id=:idP")
    Iterable<DetallePedido> findByPedido(int idP);
    @Query(value = "SELECT SUM(dp.cantidad * dp.precio) AS \"Total\" FROM detalle_pedido dp JOIN pedido p\n"
            + "ON p.id = dp.pedido_id\n"
            + "WHERE p.cliente_id =:idCli", nativeQuery = true)
    Double totalByIdCustomer(int idCli);
}