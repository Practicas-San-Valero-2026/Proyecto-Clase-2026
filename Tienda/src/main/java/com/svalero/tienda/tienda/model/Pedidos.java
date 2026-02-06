package com.svalero.tienda.tienda.model;

import java.time.LocalDate;

public class Pedidos {

    private int id;
    private String numPedido;
    private boolean entregado;
    private LocalDate fechaPedido;
    private float precio;
    private String observaciones;
    private int idCliente;

    public Pedidos(int id, String numPedido, boolean entregado, LocalDate fechaPedido, float precio, String observaciones, int idCliente) {
        this.id = id;
        this.numPedido = numPedido;
        this.entregado = entregado;
        this.fechaPedido = fechaPedido;
        this.precio = precio;
        this.observaciones = observaciones;
        this.idCliente = idCliente;
    }

    public Pedidos() {

    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(String numPedido) {
        this.numPedido = numPedido;
    }

    public boolean isEntregado() {
        return entregado;
    }

    public void setEntregado(boolean entregado) {
        this.entregado = entregado;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getIdCliente() { return idCliente; }

    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
}
