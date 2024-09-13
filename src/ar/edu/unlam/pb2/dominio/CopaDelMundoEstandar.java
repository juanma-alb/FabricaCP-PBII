package ar.edu.unlam.pb2.dominio;

import ar.edu.unlam.pb2.enums.Materiales;

public class CopaDelMundoEstandar extends CopaDelMundo {

    public CopaDelMundoEstandar(Long id, Materiales material, Double precioBase) {
        super(id, material, precioBase);
    }

    @Override
    public Double getPrecio() {
        return getPrecioBase() * 1.20;
}
}
