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
import java.sql.DatabaseMetaData;


public class DAOProveedores {

    private Connection connection;

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

    List<Map<String, Object>> registros = new database().Listar(transaccion);
    List<Proveedores> proveedores = new ArrayList<>();

    for (Map<String, Object> registro : registros) {
        Proveedores proveedor = new Proveedores(
                (int) registro.get("id_proveedor"),
                (String) registro.get("nombre"),
                (String) registro.get("direccion"),
                (String) registro.get("numero_telefono"),
                (String) registro.get("correo_electronico"),
                (String) registro.get("marca_proveedor")
        );
        proveedores.add(proveedor);

        // Agrega impresiones para depuración
        System.out.println("Proveedor ID: " + proveedor.getId_proveedor());
        System.out.println("Nombre: " + proveedor.getNombre());
        System.out.println("Dirección: " + proveedor.getDireccion());
        System.out.println("Número de Teléfono: " + proveedor.getNumero_telefono());
        System.out.println("Correo Electrónico: " + proveedor.getCorreo_electronico());
        System.out.println("Marca Proveedor: " + proveedor.getMarca_proveedor());
    }

    return proveedores;
}

    // Método para obtener nombres de proveedores
    public List<String> obtenerNombresProveedores() {
        String transaccion = "SELECT nombre FROM Proveedores";
        List<Map<String, Object>> registros = new database().Listar(transaccion);
        List<String> nombresProveedores = new ArrayList<>();

        for (Map<String, Object> registro : registros) {
            nombresProveedores.add((String) registro.get("nombre"));
        }

        return nombresProveedores;
    }

    // Método para obtener un proveedor por ID
    public Proveedores obtenerProveedorPorId(int id) {
        String transaccion = "SELECT * FROM Proveedores WHERE id_proveedor = " + id;
        List<Map<String, Object>> registros = new database().Listar(transaccion);

        if (registros != null && registros.size() > 0) {
            Map<String, Object> registro = registros.get(0);
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
    //metodo para obtener proveedor por nombre, presente en Transacciones
public Proveedores obtenerProveedorPorNombre(String nombre) {
    Proveedores proveedor = null;
    String sql = "SELECT id_proveedor, nombre, direccion, numero_telefono, correo_electronico, marca_proveedor FROM proveedores WHERE nombre = ?";

    try (Connection conexion = new database().obtenerConexion();
         PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {

        preparedStatement.setString(1, nombre);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                int idProveedor = resultSet.getInt("id_proveedor");
                String direccion = resultSet.getString("direccion");
                String numeroTelefono = resultSet.getString("numero_telefono");
                String correoElectronico = resultSet.getString("correo_electronico");
                String marcaProveedor = resultSet.getString("marca_proveedor");

                proveedor = new Proveedores(idProveedor, nombre, direccion, numeroTelefono, correoElectronico, marcaProveedor);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return proveedor;
}


// Método para verificar la existencia de una columna en una tabla
private boolean existeColumna(String nombreColumna, String nombreTabla) {
    try {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet resultSet = metaData.getColumns(null, null, nombreTabla, null);

        while (resultSet.next()) {
            String nombre = resultSet.getString("COLUMN_NAME");
            if (nombreColumna.equals(nombre)) {
                return true;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

//metodo para actualizar un proveedor, enrealidad es editar, pero actualizar me gusta mas como palabra
public int actualizarProveedor(int id, Map<String, Object> cambios) {
    try {
        StringBuilder query = new StringBuilder("UPDATE Proveedores SET ");
        for (Map.Entry<String, Object> entry : cambios.entrySet()) {
            query.append(entry.getKey()).append("=?, ");
        }
        query.delete(query.length() - 2, query.length()); //elimina la última coma
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
        return 0; //devuelve 0 para indicar un error, viste?
    }
}

    // Método para eliminar un proveedor por ID
    public int eliminarProveedor(int id) {
        String transaccion = "DELETE FROM Proveedores WHERE id_proveedor=" + id;
        return new database().Actualizar(transaccion);
    }
}