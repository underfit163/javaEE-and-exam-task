package com.tay.lab8jee.entities;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Album")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_for_al")
    @SequenceGenerator(name = "id_for_al", sequenceName = "id_for_album", allocationSize = 1)
    private int idAlbum;
    @Column(nullable = false, length = 30)
    private String nameAlbum;
    @Column(length = 30)
    private String genreAlbum;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMusician")
    private Musician musician;
    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    private List<Composition> compositions;

    public Album() {
    }

    public Album(String nameAlbum, String genreAlbum) {
        this.nameAlbum = nameAlbum;
        this.genreAlbum = genreAlbum;
        this.compositions = new ArrayList<>();
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public String getNameAlbum() {
        return nameAlbum;
    }

    public void setNameAlbum(String nameAlbum) {
        this.nameAlbum = nameAlbum;
    }

    public String getGenreAlbum() {
        return genreAlbum;
    }

    public void setGenreAlbum(String genreAlbum) {
        this.genreAlbum = genreAlbum;
    }

    public Musician getMusician() {
        return musician;
    }

    public void setMusician(Musician musician) {
        this.musician = musician;
    }

    public List<Composition> getCompositions() {
        return compositions;
    }

    public void setCompositions(List<Composition> compositions) {
        this.compositions = compositions;
    }

    @Override
    public String toString() {
        return "Album{" +
                "nameAlbum='" + nameAlbum + '\'' +
                ", genreAlbum='" + genreAlbum + '\'' +
                '}';
    }

    public void addComposition(Composition composition) {
        composition.setAlbum(this);
        compositions.add(composition);
    }
}
