package pe.trujillo.ropa.TiendaRopaOnline.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.trujillo.ropa.TiendaRopaOnline.Model.DetallePedido;
import pe.trujillo.ropa.TiendaRopaOnline.Repository.IDetallePedidoRepository;

@Service
public class DetallePedidoService {

    @Autowired
    private IDetallePedidoRepository detallePedidoRepository;

    public void guardarDetallePedido(DetallePedido detallePedido) {
        detallePedidoRepository.save(detallePedido);
    }
}
