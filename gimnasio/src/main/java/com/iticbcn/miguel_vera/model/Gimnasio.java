package com.iticbcn.miguel_vera.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="gimnasio")
public class Gimnasio implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column
    private String  nombre;
    @Column
    private int  aforo;
    @Column
    private String ubicacion;
    
    @OneToMany(mappedBy = "gimnasio")
    private List<Socio> socios;

    public Gimnasio() {}

    public Gimnasio(String nombre, int aforo, String ubicacion) {
        this.nombre = nombre;
        this.aforo = aforo;
        this.ubicacion = ubicacion;
    }

    public int getIdGimnasio() {
        return id;
    }

    public void setIdGimnasio(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAforo() {
        return aforo;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    @Override
    public String toString() {
        return "Gimnasio [id=" + id + ", nombre=" + nombre + ", aforo=" + aforo + ", ubicacion=" + ubicacion + "]";
    }
}
