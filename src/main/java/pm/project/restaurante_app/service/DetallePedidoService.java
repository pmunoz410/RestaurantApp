package pm.project.restaurante_app.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pm.project.restaurante_app.entity.DetallePedido;
import pm.project.restaurante_app.repository.IDetallePedidoRepository;

@Service
@Transactional
public class DetallePedidoService {
    private final IDetallePedidoRepository detallePedidoRepository;

    public DetallePedidoService(IDetallePedidoRepository detallePedidoRepository) {
        this.detallePedidoRepository = detallePedidoRepository;
    }

    // Metodo para guardar detalles del pedido
    public void guardarDetalles(Iterable<DetallePedido> detalle){
        this.detallePedidoRepository.saveAll(detalle);
    }
}