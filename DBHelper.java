package util;

import java.sql.Connection;
import java.sql.DriverManager;

import entity.Items;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBHelper {
   
		private static final String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		private static final String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=shop";
		private static final String userName="sa";
		private static final String userPwd="asd07412";
	
		private static Connection dbConn=null;
		
		//静态代码块负责加载驱动
		static 
		{
			try
			{
				Class.forName(driverName);
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		
		//单例模式返回数据库连接对象
		public static Connection getConnection() throws Exception
		{
			if(dbConn==null)
			{
				dbConn = DriverManager.getConnection(dbURL,userName,userPwd);
				return dbConn;
			}
			return dbConn;
		}
		
		
		
		//测试
		public static void main(String[] args) {
			
			try
			{
			   Connection conn = DBHelper.getConnection();
			   if(conn!=null)
			   {
				   System.out.println("数据库连接正常！");
				   String str="select * from items";
				   Statement statement = null;
				   statement = dbConn.createStatement();
			       ResultSet rs=statement.executeQuery(str);
			       while(rs.next())
			        {
			        	System.out.print(rs.getInt(1)+"\t");
						System.out.print(rs.getString(2)+"\t");
						System.out.print(rs.getString(3)+"\t");
						System.out.print(rs.getInt(4)+"\t");
						System.out.print(rs.getInt(5)+"\t");
						System.out.print(rs.getString(6)+"\t");
						System.out.println();
						
			        }
			        statement.close();
			   }
			   else
			   {
				   System.out.println("数据库连接异常！");
			   }
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
		}
		
		
		
		
	
	
	
}	
