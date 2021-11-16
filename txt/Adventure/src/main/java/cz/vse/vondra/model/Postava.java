/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package cz.vse.vondra.model;





/*******************************************************************************
 * Třída představující postavu ve hře.
 * Každá postava má svoje jméno a proslov, který říká.
 *
 * @author  Matyáš Vondra
 * @version 2020-05-31
 */
public class Postava
{
    // Datové atributy
    private String jmeno;
    private String proslov;
    private boolean smrtici;
    
    /**
     * Konstruktor třídy. Inicializuje datové atributy.
     * 
     * @param jmeno String jména postavy
     * @param proslov String proslovu postavy
     */
    public Postava (String jmeno, String proslov, boolean smrtici)
    {
        this.jmeno = jmeno;
        this.proslov = proslov;
        this.smrtici = smrtici;
    }
    
    /**
     * Metoda, která vrací jméno postavy.
     * 
     * @return jméno postavy
     */
    public String getJmeno()
    {
        return jmeno; 
    }
    
     /**
     * Metoda, která vrací proslov postavy.
     * 
     * @return proslov postavy
     */
    public String getProslov() 
    {
        return proslov; 
    }
    
    public boolean isSmrtici()
    {
        return smrtici;
    }
}
