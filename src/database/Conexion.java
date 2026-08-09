package database;

import java.sql.*;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Conexion {

    private static final String DATABASE = "proyecto_final"; //AQUÍ VA EL NOMBRE DE LA BASE DE DATOS
    private static final String USUARIO = "postgres"; //AQUÍ PONER SU USUARIO DEL SERVIDOR DE POSTGRES
    private static final String PASS = "POSTGRES"; // AQUÍ LA CONTRASEÑA DEL SERVIDOR DE POSTGRES
    private static final String URL = "jdbc:postgresql://localhost:5432/" + DATABASE;

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASS);
    }

    // Método para SELECT
    public static List<Map<String, Object>> query(String sql) {
        List<Map<String, Object>> lista = new ArrayList<>();

        try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                Map<String, Object> fila = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    fila.put(metaData.getColumnName(i), rs.getObject(i));
                }
                lista.add(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error en query: " + e.getMessage());
        }
        return lista;
    }

    // Método para INSERT, UPDATE, DELETE
    public static int ejecutar(String sql) {
        try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            String sqlState = e.getSQLState();
            String msgOut;
            switch (sqlState) {
                case "23505":
                    msgOut = "Error: El registro ya existe (clave o índice único duplicado).";
                    break;
                case "23503":
                    msgOut = "Error: No se puede eliminar/insertar por restricción de clave foránea.";
                    break;
                case "23502":
                    msgOut = "Error: Un campo obligatorio no puede ser nulo.";
                    break;
                default:
                    msgOut = "Error de PostgreSQL [" + sqlState + "]: " + e.getMessage();
                    break;
            }
            JOptionPane.showMessageDialog(new JFrame(), msgOut);
            System.err.println("Error en Postgres: " + msgOut);
            return 0;
        }
    }
}
