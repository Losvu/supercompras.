package Modelo;

import Conexion.database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DAOProveedores {

    private Connection connection;

    public DAOProveedores() {
    }

    public DAOProveedores(Connection connection) {
        this.connection = connection;
    }

    // Método para insertar un nuevo proveedor
   public Proveedores insertarProveedor(String nombre, String direccion, String numeroTelefono, String correoElectronico, String marcaProveedor) {
        String query = "INSERT INTO Proveedores (nombre, direccion, numero_telefono, correo_electronico, marca_proveedor) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, direccion);
            preparedStatement.setString(3, numeroTelefono);
            preparedStatement.setString(4, correoElectronico);
            preparedStatement.setString(5, marcaProveedor);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int idProveedor = generatedKeys.getInt(1);
                    return new Proveedores(idProveedor, nombre, direccion, numeroTelefono, correoElectronico, marcaProveedor);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    // Método para obtener todos los proveedores
    public List<Proveedores> obtenerDatos() {
        String transaccion = "SELECT * FROM Proveedores";

        List<Map> registros = new database().Listar(transaccion);
        List<Proveedores> proveedores = new ArrayList<>();

        for (Map registro : registros) {
            Proveedores proveedor = new Proveedores(
                    (int) registro.get("id_proveedor"),
                    (String) registro.get("nombre"),
                    (String) registro.get("direccion"),
                    (String) registro.get("numero_telefono"),
                    (String) registro.get("correo_electronico"),
 
                    (String) registro.get("marca_proveedor")
            );
            proveedores.add(proveedor);
        }

        return proveedores;
    }

  //método para obtener un proveedor por ID
public Proveedores obtenerProveedorPorId(int id) {
    String transaccion = "SELECT * FROM Proveedores WHERE id_proveedor = " + id;
    List<Map> registros = new database().Listar(transaccion);

    if (registros != null && registros.size() > 0) {
        Map registro = registros.get(0);
        return new Proveedores(
                (int) registro.get("id_proveedor"),
                (String) registro.get("nombre"),
                (String) registro.get("direccion"),
                (String) registro.get("numero_telefono"),
                (String) registro.get("correo_electronico"),
                (String) registro.get("marca_proveedor")
        );
    }

    return null;
}
    // Método para actualizar un proveedor
    public int actualizarProveedor(int id, Map<String, Object> cambios) {
        try {
            StringBuilder query = new StringBuilder("UPDATE Proveedores SET ");
            for (Map.Entry<String, Object> entry : cambios.entrySet()) {
                query.append(entry.getKey()).append("=?, ");
            }
            query.delete(query.length() - 2, query.length()); // Elimina la última coma
            query.append(" WHERE id_proveedor=?");

            try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
                int parameterIndex = 1;
                for (Object value : cambios.values()) {
                    preparedStatement.setObject(parameterIndex++, value);
                }
                preparedStatement.setInt(parameterIndex, id);

                return preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0; // Devuelve 0 para indicar un error
        }
    }

    // Método para eliminar un proveedor por ID
    public int eliminarProveedor(int id) {
        String transaccion = "DELETE FROM Proveedores WHERE id_proveedor=" + id;
        return new database().Actualizar(transaccion);
    }
}