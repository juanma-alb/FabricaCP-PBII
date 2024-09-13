package ar.edu.unlam.pb2.dominio;

import ar.edu.unlam.pb2.enums.Colores;
import ar.edu.unlam.pb2.enums.Materiales;

public class CopaDelMundoPersonalizada extends CopaDelMundo {
    private Colores colorAtril;

    public CopaDelMundoPersonalizada(Long id, Materiales material, Double precioBase, Colores colorAtril) {
        super(id, material, precioBase);
        this.colorAtril = colorAtril;
    }

    public Colores getColorAtril() {
        return colorAtril;
    }
    

    
    public Double getPrecio() {
        double porcentajeAtril = 0.0;
        switch (colorAtril) {
            case CAOBA:
                porcentajeAtril = 0.05;
                break;
            case CEDRO:
                porcentajeAtril = 0.10;
                break;
            case ROBLE_OSCURO:
                porcentajeAtril = 0.15;
                break;
        }
        return getPrecioBase() * (1.15 + porcentajeAtril);
}
}
