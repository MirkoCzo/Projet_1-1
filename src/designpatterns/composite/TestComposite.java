package designpatterns.composite;

public class TestComposite {

    public static void main(String[] args) {
        // Créer des cours
        Cours cours1 = new Cours(1, "Cours 1", 10);
        Cours cours2 = new Cours(2, "Cours 2", 20);
        Cours cours3 = new Cours(3, "Cours 3", 15);

        // Créer des catégories
        Categorie categorie1 = new Categorie(101, "Catégorie 1");
        Categorie categorie2 = new Categorie(102, "Catégorie 2");

        // Ajouter des cours et des catégories
        categorie1.ajouterElement(cours1);
        categorie1.ajouterElement(cours2);
        categorie2.ajouterElement(cours3);

        // Ajouter la catégorie 2 à la catégorie 1
        categorie1.ajouterElement(categorie2);

        // Afficher les éléments de la catégorie 1
        categorie1.afficher();
    }
}
