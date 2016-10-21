package Actions;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class showDetails {
	private String ISBN;
	private String AuthorID;
	private String Publisher;
	private String PublishDate;
	private int Price;
	
	// JDBC 驱动名及数据库 URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_jinpengfei1";

	// 数据库的用户名与密码，需要根据自己的设置
	static final String USER = "l205ozozlw";
	static final String PASS = "zhl51klw1zy5l0zykkzkhl0kzm2jw5w2z44zz143";
	
	private String BookTitle = null;
	public String getBookTitle(){
		return BookTitle;
	}
	public void setBookTitle(String BookTitle){
		this.BookTitle = new String(BookTitle);
	}
	
	public String getISBN(){
		return ISBN;
	}
	public String getAuthorID(){
		return AuthorID;
	}
	public String getPublisher(){
		return Publisher;
	}
	public String getPublishDate(){
		return PublishDate;
	}
	public int getPrice(){
		return Price;
	}
	
	Connection con;
	Statement st;
	public static Connection getConnection(){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
		}catch(Exception e){
			System.out.println("Connect Fail:"+e.getMessage());
		}
		return con;
	}
	
	public String execute()throws Exception{
		con = getConnection();
		System.out.println(BookTitle);
		try{
			String sql = "select * from Book";
			st = (Statement)con.createStatement();
			ResultSet res = st.executeQuery(sql);
			while(res.next()){
				if(BookTitle.equals(res.getString("Title"))){
					ISBN = new String(res.getString("ISBN"));
					AuthorID = new String(res.getString("AuthorID"));
					Publisher = new String(res.getString("Publisher"));
					PublishDate = new String(res.getString("PublishDate"));
					Price = res.getInt("Price");
				}
			}
			con.close();
			return "Success";
		}catch(SQLException e){
			System.out.println("Connect Failed."+e.getMessage());
			return "Failed";
		}
	}
}
