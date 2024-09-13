package ar.edu.unlam.pb2.dominio;

import ar.edu.unlam.pb2.enums.Colores;
import ar.edu.unlam.pb2.enums.Materiales;
import ar.edu.unlam.pb2.excepciones.ClienteDuplicadoException;
import ar.edu.unlam.pb2.excepciones.CopaDelMundoNoEncontradaException;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;

public class FabricaCopasDelMundoTest {
    private FabricaDeCopasDelMundo fabrica;

    @Before
    public void setUp() {
        fabrica = new FabricaDeCopasDelMundo();
    }
    

    @Test
    public void dadoQueExisteUnaFabricaDeCopasDelMundoSePuedeAgregarUnaCopaDelMundoEstandar() {
        CopaDelMundoEstandar copa = new CopaDelMundoEstandar(1L, Materiales.PLASTICO, 100.0);
        assertTrue(fabrica.agregarCopaDelMundo(copa));
    }
    
    

    @Test
    public void dadoQueExisteUnaFabricaDeCopasDelMundoSePuedeAgregarUnaCopaDelMundoPersonalizada() {
        CopaDelMundoPersonalizada copa = new CopaDelMundoPersonalizada(2L, Materiales.YESO, 200.0, Colores.CAOBA);
        assertTrue(fabrica.agregarCopaDelMundo(copa));
    }
    

    @Test(expected = ClienteDuplicadoException.class)
    public void dadoQueExisteUnaFabricaDeCopasDelMundoAlAgregarUnClienteExistenteSeLanzaUnaClienteDuplicadoException() throws ClienteDuplicadoException {
        Cliente cliente = new Cliente(12345678, "juan", "alb");
        fabrica.agregarCliente(cliente);
        fabrica.agregarCliente(cliente);  
    }

    
    @Test
    public void dadoQueExisteUnaFabricaQuePoseeCopasDelMundoSePuedenObtenerLasCopasDelMundoEstandar() {
        CopaDelMundoEstandar copa1 = new CopaDelMundoEstandar(1L, Materiales.PLASTICO, 100.0);
        CopaDelMundoEstandar copa2 = new CopaDelMundoEstandar(2L, Materiales.YESO, 150.0);
        fabrica.agregarCopaDelMundo(copa1);
        fabrica.agregarCopaDelMundo(copa2);

        List<CopaDelMundo> copasEstandar = fabrica.obtenerCopasDelMundoEstandar();
        assertEquals(2, copasEstandar.size());
    }

    @Test
    public void dadoQueExisteUnaFabricaDeCopasDelMundoConCopasDelMundoPuedoObtenerUnaCopaDelMundoPorSuId() throws CopaDelMundoNoEncontradaException {
        CopaDelMundoEstandar copa = new CopaDelMundoEstandar(1L, Materiales.PLASTICO, 100.0);
        fabrica.agregarCopaDelMundo(copa);

        CopaDelMundo obtenida = fabrica.obtenerCopaDelMundoPorId(1L);
        assertNotNull(obtenida);
        assertEquals(copa, obtenida);
        
    }

    
    
    @Test
    public void dadoQueExisteUnaFabricaDeCopasDelMundoConCopasDelMundoAlAgregarCincoCopasDelMundoAUnaVentaDeCopasDelMundoEstandarParaUnClienteSeDescuentanCincoUnidadesDelStockDeCopasDelMundoEstandar() throws CopaDelMundoNoEncontradaException, ClienteDuplicadoException {
        CopaDelMundoEstandar copa = new CopaDelMundoEstandar( 1L, Materiales.PLASTICO, 100.0);
        fabrica.agregarCopaDelMundo(copa);
        fabrica.agregarCopaDelMundo(copa);
        fabrica.agregarCopaDelMundo(copa);
        fabrica.agregarCopaDelMundo(copa);
        fabrica.agregarCopaDelMundo(copa);

        Cliente cliente = new Cliente(12345678, "juan", "alb");
        fabrica.agregarCliente(cliente);

        fabrica.agregarCopaDelMundoEstandarAVentaDeCliente(cliente, 1L, 5);
        assertEquals(0, fabrica.obtenerCopasDelMundoEstandar().size());
    
    
    }
    
    

    @Test
    public void dadoQueExisteUnaFabricaDeCopasDelMundoConCopasDelMundoAlAgregarUnaVentaDeCopasDelMundoPersonalizadaParaUnClienteSeRemueveLaCopaDelMundoPersonalizadaDeLaFabrica() throws CopaDelMundoNoEncontradaException, ClienteDuplicadoException {
        CopaDelMundoPersonalizada copa = new CopaDelMundoPersonalizada(2L, Materiales.YESO, 200.0, Colores.CAOBA);
        fabrica.agregarCopaDelMundo(copa);

        Cliente cliente = new Cliente(12345678, "juan", "alb");
        fabrica.agregarCliente(cliente);

        fabrica.agregarCopaDelMundoPersonalizadaAVentaDeCliente(cliente, 2L);
        assertEquals(0, fabrica.obtenerCopasDelMundoEstandar().size());
    }

    @Test
    public void dadoQueExisteUnaFabricaDeCopasDelMundoConCopasDelMundoPersonalizadasSePuedeObtenerElPrecioDeUnaCopaDelMundoPersonalizada() throws CopaDelMundoNoEncontradaException {
        CopaDelMundoPersonalizada copa = new CopaDelMundoPersonalizada(2L, Materiales.YESO, 200.0, Colores.CAOBA);
        fabrica.agregarCopaDelMundo(copa);

        Double precio = fabrica.obtenerPrecioDeCopaDelMundoPersonalizada(2L);
        assertEquals(copa.getPrecio(), precio);
    
    }

    @Test
    public void dadoQueExisteUnaFabricaDeCopasDelMundoConVentasDeCopasDelMundoEstandarYPersonalizadasVendidasAClientesSePuedeObtenerUnMapaConClaveClienteYTotalDeVentasDeCopasEstandarOrdenadoPorCliente() throws CopaDelMundoNoEncontradaException, ClienteDuplicadoException {
        CopaDelMundoEstandar copa1 = new CopaDelMundoEstandar(1L, Materiales.PLASTICO, 100.0);
        CopaDelMundoEstandar copa2 = new CopaDelMundoEstandar(2L, Materiales.YESO, 150.0);
        fabrica.agregarCopaDelMundo(copa1);
        fabrica.agregarCopaDelMundo(copa2);

        Cliente cliente1 = new Cliente(12345678, "juan", "alb");
        Cliente cliente2 = new Cliente(87654321, "julieta", "gomez");
        
        fabrica.agregarCliente(cliente1);
        fabrica.agregarCliente(cliente2);

        fabrica.agregarCopaDelMundoEstandarAVentaDeCliente(cliente1, 1L, 1);
        fabrica.agregarCopaDelMundoEstandarAVentaDeCliente(cliente2, 2L, 1);

        Map<Cliente, Double> totalPorCliente = fabrica.obtenerTotalDePrecioDeCopasDelMundoEstandarVendidasAClientesOrdenadasPorCliente();
        assertEquals(2, totalPorCliente.size());
        assertEquals(Double.valueOf(120.0), totalPorCliente.get(cliente1));
        assertEquals(Double.valueOf(180.0), totalPorCliente.get(cliente2));
        
        
        
}
    
}
