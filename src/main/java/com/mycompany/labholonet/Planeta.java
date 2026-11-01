package com.mycompany.Labholonet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "planetas")
public class Planeta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planeta_id")
    private int id;

    @Column(name = "nombre", nullable = false, unique = true, length = 100)
    private String nombre;

    @Column(name = "afiliacion", length = 50)
    private String afiliacion;

    // Constructor obligatorio JPA
    public Planeta() {}

    public Planeta(String nombre, String afiliacion) {
        this.nombre = nombre;
        this.afiliacion = afiliacion;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getAfiliacion() { return afiliacion; }
    public void setAfiliacion(String afiliacion) { this.afiliacion = afiliacion; }

    @Override
    public String toString() {
        return "Planeta [ID=" + id + ", Nombre=" + nombre + ", Afiliación=" + afiliacion + "]";
    }
}
