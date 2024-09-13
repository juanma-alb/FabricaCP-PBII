package ar.edu.unlam.pb2.dominio;

import ar.edu.unlam.pb2.enums.Materiales;
import java.util.Objects;

public abstract class CopaDelMundo {
    private Long id;
    private Materiales material;
    private Double precioBase;

    public CopaDelMundo(Long id, Materiales material, Double precioBase) {
        this.id = id;
        this.material = material;
        this.precioBase = precioBase;
    }

    public Long getId() {
        return id;
    }


    
    public Materiales getMaterial() {
        return material;
    }

    public Double getPrecioBase() {
        return precioBase;
    }

    public abstract Double getPrecio();

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CopaDelMundo other = (CopaDelMundo) obj;
        return Objects.equals(id, other.id);
    }
}
