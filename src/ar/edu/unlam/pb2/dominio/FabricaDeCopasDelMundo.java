package ar.edu.unlam.pb2.dominio;

import ar.edu.unlam.pb2.excepciones.ClienteDuplicadoException;
import ar.edu.unlam.pb2.excepciones.CopaDelMundoNoEncontradaException;

import java.util.*;

public class FabricaDeCopasDelMundo {
    private Map<Integer, Cliente> clientes = new TreeMap<>();
    private Map<Long, CopaDelMundo> copasDelMundo = new HashMap<>();
    private Set<Venta> ventas = new HashSet<>();

    public Boolean agregarCopaDelMundo(CopaDelMundo copaDelMundo) {
        return copasDelMundo.putIfAbsent(copaDelMundo.getId(), copaDelMundo) == null;
    	}

    public Boolean agregarCliente(Cliente cliente) throws ClienteDuplicadoException {
        if (clientes.containsKey(cliente.getDni())) {
        throw new ClienteDuplicadoException("el cliente ya existe");
        }
        clientes.put(cliente.getDni(), cliente);
        return true;
    }

    public List<CopaDelMundo> obtenerCopasDelMundoEstandar() {
        List<CopaDelMundo> copasEstandar = new ArrayList<>();
        for (CopaDelMundo copa : copasDelMundo.values()) {
            if (copa instanceof CopaDelMundoEstandar) {
                copasEstandar.add(copa);
            }
        }
        return copasEstandar;
        
        
        
        
    }

    public CopaDelMundo obtenerCopaDelMundoPorId(Long id) throws CopaDelMundoNoEncontradaException {
        CopaDelMundo copa = copasDelMundo.get(id);
        if (copa == null) {
            throw new CopaDelMundoNoEncontradaException("copa del Mundo no encontrada");
        	}
        return copa;
    }

    public void agregarCopaDelMundoEstandarAVentaDeCliente(Cliente clienteDeVenta, Long idCopaDelMundo,
            Integer cantidadAVender) throws CopaDelMundoNoEncontradaException {
        CopaDelMundoEstandar copa = (CopaDelMundoEstandar) obtenerCopaDelMundoPorId(idCopaDelMundo);
        Venta venta = obtenerVentaPorCliente(clienteDeVenta);
        for (int i = 0; i < cantidadAVender; i++) {
            venta.agregarCopa(copa);
        }
        ventas.add(venta);
    	}

    public void agregarCopaDelMundoPersonalizadaAVentaDeCliente(Cliente clienteDeVenta, Long idCopaDelMundo)
            throws CopaDelMundoNoEncontradaException {
        CopaDelMundoPersonalizada copa = (CopaDelMundoPersonalizada) obtenerCopaDelMundoPorId(idCopaDelMundo);
        copasDelMundo.remove(idCopaDelMundo);
        Venta venta = obtenerVentaPorCliente(clienteDeVenta);
        venta.agregarCopa(copa);
        ventas.add(venta);
    	}

    public Double obtenerPrecioDeCopaDelMundoPersonalizada(Long id) throws CopaDelMundoNoEncontradaException {
        CopaDelMundoPersonalizada copa = (CopaDelMundoPersonalizada) obtenerCopaDelMundoPorId(id);
        return copa.getPrecio();
    }

    public Map<Cliente, Double> obtenerTotalDePrecioDeCopasDelMundoEstandarVendidasAClientesOrdenadasPorCliente() {
        Map<Cliente, Double> totalPorCliente = new TreeMap<>();
        for (Venta venta : ventas) {
            totalPorCliente.put(venta.getCliente(), venta.getTotalPrecioEstandar());
        }
        return totalPorCliente;
    	}

    private Venta obtenerVentaPorCliente(Cliente cliente) {
        for (Venta venta : ventas) {
            if (venta.getCliente().equals(cliente)) {
                return venta;
            }
        }
        return new Venta(cliente);
    	}
    
    
}


