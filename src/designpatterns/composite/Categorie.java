package designpatterns.composite;

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
        getHeures();

        System.out.println("Catégorie id = "+id+" nom = "+nom+" heures = "+heures);
        for (Element e : elementList)
        {
            e.afficher();

        }
    }

    @Override
    public int getHeures() {
        heures = 0;
        for (Element element : elementList) {
            heures += element.getHeures();
        }
        return heures;
    }
}
