package kr.ac.jj.s201351029;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.table.DefaultTableModel;

public class SellDAO {
	String DB = "C:\\Users\\ANTAEHO\\pos.db";
	
	public SellDAO(){}
	
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
	
	public void s_insert(String group, String barcord, String name, int price, int bprice, int count) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO Sell(s_group,barcord,s_name,s_pri,s_bpri,s_count) VALUES(?,?,?,?,?,?)";
		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, group);
			ps.setString(2, barcord);
			ps.setString(3, name);
			ps.setInt(4, price);
			ps.setInt(5, bprice);
			ps.setInt(6, count);
			ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String[] searchProduct(String[] row,String barcord) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM product WHERE barcord = ?";
		
		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, barcord);
			rs = ps.executeQuery();
			while(rs.next()) {
				row = new String[7];
				row[0] = "";
				row[1] = rs.getString("barcord");
				row[2] = rs.getString("p_name");
				row[3] = rs.getString("p_pri");
				row[4] = "";
				row[5] = rs.getString("p_qua");
				row[6] = rs.getString("p_bpri");
			}
			rs.close();
			return row;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	public void Refresh(int rCount,String barcord) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "UPDATE product SET p_qua = ? WHERE barcord = ?";
		try {
			
			conn = getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, rCount);
			ps.setString(2, barcord);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public DefaultTableModel customer(String sDate,DefaultTableModel cModel) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT DISTINCT s_date FROM Sell WHERE date(s_date) = '"+sDate+"'AND s_group = '판매'";
		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			cModel.setNumRows(0);
			while(rs.next()) {
				String [] row = new String[8];
				row[0] = rs.getString("s_date");
				cModel.addRow(row);
			}
			rs.close();
			conn.close();
			return cModel;
		} catch (SQLException e) {}
		return cModel;
	}
	
	public DefaultTableModel reList(String sDate,DefaultTableModel reModel) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM Sell WHERE date(s_date) = '"+sDate+"' AND s_group = '반품'";
		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			reModel.setNumRows(0);
			while(rs.next()) {
				String [] row = new String[8];
				row[0] = rs.getString("s_date");
				row[1] = rs.getString("s_group");
				row[2] = rs.getString("barcord");
				row[3] = rs.getString("s_name");
				row[4] = rs.getString("s_pri");
				row[5] = rs.getString("s_bpri");
				row[6] = rs.getString("s_count");
//				System.out.println(row[6]+" "+row[4]);
				int a = Integer.parseInt(row[6]) * Integer.parseInt(row[4]);
//				System.out.println(a+"");
				row[7] = a+"";
				reModel.addRow(row);
			}
			rs.close();
			conn.close();
			return reModel;
		} catch (SQLException e) {}
		return reModel;
	}
	
	public void Return(String reTime) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "UPDATE Sell SET s_group = '반품' WHERE s_date = ?";
		
		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, reTime);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	public DefaultTableModel sellList(String sDate,DefaultTableModel slModel) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM Sell WHERE date(s_date) = '"+sDate+"' AND s_group = '판매'";
		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			slModel.setNumRows(0);
			while(rs.next()) {
				String [] row = new String[8];
				row[0] = rs.getString("s_date");
				row[1] = rs.getString("s_group");
				row[2] = rs.getString("barcord");
				row[3] = rs.getString("s_name");
				row[4] = rs.getString("s_pri");
				row[5] = rs.getString("s_bpri");
				row[6] = rs.getString("s_count");
				int a = Integer.parseInt(row[6]) * Integer.parseInt(row[4]);				row[7] = a+"";
				slModel.addRow(row);
			}
			rs.close();
			conn.close();
			return slModel;
		} catch (SQLException e) {}
		return slModel;
	}

}
