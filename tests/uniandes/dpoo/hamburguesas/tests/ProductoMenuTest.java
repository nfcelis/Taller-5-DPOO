package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoMenuTest {
	
	private ProductoMenu productomenu1;
	
	
	@BeforeEach
	void setup() throws Exception{
		productomenu1= new ProductoMenu("Hamburguesa de pollo",25000);
		
	}
	
	@AfterEach
	void tearDown() throws Exception{
		
	}
	
	@Test
	void testgetnombre() {
		assertEquals("Hamburguesa de pollo", productomenu1.getNombre(),"El nombre no corresponde con el esperado");
	}
	@Test
	void testgetprecio() {
		assertEquals(25000, productomenu1.getPrecio(),"El precio no corresponde con el esperado.");
	}
	@Test
	void testgenerartextofactura() {
		
		assertEquals("Hamburguesa de pollo\n            25000\n", productomenu1.generarTextoFactura(),"La factura no se genera correctamente");
	}
	
	

}
