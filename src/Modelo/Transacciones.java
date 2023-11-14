package Modelo;

import java.util.Date;

public class Transacciones {
    private int idTransaccion;
    private Date fechaHora;
    private String tipo;
    private double total;
    private String metodoPago;
    private int clienteId;
    private int proveedorId;

    // Constructor
    public Transacciones(int idTransaccion, Date fechaHora, String tipo, double total, String metodoPago, int clienteId, int proveedorId) {
        this.idTransaccion = idTransaccion;
        this.fechaHora = fechaHora;
        this.tipo = tipo;
        this.total = total;
        this.metodoPago = metodoPago;
        this.clienteId = clienteId;
        this.proveedorId = proveedorId;
    }
    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(int proveedorId) {
        this.proveedorId = proveedorId;
    }
}
