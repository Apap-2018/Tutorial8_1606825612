package com.apap.tutorial8.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * CarModel
 */
@Entity
@Table(name = "car")
public class CarModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "brand", nullable = false)
    private String brand;
    
    @NotNull
    @Size(max = 50)
    @Column(name = "type", nullable = false, unique = true)
    private String type;

    @NotNull
    @Column(name = "price", nullable = false)
    private long price;

    @NotNull
    @Column(name = "amount", nullable = false)
    private int amount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dealer_id", referencedColumnName = "id")
    @JsonIgnore
    private DealerModel dealer;

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }
    /**
     * @param brand the brand to set
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }
    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * @param price the price to set
     */
    public void setPrice(long price) {
        this.price = price;
    }
    /**
     * @param amount the amount to set
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }
    /**
     * @param dealer the dealer to set
     */
    public void setDealer(DealerModel dealer) {
        this.dealer = dealer;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }
    /**
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }
    /**
     * @return the type
     */
    public String getType() {
        return type;
    }
    /**
     * @return the price
     */
    public long getPrice() {
        return price;
    }
    /**
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }
    /**
     * @return the dealer
     */
    public DealerModel getDealer() {
        return dealer;
    }
}