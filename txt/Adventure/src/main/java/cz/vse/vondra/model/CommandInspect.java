package cz.vse.vondra.model;


/*******************************************************************************
 * Třída implementující příkaz prozkoumej pro vypsání popisku předmětu.
 *
 * @author  Jan Říha
 * @version LS 2020
 */
public class CommandInspect implements ICommand
{
    private static final String NAME = "prozkoumej";
    
    private GamePlan plan;
    
    /**
     * Konstruktor třídy. Inicializuje datové atributy.
     * 
     * @param plan objekt třídy GamePlan
     */
    public CommandInspect(GamePlan plan)
    {
        this.plan = plan;
    }
    
    /**
     * Metoda vykonání příkazu- provede metodu předmětu pro získání popisku.
     * Podmínkami se kontrolují vstupní parametry a zda se v lokaci nachází hledaný předmět.
     * 
     * @param parameters parametry příkazu <i>(očekává se pole s jedním prvkem)</i>
     * @return informace pro hráče, které se vypíšou na konzoli
     */
    public String process(String... parameters)
    {
        if (parameters.length == 0) {
            return "Nevím, co mám prozkoumat, musíš zadat název předmětu.";
        }
        
        if (parameters.length > 1) {
            return "Tomu nerozumím, neumím prozkoumat více předmětů současně.";
        }
        
        String itemName = parameters[0];
        Area area = plan.getCurrentArea();
                
        // Prozkoumávaný předmět může být v aktuální lokaci, ale i v inventáři
        
        // (pseudo-kód, jak by to asi mohlo vypadat, konkrétní
        // implementace ve Vaší hře se může lišit)
        
        /* Inventory inventory = plan.getInventory();
         *
         * if (!area.containsItem(itemName) && !inventory.containsItem(itemName)) {
         *     ... */

        if (!area.containsItem(itemName)) {
            return "Předmět '" + itemName + "' tady není.";
        }
        
        // Výpis popisu předmětu z inventáře
        
        // (pseudo-kód, jak by to asi mohlo vypadat, konkrétní
        // implementace ve Vaší hře se může lišit)
        
        /* if (inventory.containsItem(itemName)) {
         *     return inventory.getItem(itemName).getDescription();
         * } */

        return area.getItem(itemName).getDescription();
    }

    /**
     * Metoda vrací název příkazu tj.&nbsp; slovo {@value NAME}.
     *
     * @return název příkazu
     */
    public String getName()
    {
        return NAME;
    }
}
