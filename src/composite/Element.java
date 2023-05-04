package composite;

import java.util.Objects;

public abstract class Element {

    private int id;

    private String nom;

    private int heures;

    public int getId()
    {
        return id;
    }

    public String getNom()
    {
        return nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return id == element.id && Objects.equals(nom, element.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom);
    }

    public abstract void afficher();

    public int getHeures()
    {
        return heures;
    }
}
