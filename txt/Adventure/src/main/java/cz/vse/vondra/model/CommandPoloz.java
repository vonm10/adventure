/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package cz.vse.vondra.model;





/*******************************************************************************
 * Třída implementující příkaz polož, který předmět z invetáře
 * přemístí do aktuální oblasti.
 *
 * @author  Matyáš Vondra
 * @version 20-05-31
 */
public class CommandPoloz implements ICommand
{
    private static final String NAME = "poloz";
    
    private GamePlan plan;
    
    // Vytvoření inventáře
    private Inventar inventar;
    
    /**
     * Konstruktor třídy. Inicializuje datové atributy.
     * 
     * @param inventar objekt třídy Inventar
     * @param plan objekt třídy GamePlan
     */
    public CommandPoloz(GamePlan plan)
    {
        this.plan = plan;
        inventar = plan.getInventar();
    }
    
    /**
     * Metoda vykonání příkazu- pokusí se předmět z inventáře přesunout
     * do aktuální lokace. Nejdříve podmínkami zkontroluje vstupní parametry
     * a pak jestli vyhledaný předmět vůbec je v inventáři. Pokud ano,
     * předmět z inventáře smaže a přidá ho do aktuální lokace.
     * 
     * Také je zde obsažena podmínka pro výměnu s opicí v lokaci liány.
     * Ta volá metodu ze třídy GamePlan.
     * 
     * @param parameters parametry příkazu <i>(očekává se pole s jedním prvkem)</i>
     * @return informace pro hráče, které se vypíšou na konzoli
     */
    @Override
    public String process(String... parameters)
    {
        if (parameters.length == 0) {
            return "Nevím, co mám položit, musíš zadat název předmětu.";
        }
        
        if (parameters.length > 1) {
            return "Tomu nerozumím, neumím položit více předmětů současně.";
        }
        
        String itemName = parameters[0];
        Area area = plan.getCurrentArea();
        
        Item vec = inventar.vyndatZInventare(itemName);
        if (vec == null)
        {
            return "Takovou věc v inventáři nemáš.";
        }
        
        if(area.getName().equals("liány") && itemName.equals("banán"))
        {
            plan.vymenaSOpici();
            return "Dal jsi opici banán a za to ti v inventáři přibylo veslo.";
        }
        plan.getCurrentArea().addItem(vec);
        return "Položil jsi " + itemName + " na zem.";
    }
    
    /**
     * Metoda vrací název příkazu tj.&nbsp; slovo {@value NAME}.
     *
     * @return název příkazu
     */
    @Override
    public String getName()
    {
        return NAME;
    }
}
