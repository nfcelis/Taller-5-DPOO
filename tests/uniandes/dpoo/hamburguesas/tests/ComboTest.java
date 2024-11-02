package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ComboTest {
	
	 private Combo combo1;

	
	@BeforeEach
	void setup() throws Exception{
		
		ProductoMenu HPollo= new ProductoMenu("Hamburguesa de pollo",25000);
		ProductoMenu Papas= new ProductoMenu("Papas fritas",6000);
		ProductoMenu Gaseosa= new ProductoMenu("CocaCola",5000);
		ArrayList<ProductoMenu> itemsCombo=new ArrayList<>(); 
		itemsCombo.add(HPollo);
		itemsCombo.add(Papas);
		itemsCombo.add(Gaseosa);
		
		combo1= new Combo("McPollo",2000,itemsCombo);
		
	}
	
	@AfterEach
	void tearDown() throws Exception{
		
	}
	
	@Test
	void testgetnombre() {
		assertEquals("McPollo",combo1.getNombre(),"El nombre no corresponde con el esperado.");
	}
	@Test
	void testgetprecio() {
		assertEquals(30000, combo1.getPrecio(),"El precio no corresponde con el esperado.");
	}
	
	@Test
	void testgenerartextofactura() {
		String resultado="Combo McPollo\n Descuento: 2000.0\n            30000\n";
		assertEquals(resultado,combo1.generarTextoFactura(),"La factura no se genera correctamente");
	}
}
	
	


