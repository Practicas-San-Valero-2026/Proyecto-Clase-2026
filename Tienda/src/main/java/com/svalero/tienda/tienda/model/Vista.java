package com.svalero.tienda.tienda.model;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;

public class Vista {

    private final String numeroPedido;
    private final boolean entregado;
    private final LocalDate fechaPedido;
    private final float precio;
    private final String observaciones;

    private final String nombre;
    private final String apellidos;
    private final LocalDate fechaNacimiento;
    private final String email;
    private final int telefono;

    public Vista(String numeroPedido, boolean entregado, LocalDate fechaPedido, float precio, String observaciones,
                 String nombre, String apellidos, LocalDate fechaNacimiento, String email, int telefono) {
        this.numeroPedido = numeroPedido;
        this.entregado = entregado;
        this.fechaPedido = fechaPedido;
        this.precio = precio;
        this.observaciones = observaciones;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.telefono = telefono;
    }

    public String getNumeroPedido() { return numeroPedido; }
    public boolean isEntregado() { return entregado; }
    public LocalDate getFechaPedido() { return fechaPedido; }
    public float getPrecio() { return precio; }
    public String getObservaciones() { return observaciones; }

    public String getNombre() { return nombre; }
    public String getApellidos() { return apellidos; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public String getEmail() { return email; }
    public int getTelefono() { return telefono; }


    }





