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


public class DAOCliente {
    
    
// Este método devuelve una lista de nombres de clientes
public List<String> obtenerNombresClientes() {
    String transaccion = "SELECT nombre FROM Clientes";
    List<Map<String, Object>> registros = new database().Listar(transaccion);
    List<String> nombresClientes = new ArrayList<>();

    for (Map<String, Object> registro : registros) {
        String nombre = (String) registro.get("nombre");
        nombresClientes.add(nombre);
    }

    return nombresClientes;
}
    
    
    private Connection connection;

    
    public DAOCliente(Connection connection) {
        this.connection = connection;
    }
    
    
    
    // metodo para obtener todos los clientes
// método para obtener todos los clientes
public List<Cliente> obtenerDatos() {
    String transaccion = "SELECT * FROM Clientes";

    List<Map<String, Object>> registros = new database().Listar(transaccion);
    List<Cliente> clientes = new ArrayList<>();

    for (Map<String, Object> registro : registros) {
        Cliente cliente = new Cliente(
                (int) registro.get("id_cliente"),
                (String) registro.get("nombre"),
                (String) registro.get("direccion"),
                (String) registro.get("numero_telefono"),
                (String) registro.get("correo_electronico")
        );
        clientes.add(cliente);
    }

    return clientes;
}
    
    //metodo para Transacciones
public Cliente obtenerClientePorNombre(String nombre) {
    String transaccion = "SELECT * FROM Clientes WHERE nombre = ?";
    List<Map<String, Object>> registros = new database().Listar(transaccion, nombre);

    if (registros != null && registros.size() > 0) {
        Map<String, Object> registro = registros.get(0);
        return new Cliente(
                (int) registro.get("id_cliente"),
                (String) registro.get("nombre"),
                (String) registro.get("direccion"),
                (String) registro.get("numero_telefono"),
                (String) registro.get("correo_electronico")
        );
    }

    return null;
}

    // Método para obtener un cliente por ID
    public Cliente obtenerClientePorId(int id) {
        String transaccion = "SELECT * FROM Clientes WHERE id_cliente = " + id;
        List<Map<String, Object>> registros = new database().Listar(transaccion);

        if (registros != null && registros.size() > 0) {
            Map registro = registros.get(0);
            return new Cliente(
                    (int) registro.get("id_cliente"),
                    (String) registro.get("nombre"),
                    (String) registro.get("direccion"),
                    (String) registro.get("numero_telefono"),
                    (String) registro.get("correo_electronico")
            );
        }

        return null;
    }

    // Método para insertar un nuevo cliente
// Método para insertar un nuevo cliente
public Cliente insertarCliente(String nombre, String direccion, String numeroTelefono, String correoElectronico) {
    String query = "INSERT INTO Clientes (nombre, direccion, numero_telefono, correo_electronico) VALUES (?, ?, ?, ?)";

    try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
        preparedStatement.setString(1, nombre);
        preparedStatement.setString(2, direccion);
        preparedStatement.setString(3, numeroTelefono);
        preparedStatement.setString(4, correoElectronico);

        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idCliente = generatedKeys.getInt(1);
                return new Cliente(idCliente, nombre, direccion, numeroTelefono, correoElectronico);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null;
}
    // Método para actualizar un cliente
    public int actualizarCliente(int id, Map<String, Object> cambios) {
    try {
        StringBuilder query = new StringBuilder("UPDATE Clientes SET ");
        for (Map.Entry<String, Object> entry : cambios.entrySet()) {
            query.append(entry.getKey()).append("=?, ");
        }
        query.delete(query.length() - 2, query.length()); // Elimina la última coma
        query.append(" WHERE id_cliente=?");

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

    //metodo para eliminar un cliente por ID
    public int eliminarCliente(int id) {
        String transaccion = "DELETE FROM Clientes WHERE id_cliente=" + id;
        return new database().Actualizar(transaccion);
    }
}
