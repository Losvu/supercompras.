package Modelo;

import java.sql.Connection;
import Conexion.database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime; // Importante agregar esta línea
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.Time;

public class DAOEmpleados {
    private Connection connection;

    public DAOEmpleados() {
    }

    public DAOEmpleados(Connection connection) {
        this.connection = connection;
    }

    // Nueva función para obtener el último ID insertado
    private int obtenerUltimoId() {
        String transaccion = "SELECT MAX(id_empleado) as max_id FROM Empleados";
        List<Map> registros = new database().Listar(transaccion);

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

    //mtodo obtenerDatos
public List<Empleados> obtenerDatos() {
    String transaccion = "SELECT * FROM Empleados";

    List<Map> registros = new database().Listar(transaccion);
    List<Empleados> empleados = new ArrayList<>();

    for (Map registro : registros) {
        Empleados empleado = new Empleados(
            (int) registro.get("id_empleado"),
            (String) registro.get("nombre"),
            (String) registro.get("rol"),
            (LocalDateTime) registro.get("horario_inicio"),
            (LocalDateTime) registro.get("horario_fin"),
            (String) registro.get("dias_trabajo")
        );

        // Verifica si horario_inicio o horario_fin es null antes de mostrarlos
        LocalDateTime horarioInicio = empleado.getHorario_inicio();
        LocalDateTime horarioFin = empleado.getHorario_fin();

        if (horarioInicio != null) {
            System.out.println("Horario inicio: " + horarioInicio.toString());
        } else {
            System.out.println("Horario inicio: No disponible");
        }

        if (horarioFin != null) {
            System.out.println("Horario fin: " + horarioFin.toString());
        } else {
            System.out.println("Horario fin: No disponible");
        }

        empleados.add(empleado);
    }

    return empleados;
}
    // Método eliminar
    public int Eliminar(int id) {
        String transaccion = "DELETE FROM Empleados WHERE id_empleado=" + id;

        return new database().Actualizar(transaccion);
    }
}