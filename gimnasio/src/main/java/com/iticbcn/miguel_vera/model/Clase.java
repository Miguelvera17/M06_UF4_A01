package com.iticbcn.miguel_vera.model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity
@Table(name="clase")
public class Clase implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column
    private String  nombre;
    @Column
    private String  horario;
    @Column
    private int aforo;
    
    public Clase() {}

    public Clase(String nombre, String horario, int aforo) {
        this.nombre = nombre;
        this.horario = horario;
        this.aforo = aforo;
    }

    public int getIdClase() {
        return id;
    }

    public void setIdClase(int id) {
        this.id = id;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
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

    @Override
    public String toString() {
        return "Clase [id=" + id + ", nombre=" + nombre + ", horario=" + horario +", aforo=" + aforo + "]";
    }
}
