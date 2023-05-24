package classesmetiers;

import java.util.Objects;
/**
 *   classe Formateur de l'organisme de formation
 *   @author Mirko Cuozzo
 *   @version 1.0
 *
 */
public class Formateur {
    /**
     *Identifiant unique du formateur
     */
    private int id_formateur;
    /**
     * Email du client
     */
    private String mail;
    /**
     * Nom du client
     */
    private String nom;
    /**
     * Prenom du client
     */
    private String prenom;

    /**
     * Constructeur par défaut du Formateur
     */
    public Formateur()
    {

    }

    /**
     *
     * @param id identifiant unique du client affecté par la base de donnée
     * @param mail email du client unique
     * @param nom nom du client
     * @param prenom prenom du client
     */
    public Formateur(int id, String mail, String nom, String prenom) {
        this.id_formateur = id;
        this.mail = mail;
        this.nom = nom;
        this.prenom = prenom;
    }

    private Formateur(FormateurBuilder builder)
    {
        this.id_formateur = builder.id_formateur;
        this.mail = builder.mail;
        this.prenom = builder.prenom;
        this.nom = builder.nom;
    }

    /**
     * égalité de deux formateurs basé sur l'id et l'email
     * @param o autre element
     * @return egalité ou pas
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Formateur formateur = (Formateur) o;
        return id_formateur == formateur.id_formateur && mail.equals(formateur.mail);
    }

    @Override
    public String toString() {
        return "Formateur{" +
                "id_formateur=" + id_formateur +
                ", mail='" + mail + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }

    /**
     * Calcul du hashcode du formateur basé sur l'id_formateur et le mail
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(id_formateur, mail);
    }

    /**
     * Getter id
     * @return id du formateur
     */
    public int getId_formateur() {
        return id_formateur;
    }

    /**
     * Setter id
     * @param id id du formateur
     */
    public void setId_formateur(int id) {
        this.id_formateur = id;
    }

    /**
     * Getter Mail
     * @return email du formateur
     */
    public String getMail() {
        return mail;
    }

    /**
     * Setter Mail
     * @param mail email du formateur
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Getter Nom
     * @return Nom du formateur
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter Nom
     * @param nom nom du Formateur
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter Prenom
     * @return prenom du formateur
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Setter Prenom
     * @param prenom prenom du formateur
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public static class FormateurBuilder
    {
        protected int id_formateur;

        protected String mail;

        protected String nom;

        protected String prenom;

        public FormateurBuilder setIdFormateur(int idFormateur)
        {
            this.id_formateur = idFormateur;
            return this;
        }

        public FormateurBuilder setMail(String mail) {
            this.mail = mail;
            return this;
        }

        public FormateurBuilder setNom(String nom) {
            this.nom = nom;
            return this;
        }

        public FormateurBuilder setPrenom(String prenom) {
            this.prenom = prenom;
            return this;
        }

        public Formateur build() throws Exception {
            if (mail.isBlank()) {
                throw new Exception("Erreur lors de la construction du formateur : l'adresse e-mail est nulle");
            }
            return new Formateur(this);
        }


    }
}
