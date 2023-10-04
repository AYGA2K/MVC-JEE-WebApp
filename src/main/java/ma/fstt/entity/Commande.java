package ma.fstt.entity;

import java.sql.Date;
import java.util.List;

public class Commande {

  private Integer id;

  private Date date;

  private Client client;

  private List<LignedeCommande> lignesdeCommande;

  public Commande() {
  }

  public Commande(Integer id, Date date, Client client) {
    this.id = id;
    this.date = date;
    this.client = client;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  @Override
  public String toString() {
    return "Commande{" +
        "id='" + id + '\'' +
        ", date=" + date +
        ", client=" + client +
        '}';
  }

  public List<LignedeCommande> getLignesdeCommande() {
    return lignesdeCommande;
  }

  public void setLignesdeCommande(List<LignedeCommande> lignesdeCommande) {
    this.lignesdeCommande = lignesdeCommande;
  }

}
