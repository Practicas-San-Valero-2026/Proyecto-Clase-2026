package com.svalero.tienda.tienda.model;

public class Productos {

    private String nombre;
    private String tipo;
    private float precio;
    private boolean stock;
    private String descripcion;

    public Productos(String nombre, String tipo, float precio, boolean stock, String descripcion) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.stock = stock;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public boolean isStock() {
        return stock;
    }

    public void setStock(boolean stock) {
        this.stock = stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
