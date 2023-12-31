
package ma.fstt.entity;

import java.util.List;

public class Client {

  private Integer id;

  private String nom;
  private String prenom;
  private String adresse;
  private String email;
  private String telephone;

  private List<Commande> commandes;

  public List<Commande> getCommandes() {
    return commandes;
  }

  public void setCommandes(List<Commande> commandes) {
    this.commandes = commandes;
  }

  public Client() {
  }

  public Client(Integer id, String nom, String prenom, String adresse, String email, String telephone) {
    this.id = id;
    this.nom = nom;
    this.prenom = prenom;
    this.adresse = adresse;
    this.email = email;
    this.telephone = telephone;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  public String getAdresse() {
    return adresse;
  }

  public void setAdresse(String adresse) {
    this.adresse = adresse;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  @Override
  public String toString() {
    return "Client{" +
        "id='" + id + '\'' +
        ", nom='" + nom + '\'' +
        ", prenom='" + prenom + '\'' +
        ", adresse='" + adresse + '\'' +
        ", email='" + email + '\'' +
        ", telephone='" + telephone + '\'' +
        '}';
  }
}
