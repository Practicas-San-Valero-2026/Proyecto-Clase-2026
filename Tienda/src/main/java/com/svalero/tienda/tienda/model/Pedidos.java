package com.svalero.tienda.tienda.model;

import java.time.LocalDate;

public class Pedidos {

    private String numPedido;
    private boolean entregado;
    private LocalDate fechaPedido;
    private float precio;
    private String observaciones;

    public Pedidos(String numPedido, boolean entregado, LocalDate fechaPedido, float precio, String observaciones) {
        this.numPedido = numPedido;
        this.entregado = entregado;
        this.fechaPedido = fechaPedido;
        this.precio = precio;
        this.observaciones = observaciones;
    }

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
}
