package cz.vse.vondra;

import cz.vse.vondra.model.Area;
import cz.vse.vondra.model.Item;
import cz.vse.vondra.model.Postava;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Testovací třída pro komplexní otestování třídy {@link Area}.
 *
 * @author Jarmila Pavlíčková
 * @author Jan Říha
 * @version LS 2020
 */
public class AreaTest
{
    /**
     * Testovací metoda pro otestování východů lokací
     */
    @Test
    public void testAreaExits()
    {
        Area area1 = new Area("hala", "Toto je vstupní hala budovy VŠE na Jižním městě.");
        Area area2 = new Area("bufet", "Toto je bufet, kam si můžete zajít na svačinu.");

        area1.addExit(area2);
        area2.addExit(area1);

        assertEquals(area1, area2.getExitArea(area1.getName()));
        assertEquals(area2, area1.getExitArea(area2.getName()));

        assertNull(area1.getExitArea("pokoj"));
        assertNull(area2.getExitArea("pokoj"));
    }

    /**
     * Testovací metoda pro otestování předmětů v lokacích
     */
    @Test
    public void testItems()
    {
        Area area = new Area("hala", "Toto je vstupní hala budovy VŠE na Jižním městě.");

        Item item1 = new Item("stul", "Těžký dubový stůl.", false);
        Item item2 = new Item("rum", "Láhev vyzrálého rumu.");

        assertFalse(area.containsItem(item1.getName()));
        assertFalse(area.containsItem(item2.getName()));
        assertFalse(area.containsItem("pc"));

        area.addItem(item1);
        area.addItem(item2);

        assertEquals(item1, area.getItem(item1.getName()));
        assertEquals(item2, area.getItem(item2.getName()));
        assertNull(area.getItem("pc"));

        assertTrue(area.containsItem(item1.getName()));
        assertTrue(area.containsItem(item2.getName()));
        assertFalse(area.containsItem("pc"));

        assertEquals(item1, area.removeItem(item1.getName()));
        assertEquals(item2, area.removeItem(item2.getName()));
        assertNull(area.removeItem("pc"));

        assertFalse(area.containsItem(item1.getName()));
        assertFalse(area.containsItem(item2.getName()));
        assertFalse(area.containsItem("pc"));
    }  
    
    /**
     * Testovací metoda pro otestování postav v lokacích
     */
    @Test
    public void testPostavy()
    {
        Area area = new Area("hala", "Toto je vstupní hala budovy VŠE na Jižním městě.");

        Postava postava1 = new Postava("postava1", "blabla",false);
        Postava postava2 = new Postava("postava2", "blabla",false);

        assertEquals(null, area.getPostavu("postava1"));
        assertEquals(null, area.getPostavu("postava2"));
        
        area.addPostavu(postava1);
        area.addPostavu(postava2);
        
        assertEquals(postava1, area.getPostavu("postava1"));
        assertEquals(postava2, area.getPostavu("postava2"));
    }   

}
