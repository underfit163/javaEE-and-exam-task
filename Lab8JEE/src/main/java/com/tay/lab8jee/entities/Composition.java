package com.tay.lab8jee.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Composition")
public class Composition {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "id_for_comp")
    @SequenceGenerator(name = "id_for_comp", sequenceName = "id_for_composition",allocationSize = 1)
    private int idComposition;
    @Column(nullable = false,length = 30)
    private String nameComposition;
    @Column(nullable = false)
    private double durationComposition;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAlbum")
    private Album album;

    public Composition() {
    }

    public Composition(String nameComposition, double durationComposition) {
        this.nameComposition = nameComposition;
        this.durationComposition = durationComposition;
    }

    public int getIdComposition() {
        return idComposition;
    }

    public String getNameComposition() {
        return nameComposition;
    }

    public void setNameComposition(String nameComposition) {
        this.nameComposition = nameComposition;
    }

    public double getDurationComposition() {
        return durationComposition;
    }

    public void setDurationComposition(double durationComposition) {
        this.durationComposition = durationComposition;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "Composition{" +
                "nameComposition='" + nameComposition + '\'' +
                ", durationComposition=" + durationComposition +
                '}';
    }
}
