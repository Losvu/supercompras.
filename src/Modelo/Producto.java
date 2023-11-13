package Modelo;

public class Producto {
    
    private int id_producto; // Identificador del producto
    private String nombre;
    private String categoria;
    private double precio_unitario;
    private String marca;
    private String stock; // Cantidad en stock como cadena

    public Producto() {
    }

    public Producto(int id_producto, String nombre, String categoria, double precio_unitario, String marca) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio_unitario = precio_unitario;
        this.marca = marca;
        this.stock = "0"; // Inicialmente, el stock se establece en 0.
    }

    public Producto(String nombre, String categoria, double precio_unitario, String marca, String stock) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio_unitario = precio_unitario;
        this.marca = marca;
        this.stock = stock;
    }

    public Producto(int id_producto, String nombre, String categoria, double precio_unitario, String marca, String stock) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio_unitario = precio_unitario;
        this.marca = marca;
        this.stock = stock;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    // Métodos relacionados con la gestión de inventarios
    public void agregarStock(int cantidad) {
        int stockActual = Integer.parseInt(stock);
        stockActual += cantidad;
        stock = String.valueOf(stockActual);
    }
    
    public void reducirStock(int cantidad) {
        int stockActual = Integer.parseInt(stock);
        if (stockActual >= cantidad) {
            stockActual -= cantidad;
            stock = String.valueOf(stockActual);
        } else {
            System.out.println("No hay suficiente stock disponible.");
        }
    }
}