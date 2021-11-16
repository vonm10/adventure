package cz.vse.vondra.model;


/**
 * Třída představující předmět ve hře.
 * Každý předmět má svůj název, popis a příznak, zda ho lze sebrat.
 * 
 * @author Jan Říha
 * @version LS 2020
 */
public class Item
{
    private String name;
    private String description;
    private boolean moveable;
    
    /**
     * Konstruktor třídy. Inicializuje datové atributy.
     * 
     * @param name String jména předmětu
     * @param description String popisku předmětu
     * @param moveable příznak pohyblivosti
     */
    public Item(String name, String description, boolean moveable)
    {
        this.name = name;
        this.description = description;
        this.moveable = moveable;
    }
    
    
    /**
     * Druhý konstruktor, využívaný pro předměty s moveable = true.
     * 
     * @param name String jména předmětu
     * @param description String popisku předmětu
     */
    public Item(String name, String description)
    {
        this(name, description, true);
    }
    
    /**
     * Metoda pro získání jména předmětu.
     * 
     * @return jméno
     */
    public String getName()
    {
        return name;
    }

    /**
     * Metoda pro získání popisku předmětu.
     * 
     * @return popisek
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Metoda pro nastavení popisku předmětu.
     * 
     * @param description Popisek, který chceme nastavit
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Metoda pro získání příznaku moveable.
     * 
     * @return true nebo false
     */
    public boolean isMoveable()
    {
        return moveable;
    }

    /**
     * Metoda pro nastavení příznaku moveable.
     * 
     * @param moveable true nebo false
     */
    public void setMoveable(boolean moveable)
    {
        this.moveable = moveable;
    }

}
