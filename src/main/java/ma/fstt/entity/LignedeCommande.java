package ma.fstt.entity;

public class LignedeCommande {

  private Integer id;

  private int quantite;

  private Commande commande;

  private Produit produit;

  public LignedeCommande() {
  }

  public LignedeCommande(Integer id, int quantite, Produit produit, Commande commande) {
    this.id = id;
    this.quantite = quantite;
    this.produit = produit;
    this.commande = commande;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public int getQuantite() {
    return quantite;
  }

  public void setQuantite(int quantite) {
    this.quantite = quantite;
  }

  public Produit getProduit() {
    return produit;
  }

  public void setProduit(Produit produit) {
    this.produit = produit;
  }

  public Commande getCommande() {
    return commande;
  }

  public void setCommande(Commande commande) {
    this.commande = commande;
  }

  @Override
  public String toString() {
    return "LignedeCommande{" +
        "id='" + id + '\'' +
        ", quantite=" + quantite +
        ", produit=" + produit +
        ", commande=" + commande +
        '}';
  }
}
