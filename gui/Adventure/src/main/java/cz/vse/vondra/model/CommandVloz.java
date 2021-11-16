/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package cz.vse.vondra.model;





/*******************************************************************************
 * Třída implementující příkaz Vlož
 * pro přesun předmětu mezi dvěma inventáři.
 *
 * @author  Matyáš Vondra
 * @version 2020-05-31
 */
public class CommandVloz implements ICommand
{
    // Datové atributy
    private final static String NAME = "vloz";
    private GamePlan plan;
    private Inventar inventar;
    private Inventar lod;
    
    
    /**
     * Konstruktor třídy. Inicializuje datové atributy.
     * 
     * @param plan objekt třídy GamePlan
     * @param inventar objekt třídy Inventar
     * @param lod objekt třídy Inventar
     */
    public CommandVloz(GamePlan plan)
    {
        this.plan = plan;
        inventar = plan.getInventar();
        lod = plan.getLod();
    }
    
    /**
     * Metoda vykonávání příkazu. Zkontroluje podmínky a na základě nich vrací textový řetězec.
     * 
     * @param parameters textové řetězce načtené z konzole
     * @return textový řetězec
     */
    @Override
    public String process(String... parameters)
    {
        if (parameters.length == 0) {
            return "Nevím, co mám předat, musíš zadat název předmětu.";
        }
        
        if (parameters.length > 1) {
            return "Tomu nerozumím, neumím předat více předmětů současně.";
        }
        
        String itemName = parameters[0];
        Item predmet = inventar.getVec(itemName);
        
        if (predmet == null)
        {
            return "Takovou věc v inventáři nemáš.";
        }
        
        if ((predmet.getName().equals("veslo") || predmet.getName().equals("kormidlo") || predmet.getName().equals("plachta")) & plan.getCurrentArea().equals(plan.getFinalArea()))
        {
                lod.vlozitDoInventare(predmet);
                inventar.smazatVec(predmet.getName());
                return "Vložil jsi do lodi předmět " + predmet.getName();
        }
        
        return "Tady není do čeho vkládat.";
    }
    
    
    /**
     * Metoda pro získání jména.
     * 
     * @return konstanta NAME
     */
    @Override
    public String getName()
    {
        return NAME;
    }
}
