package Modelo;
import Conexion.database;
import java.util.*;
import java.sql.Date;

public class DAOProducto {

    public Producto Insertar(String nombre, String categoria, double precioUnitario, String marca, int relacionTablaId) {
        String transaccion = "INSERT INTO Productos (nombre, categoria, precio_unitario, marca, relacion_tabla_id) VALUES ('"
            + nombre + "', '"
            + categoria + "', "
            + precioUnitario + ", '"
            + marca + "', "
            + relacionTablaId + ")";

        if (new database().Actualizar(transaccion) > 0) {
            return new Producto(relacionTablaId, nombre, categoria, precioUnitario, marca);
        }
        return null;
    }

    public int Actualizar(int id, String nombre, String categoria, double precioUnitario, String marca, int relacionTablaId) {
        String transaccion = "UPDATE Productos SET nombre='"
            + nombre + "', categoria='"
            + categoria + "', precio_unitario="
            + precioUnitario + ", marca='"
            + marca + "', relacion_tabla_id="
            + relacionTablaId + " WHERE id_producto="
            + id;

        return new database().Actualizar(transaccion);
    }

    public List<Producto> ObtenerDatos() {
        String transaccion = "SELECT * FROM Productos";

        List<Map> registros = new database().Listar(transaccion);
        List<Producto> productos = new ArrayList();

        for (Map registro : registros) {
            Producto producto = new Producto((int) registro.get("id_producto"),
                (String) registro.get("nombre"),
                (String) registro.get("categoria"),
                (double) registro.get("precio_unitario"),
                (String) registro.get("marca"));
            productos.add(producto);
        }

        return productos;
    }

    public int Eliminar(int id) {
        String transaccion = "DELETE FROM Productos WHERE id_producto=" + id;

        return new database().Actualizar(transaccion);
    }
}

