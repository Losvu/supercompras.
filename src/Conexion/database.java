package Conexion;

import java.sql.*;
import java.util.*;
import java.math.BigDecimal;
import java.util.Date;

public class database {

    private final String url = "jdbc:mysql://localhost:3306/dbsupercompras";
    private final String user = "sandia";
    private final String password = "ytz12345678";

    private Connection conexion;

    public Connection obtenerConexion() {
        return this.conexion;
    }

    public Connection getConnection() {
        return this.conexion;
    }

    public database() {
        try {
            conexion = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión establecida");
        } catch (SQLException e) {
            System.out.println("Error de conexión");
            e.printStackTrace();
        }
    }

    public void cerrarConexion() {
        try {
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int Actualizar(String consulta, Object... parametros) {
        int filasAfectadas = 0;
        try (Connection conexion = obtenerConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement(consulta)) {

            for (int i = 0; i < parametros.length; i++) {
                preparedStatement.setObject(i + 1, parametros[i]);
            }

            filasAfectadas = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return filasAfectadas;
    }

    private List<Map<String, Object>> organizarDatos(ResultSet rs) {
        List<Map<String, Object>> filas = new ArrayList<>();
        try {
            int numColumnas = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                Map<String, Object> renglon = new HashMap<>();
                for (int i = 1; i <= numColumnas; i++) {
                    String nombreCampo = rs.getMetaData().getColumnName(i);
                    Object valor = rs.getObject(nombreCampo);
                    renglon.put(nombreCampo, valor);
                }
                filas.add(renglon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filas;
    }

    public List<Map<String, Object>> Listar(String consulta, Object... parametros) {
        ResultSet rs = null;
        List<Map<String, Object>> resultado = new ArrayList<>();
        try {
            try (PreparedStatement preparedStatement = conexion.prepareStatement(consulta)) {
                for (int i = 0; i < parametros.length; i++) {
                    preparedStatement.setObject(i + 1, parametros[i]);
                }

                rs = preparedStatement.executeQuery();

                resultado = organizarDatos(rs);

            } catch (SQLException e) {
                System.out.println("Error al ejecutar la consulta preparada");
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("No se realizó la conexión");
            e.printStackTrace();
        } finally {
            // No cierra el ResultSet aquí para permitir su uso externo si es necesario
        }
        return resultado;
    }
}