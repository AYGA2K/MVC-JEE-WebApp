package ma.fstt.entity;

import java.util.List;

public class Produit {

  private Integer id;

  private String nom;
  private String description;
  private Double prix;

  private List<LignedeCommande> lignesdeCommande;

  public Produit() {
  }

  public Produit(Integer id, String nom, String description, double prix) {
    this.id = id;
    this.nom = nom;
    this.description = description;
    this.prix = prix;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getPrix() {
    return prix;
  }

  public void setPrix(double prix) {
    this.prix = prix;
  }

  @Override
  public String toString() {
    return "Produit{" +
        "id='" + id + '\'' +
        ", nom='" + nom + '\'' +
        ", description='" + description + '\'' +
        ", prix=" + prix +
        '}';
  }

  public void setPrix(Double prix) {
    this.prix = prix;
  }

  public List<LignedeCommande> getLignesdeCommande() {
    return lignesdeCommande;
  }

  public void setLignesdeCommande(List<LignedeCommande> lignesdeCommande) {
    this.lignesdeCommande = lignesdeCommande;
  }
}
