package kr.ac.jj.s201351029;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ProductDAO {
	String DB = "C:\\Users\\ANTAEHO\\pos.db";
	
	public ProductDAO(){
		
	}
	
	public Connection getConn() {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:"+DB);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	
	public void insert(ProductDTO pd)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO product(barcord,p_name,p_pri,p_bpri,p_qua) VALUES (?,?,?,?,?)";
		
		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, pd.getBarcord());
			ps.setString(2, pd.getP_name());
			ps.setInt(3, pd.getP_pri());
			ps.setInt(4, pd.getP_bpri());
			ps.setInt(5, pd.getP_qua());
			ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "바코드가 중복입니다.", "경고", JOptionPane.INFORMATION_MESSAGE);
			//e.printStackTrace();
		}
	}
	
	
	
	public void update(int u_p_no ,String u_barcord,String u_p_name, int u_p_pri, int u_p_bpri, int u_p_qua) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "update product set barcord = ?,"
				+ "p_name = ?,"
				+ "p_pri = ?,"
				+ "p_bpri = ?,"
				+ "p_qua = ?"
				+ "where p_no = ?";
		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, u_barcord);
			ps.setString(2, u_p_name);
			ps.setInt(3, u_p_pri);
			ps.setInt(4, u_p_bpri);
			ps.setInt(5, u_p_qua);
			ps.setInt(6, u_p_no);
			ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	DefaultTableModel selectProduct(DefaultTableModel model) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from product";
		
		try {
			conn = getConn();
			ps = conn.prepareStatement(sql); 
			rs = ps.executeQuery();
			model.setNumRows(0);
			while(rs.next()) {
				String [] row = new String[6];
				row[0] = rs.getString("p_no");
				row[1] = rs.getString("barcord");
				row[2] = rs.getString("p_name");
				row[3] = rs.getString("p_pri");
				row[4] = rs.getString("p_bpri");
				row[5] = rs.getString("p_qua");
				model.addRow(row);
			}
			rs.close();
			conn.close();
			return model;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}

	public void delete(int d_p_no) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "DELETE FROM product WHERE p_no = ?";
		
		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, d_p_no);
			ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public DefaultTableModel searchProduct(DefaultTableModel model, String barcord1) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from product WHERE barcord = ?";
		
		try {
			conn = getConn();
			ps = conn.prepareStatement(sql); 
			ps.setString(1, barcord1);
			rs = ps.executeQuery();
			model.setNumRows(0);
			while(rs.next()) {
				String [] row = new String[6];
				row[0] = rs.getString("p_no");
				row[1] = rs.getString("barcord");
				row[2] = rs.getString("p_name");
				row[3] = rs.getString("p_pri");
				row[4] = rs.getString("p_bpri");
				row[5] = rs.getString("p_qua");
				model.addRow(row);
			}
			
			rs.close();
			conn.close();
			return model;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}

}
