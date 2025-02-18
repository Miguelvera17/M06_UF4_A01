package com.iticbcn.miguel_vera.model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="persona")
public class Persona implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column
    private String  DNI;
    @Column
    private String  nombre;
    @Column
    private int telefono;

    public Persona() {}

    public Persona(String DNI, String nombre, int telefono) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public int getIdPersona() {
        return id;
    }

    public void setIdPersona(int id) {
        this.id = id;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Persona [id=" + id + ", nombre=" + nombre + ", telefono=" + telefono +"]";
    }
}
