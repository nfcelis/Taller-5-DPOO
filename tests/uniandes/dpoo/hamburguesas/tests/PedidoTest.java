package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class PedidoTest {

    private Pedido pedido;

    @BeforeEach
    void setup() throws Exception {
        pedido = new Pedido("Juan Perez", "Calle 123");
        pedido.resetnumeropedidos();
        
        ProductoMenu hamburguesa = new ProductoMenu("Hamburguesa de Res", 20000);
        ProductoMenu papas = new ProductoMenu("Papas Fritas", 5000);
        ProductoMenu gaseosa = new ProductoMenu("Gaseosa", 3000);

        pedido.agregarProducto(hamburguesa);
        pedido.agregarProducto(papas);
        pedido.agregarProducto(gaseosa);
    }

    @AfterEach
    void tearDown() throws Exception {
        pedido = null;
    }

    @Test
    void testGetIdPedido() {
        int idEsperado = 1; // Supongamos que este es el primer pedido creado
        assertEquals(idEsperado, pedido.getIdPedido(), "El ID del pedido no corresponde con el esperado.");
    }

    @Test
    void testGetNombreCliente() {
        assertEquals("Juan Perez", pedido.getNombreCliente(), "El nombre del cliente no corresponde con el esperado.");
    }

    @Test
    void testGetPrecioTotalPedido() {
        int precioTotalEsperado = 33320;
        assertEquals(precioTotalEsperado, pedido.getPrecioTotalPedido(), "El precio total del pedido no corresponde con el esperado.");
    }

    @Test
    void testGetPrecioNetoPedido() {
        int precioNetoEsperado = 28000;
        assertEquals(precioNetoEsperado, pedido.getPrecioNetoPedido(), "El precio neto del pedido no corresponde con el esperado.");
    }

    @Test
    void testGetPrecioIVAPedido() {
        double IVA = 0.19;
        int precioConIVAEsperado = (int) (28000 * (IVA));
        assertEquals(precioConIVAEsperado, pedido.getPrecioIVAPedido(), "El precio con IVA del pedido no corresponde con el esperado.");
    }

    @Test
    void testGenerarTextoFactura() {
        String resultadoEsperado = "Cliente: Juan Perez\nDirección: Calle 123\n----------------\nHamburguesa de Res\n            20000\nPapas Fritas\n            5000\nGaseosa\n            3000\n----------------\nPrecio Neto:  28000\nIVA:          5320\nPrecio Total: 33320\n";
        assertEquals(resultadoEsperado, pedido.generarTextoFactura(), "La factura generada no corresponde con la esperada.");
    }
}
