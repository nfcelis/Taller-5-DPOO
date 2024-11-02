package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;
import uniandes.dpoo.hamburguesas.mundo.Pedido;

public class RestauranteTest {

    private Restaurante restaurante;

    @BeforeEach
    void setup() throws Exception {
        restaurante = new Restaurante();
        
        // Utilizar métodos públicos para simular la carga de menú y combos
        ProductoMenu hamburguesa = new ProductoMenu("Hamburguesa de Res", 20000);
        ProductoMenu papas = new ProductoMenu("Papas Fritas", 5000);
        ProductoMenu gaseosa = new ProductoMenu("Gaseosa", 3000);
        restaurante.getMenuBase().add(hamburguesa);
        restaurante.getMenuBase().add(papas);
        restaurante.getMenuBase().add(gaseosa);

        Combo combo = new Combo("Combo Familiar", 2000, restaurante.getMenuBase());
        restaurante.getMenuCombos().add(combo);

        // Crear la carpeta de facturas si no existe
        File facturasDir = new File("./facturas");
        if (!facturasDir.exists()) {
            facturasDir.mkdirs();
        }
    }

    @AfterEach
    void tearDown() throws Exception {
        File facturasDir = new File("./facturas");
        for (File file : facturasDir.listFiles()) {
            if (file.isFile()) {
                file.delete();
            }
        }
        restaurante = null;
    }

    @Test
    void testIniciarYObtenerPedidoEnCurso() throws Exception {
        restaurante.iniciarPedido("Carlos Garcia", "Avenida Siempre Viva 742");
        Pedido pedidoEnCurso = restaurante.getPedidoEnCurso();
        assertNotNull(pedidoEnCurso, "El pedido en curso no debería ser nulo.");
        assertEquals("Carlos Garcia", pedidoEnCurso.getNombreCliente(), "El nombre del cliente no corresponde con el esperado.");
    }

    @Test
    void testCerrarYGuardarPedido() throws Exception {
        restaurante.iniciarPedido("Carlos Garcia", "Avenida Siempre Viva 742");
        restaurante.cerrarYGuardarPedido();
        assertNull(restaurante.getPedidoEnCurso(), "El pedido en curso debería ser nulo después de cerrar el pedido.");
        assertEquals(1, restaurante.getPedidos().size(), "El número de pedidos no corresponde con el esperado después de cerrar un pedido.");
    }

    @Test
    void testCargarMenu() {
        assertEquals(3, restaurante.getMenuBase().size(), "El número de productos en el menú no corresponde con el esperado.");
    }

    @Test
    void testCargarCombos() {
        assertEquals(1, restaurante.getMenuCombos().size(), "El número de combos cargados no corresponde con el esperado.");
    }

    @Test
    void testAgregarProductoAPedido() throws Exception {
        restaurante.iniciarPedido("Laura Lopez", "Calle 456");
        ProductoMenu hamburguesa = new ProductoMenu("Hamburguesa de Res", 20000);
        restaurante.getPedidoEnCurso().agregarProducto(hamburguesa);
        assertEquals(20000, restaurante.getPedidoEnCurso().getPrecioNetoPedido(), "El precio neto del pedido no corresponde con el esperado.");
    }

    @Test
    void testGuardarFactura() throws Exception {
        restaurante.iniciarPedido("Andres Torres", "Calle 789");
        ProductoMenu hamburguesa = new ProductoMenu("Hamburguesa de Res", 20000);
        restaurante.getPedidoEnCurso().agregarProducto(hamburguesa);
        int idPedido = restaurante.getPedidoEnCurso().getIdPedido();
        restaurante.cerrarYGuardarPedido();
        File archivo = new File("./facturas/factura_" + idPedido + ".txt");
        assertTrue(archivo.exists(), "El archivo de la factura debería existir.");
        archivo.delete(); // Eliminar el archivo después de la prueba
    }

    @Test
    void testGetPedidos() throws Exception {
        restaurante.iniciarPedido("Ana Ruiz", "Carrera 123");
        restaurante.cerrarYGuardarPedido();
        assertEquals(1, restaurante.getPedidos().size(), "El número de pedidos no corresponde con el esperado.");
    }

    @Test
    void testGetMenuBase() {
        assertNotNull(restaurante.getMenuBase(), "El menú base no debería ser nulo.");
        assertEquals(3, restaurante.getMenuBase().size(), "El número de productos en el menú base no corresponde con el esperado.");
    }

    @Test
    void testGetMenuCombos() {
        assertNotNull(restaurante.getMenuCombos(), "El menú de combos no debería ser nulo.");
        assertEquals(1, restaurante.getMenuCombos().size(), "El número de combos en el menú no corresponde con el esperado.");
    }

    @Test
    void testGetIngredientes() {
        assertNotNull(restaurante.getIngredientes(), "La lista de ingredientes no debería ser nula.");
        assertEquals(0, restaurante.getIngredientes().size(), "El número de ingredientes no corresponde con el esperado.");
    }
}
