/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package cz.vse.vondra;



import cz.vse.vondra.model.CommandVloz;
import cz.vse.vondra.model.GamePlan;
import cz.vse.vondra.model.Inventar;
import cz.vse.vondra.model.Item;
import org.junit.Test;


import static org.junit.Assert.*;



/*******************************************************************************
 * Testovací třída {@code CommandVlozTest} slouží ke komplexnímu otestování
 * třídy {@link CommandVlozTest}.
 *
 * @author  Matyáš Vondra
 * @version 2020-06-11
 */
public class CommandVlozTest
{
    /**
     * Testovací metoda pro otestování příkazu Vlož
     */
    @Test
    public void testVloz()
    {
        GamePlan plan = new GamePlan();
        Inventar inventar = plan.getInventar();
        Inventar lod = plan.getLod();
        
        Item item1 = new Item ("item1", "blabla");
        inventar.vlozitDoInventare(item1);
        
        CommandVloz prikaz = new CommandVloz(plan);
        
        prikaz.process();        
        assertEquals(1, inventar.getZaplnenost());
        assertEquals(0, lod.getZaplnenost());
        
        prikaz.process("a", "a");        
        assertEquals(1, inventar.getZaplnenost());
        assertEquals(0, lod.getZaplnenost());
        
        prikaz.process("blabla"); 
        assertEquals(1, inventar.getZaplnenost());
        assertEquals(0, lod.getZaplnenost());
        
        prikaz.process("item1");        
        assertEquals(0, inventar.getZaplnenost());
        assertEquals(1, lod.getZaplnenost());
    }
}
