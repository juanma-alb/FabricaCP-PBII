package ar.edu.unlam.pb2.dominio;

import java.util.ArrayList;
import java.util.List;

public class Venta {
    private Cliente cliente;
    private List<CopaDelMundo> copas;

    public Venta(Cliente cliente) {
        this.cliente = cliente;
        this.copas = new ArrayList<>();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void agregarCopa(CopaDelMundo copa) {
        this.copas.add(copa);
    }

    public Double getTotalPrecioEstandar() {
        return copas.stream()
                .filter(copa -> copa instanceof CopaDelMundoEstandar)
                .mapToDouble(CopaDelMundo::getPrecio)
                .sum();
    }
    
    
    
    
}
