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
		ArrayList<Items> list = new ArrayList<Items>(); // ��Ʒ����
		try {
			dbConn = DBHelper.getConnection();
			String sql = "select * from items;"; // SQL���
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
				list.add(item);// ��һ����Ʒ���뼯��
			}
			return list; // ���ؼ��ϡ�
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			// �ͷ����ݼ�����
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			// �ͷ�������
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
	
	// ������Ʒ��Ż����Ʒ����
	public Items getItemsById(int id) {
		Connection dbConn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		ArrayList<Items> list = new ArrayList<Items>(); // ��Ʒ����
		try {
			dbConn = DBHelper.getConnection();
			String sql = "select * from items where id=?;"; // SQL���
			
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
			// �ͷ����ݼ�����
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			// �ͷ�������
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
	
	//��ȡ��������ǰ������Ʒ��Ϣ
		public ArrayList<Items> getViewList(String list)
		{
			System.out.println("list:"+list);
			ArrayList<Items> itemlist = new ArrayList<Items>();
			int iCount=5; //ÿ�η���ǰ������¼
			if(list!=null&&list.length()>0)
			{
			    String[] arr = list.split(",");
			    System.out.println("arr.length="+arr.length);
			    //�����Ʒ��¼���ڵ���5��
			    if(arr.length>=5)
			    {
			       for(int i=arr.length-1;i>=arr.length-iCount;i--)//����������¼
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
