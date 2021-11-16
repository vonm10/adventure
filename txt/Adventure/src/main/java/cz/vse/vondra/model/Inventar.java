/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package cz.vse.vondra.model;

import java.util.Map;
import java.util.HashMap;



/*******************************************************************************
 * Třída představují herní inventář. Využitá jako
 * hráčův inventář a také jako loď. Každý inventář má
 * svojí kapacitu a HashMapu pro ukládání předmětů.
 *
 * @author  Matyáš Vondra
 * @version 2020-05-31
 */
public class Inventar
{
    // Datové atributy
    private int kapacita;
    private Map<String, Item> obsahInventare;
    
    
    /**
     * Konstruktor pro inicialziaci atributů.
     * 
     * @param kapacita pro nastavení velikosti inventáře
     */
    public Inventar(int kapacita)
    {
        obsahInventare = new HashMap<>();
        this.kapacita = kapacita;
    }
    
    /**
     * Metoda pro vložení předmětu do HashMapy.
     * 
     * @param item vkládaný objekt třídy Item 
     */
    public void vlozitDoInventare(Item item)
    {
        obsahInventare.put(item.getName(), item);        
    }
    
    /**
     * Metoda pro vyndání předmětu z HashMapy.
     * Vyhledání podle názvu a podmínka pro ověření,
     * že HashMapa tento předmět obsahuje.
     * 
     * @param jmenoVeci String se jménem hledaného předmětu
     * @return vyhledaný a vymazaný předmět, případně null
     */
    public Item vyndatZInventare(String jmenoVeci)
    {
        Item vec;
        if(obsahInventare.containsKey(jmenoVeci))
        {
            vec = obsahInventare.get(jmenoVeci);
            obsahInventare.remove(jmenoVeci);
            return vec;
        }
        return null;
    }
    
    /**
     * Metoda pro získání předmětu z HashMapy bez jeho vymazání.
     * 
     * @param jmenoVeci String se jménem hledaného předmětu.
     * @return vyhledaný předmět, případně null
     */
    public Item getVec(String jmenoVeci)
    {
        Item vec;
        if(obsahInventare.containsKey(jmenoVeci))
        {
            vec = obsahInventare.get(jmenoVeci);
            return vec;
        }
        return null;
    }
    
    /**
     * Metoda pro smazání předmětu z HashMapy.
     * 
     * @param jmenoVeci String se jménem hledaného předmětu.
     */
    public void smazatVec(String jmenoVeci)
    {
        if(obsahInventare.containsKey(jmenoVeci))
        {
            obsahInventare.remove(jmenoVeci); 
        }
    }
    
    /**
     * Metoda pro ověření, zda HashMapa obsahuje hledaný předmět.
     * 
     * @param jmenoVeci String se jménem hledaného předmětu.
     * @return true pokud obsahuje, false pokud ne
     */
    public boolean obsahujeVec(String jmenoVeci)
    {
        if(obsahInventare.containsKey(jmenoVeci))
        {
            return true;
        }
        return false;
    }
    
    /**
     * Metoda pro vypsání předmětu, který obsahuje HashMapa.
     * Do textového řetězce.
     * 
     * @return String s názvy předmětů
     */
    public String vypisInventare()
    {
        String vypis;
        
        if (obsahInventare.isEmpty())
        {
            vypis = "V inventáři nemáš nic.";
        } else 
        {
            vypis = "V inventáři máš: ";
            for (String jmenoVeci : obsahInventare.keySet())
            {
                vypis += jmenoVeci;
            }
        }
        
        return vypis;
    }
    
    /**
     * Metoda pro získání množství předmětů v HashMapě.
     * 
     * @return velikost HashMapy
     */
    public int getZaplnenost()
    {
        return obsahInventare.size();
    }
    
    /**
     * Metoda pro získání kapacity inventáře.
     * 
     * @return kapacita
     */
    public int getKapacitaInventare()
    {
        return kapacita;
    }
}
