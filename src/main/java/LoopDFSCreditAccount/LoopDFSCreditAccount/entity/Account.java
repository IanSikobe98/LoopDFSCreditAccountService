package LoopDFSCreditAccount.LoopDFSCreditAccount.entity;

import javax.persistence.*;

@Entity
public class Account {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "iban")
    private String iban;
    @Basic
    @Column(name = "bicswift")
    private String bicswift;
    @Basic
    @Column(name = "clientid")
    private Integer clientid;

    @Basic
    @Column(name = "status")
    private Integer status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBicswift() {
        return bicswift;
    }

    public void setBicswift(String bicswift) {
        this.bicswift = bicswift;
    }

    public Integer getClientid() {
        return clientid;
    }

    public void setClientid(Integer clientid) {
        this.clientid = clientid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (id != account.id) return false;
        if (iban != null ? !iban.equals(account.iban) : account.iban != null) return false;
        if (bicswift != null ? !bicswift.equals(account.bicswift) : account.bicswift != null) return false;
        if (clientid != null ? !clientid.equals(account.clientid) : account.clientid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (iban != null ? iban.hashCode() : 0);
        result = 31 * result + (bicswift != null ? bicswift.hashCode() : 0);
        result = 31 * result + (clientid != null ? clientid.hashCode() : 0);
        return result;
    }
}
