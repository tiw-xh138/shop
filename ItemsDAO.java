package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import util.DBHelper;
import entity.Items;

public class ItemsDAO {
	
	public ArrayList<Items> getAllItems() {
		Connection dbConn = null;
		Statement statement = null;
		ResultSet rs = null;
		ArrayList<Items> list = new ArrayList<Items>(); // 商品集合
		try {
			dbConn = DBHelper.getConnection();
			String sql = "select * from items;"; // SQL语句
			statement = dbConn.createStatement();
			rs=statement.executeQuery(sql);
			
			
			while (rs.next()) {
				Items item = new Items();
				item.setId(rs.getInt(1));
				item.setName(rs.getString(2));
				item.setCity(rs.getString(3));
				item.setNumber(rs.getInt(4));
				item.setPrice(rs.getInt(5));
				item.setPicture(rs.getString(6));
				list.add(item);// 把一个商品加入集合
			}
			return list; // 返回集合。
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			// 释放数据集对象
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			// 释放语句对象
			if (statement != null) {
				try {
					statement.close();
					statement = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}

	}
	
	// 根据商品编号获得商品资料
	public Items getItemsById(int id) {
		Connection dbConn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		ArrayList<Items> list = new ArrayList<Items>(); // 商品集合
		try {
			dbConn = DBHelper.getConnection();
			String sql = "select * from items where id=?;"; // SQL语句
			
			statement = dbConn.prepareStatement(sql);
			statement.setInt(1, id);
			rs=statement.executeQuery();
			
			
			if (rs.next()) {
				Items item = new Items();
				item.setId(rs.getInt(1));
				item.setName(rs.getString(2));
				item.setCity(rs.getString(3));
				item.setNumber(rs.getInt(4));
				item.setPrice(rs.getInt(5));
				item.setPicture(rs.getString(6));
				return item;
			}else {
				return null;
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}finally {
			// 释放数据集对象
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			// 释放语句对象
			if (statement != null) {
				try {
					statement.close();
					statement = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		}
	}
	
	//获取最近浏览的前五条商品信息
		public ArrayList<Items> getViewList(String list)
		{
			System.out.println("list:"+list);
			ArrayList<Items> itemlist = new ArrayList<Items>();
			int iCount=5; //每次返回前五条记录
			if(list!=null&&list.length()>0)
			{
			    String[] arr = list.split(",");
			    System.out.println("arr.length="+arr.length);
			    //如果商品记录大于等于5条
			    if(arr.length>=5)
			    {
			       for(int i=arr.length-1;i>=arr.length-iCount;i--)//倒输出浏览记录
			       {
			    	  itemlist.add(getItemsById(Integer.parseInt(arr[i])));  
			       }
			    }
			    else
			    {
			    	for(int i=arr.length-1;i>=0;i--)
			    	{
			    		itemlist.add(getItemsById(Integer.parseInt(arr[i])));
			    	}
			    }
			    return itemlist;
			}
			else
			{
				return null;
			}
			
		}
	
}

