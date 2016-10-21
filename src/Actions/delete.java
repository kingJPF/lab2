package Actions;

import com.opensymphony.xwork2.ActionSupport;

import java.sql.*;

public class delete{
	private String ISBN;
	
	public void setISBN(String ISBN){
		this.ISBN = new String(ISBN);
	}
	public String getISBN(){
		return ISBN;
	}
	
	Connection con;
	Statement st;
	public static Connection getConnection(){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_jinpengfei1","l205ozozlw","zhl51klw1zy5l0zykkzkhl0kzm2jw5w2z44zz143");
		}catch(Exception e){
			System.out.println("Connect Fail:"+e.getMessage());
		}
		return con;
	}
	
	public String execute()throws Exception{
		con = getConnection();
		try{
			String sql = "delete from Book where ISBN="+"'"+ISBN+"'";
			System.out.println(sql);
			st = (Statement)con.createStatement();
			int count = st.executeUpdate(sql);
			System.out.println("Delete Successful.Delete DataNum:"+count);
			con.close();
			return "Success";
		}catch(SQLException e){
			return "Failed";
		}
	}
}