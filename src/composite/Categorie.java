package composite;

import java.util.ArrayList;
import java.util.List;

public class Categorie extends Element{

    private int id;
    private String nom;

    private int heures;
    private List<Element> elementList;

    public Categorie(int id, String nom)
    {
        this.id = id;
        this.nom = nom;
        this.elementList = new ArrayList<>();
    }
    public void ajouterElement(Element element)
    {
        elementList.add(element);
    }
    public void removeElement(Element element)
    {
        elementList.remove(element);
    }
    @Override
    public void afficher()
    {
        int totalHeures = 0;
        for (Element e : elementList)
        {
            totalHeures=totalHeures+e.getHeures();
        }

        System.out.println("Catégorie id = "+id+" nom = "+nom+" heures = "+heures);
        for (Element e : elementList)
        {
            e.afficher();

        }
    }

    @Override
    public int getHeures() {
        int totalHeures = 0;
        for (Element element : elementList) {
            totalHeures += element.getHeures();
        }
        return totalHeures;
    }
}
