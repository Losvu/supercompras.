package Modelo;
import Conexion.database;
import java.util.*;

public class DAOProducto {
    // Método insertar 
    public Producto Insertar(String nombre, String categoria, String precio, String marca, String stock) {
        String transaccion = "INSERT INTO Productos (nombre, categoria, precio_unitario, marca, stock) VALUES ('"
                + nombre + "', '"
                + categoria + "', '"
                + precio + "', '"
                + marca + "', '"
                + stock + "')";

        if (new database().Actualizar(transaccion) > 0) {
            return new Producto(nombre, categoria, Double.parseDouble(precio), marca, stock);
        }

        return null;
    }

    // Método actualizar
public int Actualizar(int id, String nombre, String categoria, double precio, String marca, String stock) {
    String transaccion = "UPDATE Productos SET nombre='"
            + nombre + "', categoria='"
            + categoria + "', precio_unitario='"
            + precio + "', marca='"
            + marca + "', stock='"
            + stock + "' WHERE id_producto=" + id;

    return new database().Actualizar(transaccion);
}
    // Método obtener productos
    public List<Producto> obtenerDatos() {
        String transaccion = "SELECT * FROM Productos";

        List<Map> registros = new database().Listar(transaccion);
        List<Producto> productos = new ArrayList();

        for (Map registro : registros) {
            Producto producto = new Producto((int) registro.get("id_producto"),
                    (String) registro.get("nombre"),
                    (String) registro.get("categoria"),
                    (double) registro.get("precio_unitario"),
                    (String) registro.get("marca"),
                    (String) registro.get("stock"));
            productos.add(producto);
        }

        return productos;
    }

    //metodo eliminar
    public int Eliminar(int id) {
        String transaccion = "DELETE FROM Productos WHERE id_producto=" + id;

        return new database().Actualizar(transaccion);
    }
}