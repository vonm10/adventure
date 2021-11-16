/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package cz.vse.vondra;


import cz.vse.vondra.model.Area;
import cz.vse.vondra.model.CommandMove;
import cz.vse.vondra.model.GamePlan;
import org.junit.Test;


import static org.junit.Assert.*;



/*******************************************************************************
 * Testovací třída {@code CommandMoveTest} slouží ke komplexnímu otestování
 * třídy {@link CommandMoveTest}.
 *
 * @author  Matyáš Vondra
 * @version 2020-06-11
 */
public class CommandMoveTest
{
    /**
     * Testovací metoda pro otestování příkazu Jdi
     */
    @Test
    public void testMove()
    {
        Area area1 = new Area("hala", "Toto je vstupní hala budovy VŠE na Jižním městě.");
        Area area2 = new Area("bufet", "Toto je bufet, kam si můžete zajít na svačinu.");

        area1.addExit(area2);
        area2.addExit(area1);
        
        GamePlan plan = new GamePlan();
        plan.setCurrentArea(area1);
        
        CommandMove prikaz = new CommandMove(plan);
              
        prikaz.process(); 
        assertEquals(area1, plan.getCurrentArea());
        
        prikaz.process("a", "a");
        assertEquals(area1, plan.getCurrentArea());
        prikaz.process("a"); 
        assertEquals(area1, plan.getCurrentArea());
        
        prikaz.process("bufet");
        assertEquals(area2, plan.getCurrentArea());
        
        prikaz.process("a");  
        assertEquals(area2, plan.getCurrentArea());
    }
}
