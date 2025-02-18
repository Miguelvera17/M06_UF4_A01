package com.iticbcn.miguel_vera.model;

import java.io.Serializable;

public class Socio implements Serializable{

    private int id;
    private Persona persona;
    private int edad;
    private String email;
    private String fechaInscripcion;
    private float cuota;
    private Gimnasio gimnasio;

    public Socio() {}

    public Socio(Persona persona, int edad, String email, String fechaInscripcion, float cuota, Gimnasio gimnasio) {
        this.persona = persona;
        this.edad = edad;
        this.email = email;
        this.fechaInscripcion = fechaInscripcion;
        this.cuota = cuota;
        this.gimnasio = gimnasio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(String fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public float getCuota() {
        return cuota;
    }

    public void setCuota(float cuota) {
        this.cuota = cuota;
    }

    public Gimnasio getGimnasio() {
        return gimnasio;
    }

    public void setGimnasio(Gimnasio gimnasio) {
        this.gimnasio = gimnasio;
    }

    @Override
    public String toString() {
        return "Socio [id=" + id + ", persona=" + persona 
        + ", edad=" + edad + ", email=" + email + ", fecha de inscripción=" + fechaInscripcion + ", cuota=" + cuota + ", gimnasio=" + gimnasio +"]";
    }
}

