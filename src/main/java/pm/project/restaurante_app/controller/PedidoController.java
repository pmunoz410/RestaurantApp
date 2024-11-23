package pm.project.restaurante_app.controller;

import org.springframework.web.bind.annotation.*;
import pm.project.restaurante_app.entity.dto.GenerarPedidoDTO;
import pm.project.restaurante_app.entity.dto.PedidoConDetallesDTO;
import pm.project.restaurante_app.service.PedidoService;
import pm.project.restaurante_app.utils.GenericResponse;

import java.util.List;

@RestController
@RequestMapping("api/pedido")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    //LISTAR PEDIDOS CON DETALLES
    @GetMapping("/misPedidos/{idCli}")
    public GenericResponse<List<PedidoConDetallesDTO>> devolverMisComprasConDetalle(@PathVariable int idCli){
        return this.pedidoService.devolverMisCompras(idCli);
    }

    //GUARDAR PEDIDO
    @PostMapping
    public GenericResponse guardarPedido(@RequestBody GenerarPedidoDTO dto){
        return this.pedidoService.guardarPedido(dto);
    }
    //ANULAR PEDIDO
    @DeleteMapping("/{id}")
    public GenericResponse anularPedido(@PathVariable int id){
        return this.pedidoService.anularPedido(id);
    }

    /*
    //EXPORTAR PDF DE ORDEN
    @GetMapping("exportInvoice")
    public ResponseEntity<Resource> exportInvoice(@RequestParam int idCli, @RequestParam int idOrden){
        return this.pedidoService.exportInvoice(idCli, idOrden);
    }*/
}