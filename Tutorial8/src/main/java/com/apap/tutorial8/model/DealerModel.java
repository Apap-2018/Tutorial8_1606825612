package com.apap.tutorial8.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * DealerModel
 */
@Entity
@Table(name = "dealer")
public class DealerModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Size(max = 50)
    @Column(name = "alamat", nullable = false)
    private String alamat;

    @NotNull
    @Size(max = 13)
    @Column(name = "no_telp", nullable = false)
    private String noTelp;

    @OneToMany(mappedBy = "dealer", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<CarModel> listCar = new ArrayList<CarModel>();

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }
    /**
     * @param nama the nama to set
     */
    public void setNama(String nama) {
        this.nama = nama;
    } 
    /**
     * @param alamat the alamat to set
     */
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    /**
     * @param noTelp the noTelp to set
     */
    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }
    /**
     * @param listCar the listCar to set
     */
    public void setListCar(List<CarModel> listCar) {
        this.listCar = listCar;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }
    /**
     * @return the nama
     */
    public String getNama() {
        return nama;
    }
    /**
     * @return the alamat
     */
    public String getAlamat() {
        return alamat;
    }
    /**
     * @return the noTelp
     */
    public String getNoTelp() {
        return noTelp;
    }
    /**
     * @return the listCar
     */
    public List<CarModel> getListCar() {
        return listCar;
    }
}