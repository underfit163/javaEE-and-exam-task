package com.tay.lab8jee.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Musician")
public class Musician {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_for_mus")
    @SequenceGenerator(name = "id_for_mus", sequenceName = "id_for_musician", allocationSize = 1)
    private int idMusician;
    @Column(nullable = false, length = 50)
    private String nameMusician;
    @OneToMany(mappedBy = "musician", cascade = CascadeType.ALL)
    private List<Album> albums;

    public Musician() {
    }

    public Musician(String nameMusician) {
        this.nameMusician = nameMusician;
        this.albums = new ArrayList<>();
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public int getIdMusician() {
        return idMusician;
    }

    public void setIdMusician(int idMusician) {
        this.idMusician = idMusician;
    }

    public String getNameMusician() {
        return nameMusician;
    }

    public void setNameMusician(String nameMusician) {
        this.nameMusician = nameMusician;
    }

    @Override
    public String toString() {
        return "Musician{" +
                "idMusician=" + idMusician +
                ", nameMusician='" + nameMusician + '\'' +
                '}';
    }

    public void  addAlbum(Album album)
    {
        album.setMusician(this);
        albums.add(album);
    }
}
