package Actions;


import com.mysql.jdbc.Connection;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.*;
import java.util.ArrayList;

public class query extends ActionSupport {
	
	// JDBC 驱动名及数据库 URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_jinpengfei1";

	// 数据库的用户名与密码，需要根据自己的设置
	static final String USER = "l205ozozlw";
	static final String PASS = "zhl51klw1zy5l0zykkzkhl0kzm2jw5w2z44zz143";
	
	private String name;

	private ArrayList<String> BookList = new ArrayList<String>();

	public String execute() {
		Connection conn = null;
		Statement stmt = null;
		//String name1 = name;
		try{
			try{
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("连接数据库...");
				conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
			}catch(Exception e){
				System.out.println("Connect Fail:"+e.getMessage());
				return ERROR;
			}
			

			// 执行查询
			System.out.println(" 实例化Statement对...");
			//stmt = conn.createStatement();
			
			
			String sql = "SELECT AuthorID FROM Author WHERE";
			sql += " Name = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rsl = ps.executeQuery();
			
			int id =0;
			while(rsl.next()){
				 id  = rsl.getInt("AuthorID");
			}
			if(id==0)
			{
				return ERROR;
			}
			System.out.println("AuthorID"+ id);
			rsl.close();
			String s = Integer.toString(id);
			
			sql = "SELECT * FROM Book WHERE";
			sql += " AuthorID = ?";
			PreparedStatement pss = conn.prepareStatement(sql);
			pss.setString(1, s);
			ResultSet rs = pss.executeQuery();

			// 展开结果集数据库
			while(rs.next()){
				String ISBN = rs.getString("ISBN");
				String Title = rs.getString("Title");
				String AuthorID = rs.getString("AuthorID");
				String Publisher = rs.getString("Publisher");
				String date = rs.getString("PublishDate");
				float Price = rs.getFloat("Price");
				
				BookList.add(new String(rs.getString("Title")));
				// 输出数据
				System.out.print("ISBN: " + ISBN);
				System.out.print(", Title: " + Title);
				System.out.print(", AuthorID: " + AuthorID);
				System.out.print(", Publisher: " + Publisher);
				System.out.print(", date: " + date);
				System.out.println(", Price: " + Price);
			}
			// 完成后关闭
			rs.close();
			//stmt.close();
			conn.close();
		}catch(SQLException se){
			// 处理 JDBC 错误
			se.printStackTrace();
			return ERROR;
		}catch(Exception e){
			// 处理 Class.forName 错误
			e.printStackTrace();
			return ERROR;
		}finally{
			// 关闭资源
			try{
				if(stmt!=null) stmt.close();
			}catch(SQLException se2){
			}// 什么都不做
		}
		return SUCCESS;
	}
	
	public String getName() {
	      return name;
	   }
	public void setName(String name) {
	      this.name = name;
	   }
	public ArrayList<String> getBookList(){
		return BookList;
	}
	
}