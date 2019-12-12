package Liverpool;

import java.sql.*;

public class ConexionConBD {
	Connection conexion;
	Statement sentencia;
	ResultSet rs;

	public ConexionConBD() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				conexion = DriverManager.getConnection("jdbc:mysql://localhost/liverpool", "root", ""); // Conecta a la BD
				sentencia = conexion.createStatement(); 
				rs= sentencia.getResultSet();
			} catch (SQLException e) {
				System.out.println("Error al encontrar la Base de Datos");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Error al encontrar el driver de la Base de Datos");
		}
	}
	
	public void cerrarConexion() throws SQLException {
		rs.close();
		sentencia.close();
		conexion.close();
	}
	
	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
}
