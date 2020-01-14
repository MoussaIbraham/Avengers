package Liverpool;

import java.sql.*;

import javax.swing.JOptionPane;

public class ConexionConBD {
	Connection conexion;
	Statement sentencia;
	ResultSet rs;
	Modelo mimodelo;

	public ConexionConBD(Modelo mimodelo) {
		this.mimodelo=mimodelo;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				conexion = DriverManager.getConnection("jdbc:mysql://localhost/liverpool", "root", ""); // Conecta a la BD
				sentencia = conexion.createStatement(); 
				rs= sentencia.getResultSet();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,mimodelo.getTextoErrorBd1()); 
			}
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,mimodelo.getTextoErrorBd2()); 
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
