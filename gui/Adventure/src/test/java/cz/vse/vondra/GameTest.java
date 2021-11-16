package cz.vse.vondra;

import cz.vse.vondra.model.Game;
import cz.vse.vondra.model.GamePlan;
import cz.vse.vondra.model.Inventar;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Testovací třída pro komplexní otestování herního příběhu.
 *
 * @author Matyáš Vondra
 * @version 2020-06-11
 */
public class GameTest
{
    /**
     * Testovací metoda pro otestování chodu celé hry
     */
    @Test
    public void testGame()
    {
        Game game = new Game();
        GamePlan plan = game.getGamePlan();
        Inventar inventar = plan.getInventar();
        
        assertEquals("ztroskotaná_loď", plan.getCurrentArea().getName());
        
        assertEquals(0, inventar.getZaplnenost());
        game.processCommand("vezmi rum");
        assertEquals(1, inventar.getZaplnenost());
        
        game.processCommand("poloz rum");
        assertEquals(0, inventar.getZaplnenost());
        
        game.processCommand("jdi pláž");
        assertEquals("pláž", plan.getCurrentArea().getName());
        
        assertEquals(false, game.isGameOver());
        game.processCommand("konec");
        assertEquals(true, game.isGameOver());
    }

}
