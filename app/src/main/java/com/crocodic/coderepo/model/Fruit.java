package com.crocodic.coderepo.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by yzzzd crocodic on 10/11/17.
 */

public class Fruit extends RealmObject {
    @PrimaryKey
    private Integer id;
    private String nama;
    private String warna;
    private String rasa;

    public Fruit() {
    }

    public Fruit(Integer id, String nama, String warna, String rasa) {
        this.id = id;
        this.nama = nama;
        this.warna = warna;
        this.rasa = rasa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public String getRasa() {
        return rasa;
    }

    public void setRasa(String rasa) {
        this.rasa = rasa;
    }
}
