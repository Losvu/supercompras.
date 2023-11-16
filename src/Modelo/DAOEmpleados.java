package Modelo;

import Conexion.database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.DatabaseMetaData;

public class DAOEmpleados {

    private Connection connection;

    public DAOEmpleados() {
        this.connection = new database().getConnection();
    }


    public DAOEmpleados(Connection connection) {
        this.connection = connection;
    }


    // Nueva función para obtener el último ID insertado
    private int obtenerUltimoId() {
        String transaccion = "SELECT MAX(id_empleado) as max_id FROM Empleados";
        List<Map<String, Object>> registros = new database().Listar(transaccion);

        if (registros != null && registros.size() > 0) {
            return (int) registros.get(0).get("max_id");
        }

        return 0;
    }

    // Método insertar
    public Empleados Insertar(String nombre, String rol, LocalDateTime horarioInicio, LocalDateTime horarioFin, String diasTrabajo) {
        String query = "INSERT INTO Empleados (nombre, rol, horario_inicio, horario_fin, dias_trabajo) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, rol);
            preparedStatement.setObject(3, Timestamp.valueOf(horarioInicio));
            preparedStatement.setObject(4, Timestamp.valueOf(horarioFin));
            preparedStatement.setString(5, diasTrabajo);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int idEmpleado = generatedKeys.getInt(1);
                    return new Empleados(idEmpleado, nombre, rol, horarioInicio, horarioFin, diasTrabajo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Método actualizar
    public int Actualizar(int id, Map<String, Object> cambios) {
        try {
            StringBuilder query = new StringBuilder("UPDATE Empleados SET ");
            for (Map.Entry<String, Object> entry : cambios.entrySet()) {
                query.append(entry.getKey()).append("=?, ");
            }
            query.delete(query.length() - 2, query.length()); // Elimina la última coma
            query.append(" WHERE id_empleado=?");

            try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
                int parameterIndex = 1;
                for (Object value : cambios.values()) {
                    if (value instanceof LocalDateTime) {
                        preparedStatement.setObject(parameterIndex++, Timestamp.valueOf((LocalDateTime) value));
                    } else {
                        preparedStatement.setObject(parameterIndex++, value);
                    }
                }
                preparedStatement.setInt(parameterIndex, id);

                return preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0; // Devuelve 0 para indicar un error
        }
    }

    // Método obtenerDatos
public List<Empleados> obtenerDatos() {
    String transaccion = "SELECT * FROM Empleados";

    List<Map<String, Object>> registros = new database().Listar(transaccion);
    List<Empleados> empleados = new ArrayList<>();

    for (Map<String, Object> registro : registros) {
        LocalDateTime horarioInicio = obtenerLocalDateTimeDesdeObjeto(registro.get("horario_inicio"));
        LocalDateTime horarioFin = obtenerLocalDateTimeDesdeObjeto(registro.get("horario_fin"));

        Empleados empleado = new Empleados(
                (int) registro.get("id_empleado"),
                (String) registro.get("nombre"),
                (String) registro.get("rol"),
                horarioInicio,
                horarioFin,
                (String) registro.get("dias_trabajo")
        );

        empleados.add(empleado);
    }

    return empleados;
}

// Método auxiliar para obtener LocalDateTime desde un objeto
private LocalDateTime obtenerLocalDateTimeDesdeObjeto(Object objeto) {
    if (objeto instanceof Timestamp) {
        return ((Timestamp) objeto).toLocalDateTime();
    }
    return null;
}

    // Método eliminar
    public int Eliminar(int id) {
        String transaccion = "DELETE FROM Empleados WHERE id_empleado=" + id;

        return new database().Actualizar(transaccion);
    }
    
    
    //metodo para boton actualizar
   public int ActualizarRegistro(String nombreTabla, int id, Map<String, Object> cambios) throws SQLException {
    // Validar que haya cambios antes de intentar la actualización
    if (cambios.isEmpty()) {
        return 0;  // No hay cambios, retornar 0
    }

    // Crear la consulta de actualización
    StringBuilder consulta = new StringBuilder("UPDATE " + nombreTabla + " SET ");

    // Agregar las columnas y los nuevos valores a la consulta
    for (Map.Entry<String, Object> entry : cambios.entrySet()) {
        consulta.append(entry.getKey()).append(" = ?, ");
    }

    // Eliminar la coma extra al final
    consulta.delete(consulta.length() - 2, consulta.length());

    // Agregar la condición WHERE
    consulta.append(" WHERE id_empleado = ?");

    try (
        // Crear la conexión y el PreparedStatement
        Connection conexion = this.connection;
        PreparedStatement pstmt = conexion.prepareStatement(consulta.toString())
    ) {
        // Establecer los nuevos valores en la consulta
        int i = 1;
        for (Object valor : cambios.values()) {
            pstmt.setObject(i++, valor);
        }

        // Establecer el ID en la condición WHERE
        pstmt.setInt(i, id);

        // Ejecutar la consulta y devolver el número de filas afectadas
        return pstmt.executeUpdate();
    }
}
    // Método para obtener los nombres de las columnas de una tabla
public List<String> obtenerNombresColumnas(String nombreTabla) throws SQLException {
    List<String> columnas = new ArrayList<>();
    DatabaseMetaData metaData = null;

    try {
        metaData = connection.getMetaData();
        ResultSet resultSet = metaData.getColumns(null, null, nombreTabla, null);

        while (resultSet.next()) {
            String nombreColumna = resultSet.getString("COLUMN_NAME");
            columnas.add(nombreColumna);
        }
    } finally {
        // Cerrar la conexión en el bloque finally
        if (connection != null) {
            connection.close();
        }
    }

    return columnas;
}

}