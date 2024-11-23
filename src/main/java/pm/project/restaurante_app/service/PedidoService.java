package pm.project.restaurante_app.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pm.project.restaurante_app.entity.DetallePedido;
import pm.project.restaurante_app.entity.Pedido;
import pm.project.restaurante_app.entity.dto.GenerarPedidoDTO;
import pm.project.restaurante_app.entity.dto.PedidoConDetallesDTO;
import pm.project.restaurante_app.repository.IComidaRepository;
import pm.project.restaurante_app.repository.IDetallePedidoRepository;
import pm.project.restaurante_app.repository.IPedidoRepository;
import pm.project.restaurante_app.utils.GenericResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static pm.project.restaurante_app.utils.Global.*;

@Service
@Transactional
public class PedidoService {
    private final IPedidoRepository pedidoRepository;

    private final IDetallePedidoRepository detallePedidoRepository;

    private final DetallePedidoService detallePedidoService;

    private final IComidaRepository comidaRepository;

    public PedidoService(IPedidoRepository pedidoRepository, IDetallePedidoRepository detallePedidoRepository, DetallePedidoService detallePedidoService, IComidaRepository comidaRepository) {
        this.pedidoRepository = pedidoRepository;
        this.detallePedidoRepository = detallePedidoRepository;
        this.detallePedidoService = detallePedidoService;
        this.comidaRepository = comidaRepository;
    }

    // Metodo para devolver los pedidos con su respectivo detalle
    public GenericResponse<List<PedidoConDetallesDTO>> devolverMisCompras(int idCli) {
        final List<PedidoConDetallesDTO> dtos = new ArrayList<>();
        final Iterable<Pedido> pedidos = pedidoRepository.devolverMisCompras(idCli);
        pedidos.forEach(p -> {
            dtos.add(new PedidoConDetallesDTO(p, detallePedidoRepository.findByPedido(p.getId())));
        });
        return new GenericResponse(OPERACION_CORRECTA, RPTA_OK, "Petición Encontrada", dtos);
    }

    // Metodo para guardar el pedido
    public GenericResponse guardarPedido(GenerarPedidoDTO dto) {
        Date date = new Date();
        dto.getPedido().setFechaCompra(new java.sql.Date(date.getTime()));
        dto.getPedido().setAnularPedido(false);
        dto.getPedido().setMonto(dto.getPedido().getMonto());
        dto.getPedido().setCliente(dto.getCliente());
        this.pedidoRepository.save(dto.getPedido());
        for (DetallePedido dp : dto.getDetallePedidos()) {
            dp.setPedido(dto.getPedido());
            this.comidaRepository.actualizarStock(dp.getCantidad(), dp.getComida().getId());
        }
        //Llamamos al DetallePedidoService
        this.detallePedidoService.guardarDetalles(dto.getDetallePedidos());
        return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, dto);
    }

    // Metodo para anular el pedido
    public GenericResponse anularPedido(int id) {
        Pedido p = this.pedidoRepository.findById(id).orElse(new Pedido());
        if (p.getId() != 0) {
            p.setAnularPedido(true);
//            this.restablecerStock(id);
            this.pedidoRepository.save(p);
            return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, p);
        } else {
            return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_ERRONEA, "El pedido que desea anular no es válido");
        }
    }


    private void restablecerStock(final int pedidoId) {
        Iterable<DetallePedido> detalles = this.detallePedidoRepository.findByPedido(pedidoId);
        for (DetallePedido dp : detalles) {
            comidaRepository.actualizarStock(dp.getCantidad(), dp.getComida().getId());
        }
    }

    /*
    @NotNull
    public ResponseEntity<Resource> exportInvoice(int idCli, int idOrden) {
        Optional<Pedido> optPedido = this.repository.findByIdAndClienteId(idCli, idOrden);
        Double rpta = this.detallePedidoRepository.totalByIdCustomer(idCli);
        if (optPedido.isPresent()) {
            try {
                final Pedido pedido = optPedido.get();
                final File file = ResourceUtils.getFile("classpath:exportInvoice.jasper");
                final File imgLogo = ResourceUtils.getFile("classpath:images/logoCevicheria.png");
                final JasperReport report = (JasperReport) JRLoader.loadObject(file);

                final HashMap<String, Object> parameters = new HashMap<>();
                parameters.put("nombreCliente", pedido.getCliente().getNombreCompletoCiente());
                parameters.put("imgLogo", new FileInputStream(imgLogo));
                parameters.put("total", rpta);
                parameters.put("dsInvoice", new JRBeanCollectionDataSource((Collection<?>) this.detallePedidoRepository.findByPedido(idOrden)));

                JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
                byte[] reporte = JasperExportManager.exportReportToPdf(jasperPrint);
                String sdf = (new SimpleDateFormat("dd/MM/yyyy")).format(new Date());
                StringBuilder stringBuilder = new StringBuilder().append("InvoicePDF:");
                ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                        .filename(stringBuilder.append(pedido.getId())
                                .append("generateDate:")
                                .append(sdf)
                                .append(".pdf")
                                .toString())
                        .build();
                HttpHeaders headers = new HttpHeaders();
                headers.setContentDisposition(contentDisposition);
                return ResponseEntity.ok().contentLength((long) reporte.length)
                        .contentType(MediaType.APPLICATION_PDF)
                        .headers(headers).body(new ByteArrayResource(reporte));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return ResponseEntity.noContent().build(); //No se encontro el reporte
        }
        return null;
    }*/
}