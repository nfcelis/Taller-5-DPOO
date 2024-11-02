package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoAjustadoTest {
	
	private ProductoAjustado productoajustado1;
	private ProductoMenu productobase;
	
	
	
	@BeforeEach
	void setup() throws Exception{
		productobase= new ProductoMenu("Hamburguesa de pollo",25000);
		productoajustado1= new ProductoAjustado(productobase);
		
		//Caso de prueba
		productoajustado1.agregarIngrediente(new Ingrediente("Suero costeño",1000));
		productoajustado1.agregarIngrediente(new Ingrediente("Salmon ",50000));
		productoajustado1.quitarIngrediente(new Ingrediente("Pollo",0));
		
	}
	
	@AfterEach
	void tearDown() throws Exception{
		
	}
	
	@Test
	void testgetnombre() {
		assertEquals("Hamburguesa de pollo", productoajustado1.getNombre(),"El nombre no corresponde con el esperado.");
	}
	@Test
	void testgetprecio() {
		assertEquals(76000, productoajustado1.getPrecio(),"El precio no corresponde con el esperado.");
	}
	//Estaba fallando porque el metodo estaba mal implementado
	@Test
	void testgenerartextofactura() {
		String resultado="Hamburguesa de pollo\n    +Suero costeño                1000    +Salmon                 50000    -Pollo            76000\n";

		assertEquals(resultado,productoajustado1.generarTextoFactura(),"La factura no se genera correctamente");
	}
}
	
	