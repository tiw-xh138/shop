<%@ page language="java" import="java.util.*,java.sql.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="entity.Items"%>
<%@ page import="dao.ItemsDAO"%>
<%@ page import="util.DBHelper"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

	<%
		//获取订单信息
		String a="";
		String b="";
		String c="";
		String d="";
		String e="";
		request.setCharacterEncoding("utf-8");
		a=request.getParameter("name");
		b=request.getParameter("phone");
		c=request.getParameter("add");
		d=request.getParameter("id");
		e=request.getParameter("number");
		int d0;
		int e0;
		d0=Integer.parseInt(d);
		e0=Integer.parseInt(e);
		
		//修改数据库资料
		int t;
		
		int flag=0;
		
		ItemsDAO itemsDao = new ItemsDAO(); 
        ArrayList<Items> list = itemsDao.getAllItems();
        if(list!=null&&list.size()>0)
        {
        	for(int i=0;i<list.size();i++)
            {
        	   
               Items item = list.get(i);
			   
              
               
               //修改库存数据
        	   if(item.getId()==d0){
        		   
        		   
				   t=item.getNumber()-e0;
				   
				   
				
				   if(t>=0){
					   Connection dbConn = null;
					   PreparedStatement pstat = null;
					   PreparedStatement pstatuser = null;
					   int result = -1;
					   int resultuser = -1;
					   try {
						   
						    
							dbConn = DBHelper.getConnection();
							String sql = "UPDATE items SET number = ? WHERE id = ?;"; // SQL语句
							String str="INSERT INTO users VALUES (?,?,?,?,?);";
							pstat = dbConn.prepareStatement(sql);
							pstatuser = dbConn.prepareStatement(str);
							pstat.setInt(1, t);
							pstat.setInt(2, d0);
							pstatuser.setString(1, a);
							pstatuser.setString(2, b);
							pstatuser.setString(3, c);
							pstatuser.setInt(4, d0);
							pstatuser.setInt(5, e0);
							result = pstat.executeUpdate();
							resultuser =pstatuser.executeUpdate();
							out.print("hello"+"<br>");
							flag=1;
							break;
							
						} catch (Exception ex) {
							ex.printStackTrace();
							
						}finally {
						
							// 释放语句对象
							if (pstat != null) {
								try {
									pstat.close();
									pstat = null;
								} catch (Exception ex) {
									ex.printStackTrace();
								}
							}
							
							if (pstatuser != null) {
								try {
									pstatuser.close();
									pstatuser = null;
								} catch (Exception ex) {
									ex.printStackTrace();
								}
							}

						}
				   }
				   
				   else if(t<0){
					   flag=2;
					   break;
					   
					   
				   }
				   
			   }
        	   
        	   
               
               
               
               
               
               
            }
        }
		
		if(flag==1){
			request.getRequestDispatcher("success.jsp").forward(request, response);
		}else if(flag==2){
			response.sendRedirect("fail.jsp");
		}else if(flag==0){
			response.sendRedirect("nothing.jsp");
		}
		
		
		
		
	
	%>
</body>
</html>