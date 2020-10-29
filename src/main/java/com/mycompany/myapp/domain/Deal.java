package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * A Deal.
 */
@Entity
@Table(name = "deal")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Deal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date")
    private ZonedDateTime date;

    @Column(name = "amount")
    private String amount;

    @ManyToOne
    @JsonIgnoreProperties(value = "deals", allowSetters = true)
    private Client client;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "deal_goods",
               joinColumns = @JoinColumn(name = "deal_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "goods_id", referencedColumnName = "id"))
    private Set<Goods> goods = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public Deal date(ZonedDateTime date) {
        this.date = date;
        return this;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public Deal amount(String amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Client getClient() {
        return client;
    }

    public Deal client(Client client) {
        this.client = client;
        return this;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<Goods> getGoods() {
        return goods;
    }

    public Deal goods(Set<Goods> goods) {
        this.goods = goods;
        return this;
    }

    public Deal addGoods(Goods goods) {
        this.goods.add(goods);
        goods.getDeals().add(this);
        return this;
    }

    public Deal removeGoods(Goods goods) {
        this.goods.remove(goods);
        goods.getDeals().remove(this);
        return this;
    }

    public void setGoods(Set<Goods> goods) {
        this.goods = goods;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deal)) {
            return false;
        }
        return id != null && id.equals(((Deal) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Deal{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", amount='" + getAmount() + "'" +
            "}";
    }
}
