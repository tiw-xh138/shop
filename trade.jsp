<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<h1>商品购买</h1>
    <hr>
    <center>
	<form action="trade2.jsp" name="webForm" method="post">
    <table >
     <tr>
       <p>
       <td>姓名：</td>
       <td><input type="text" name="name"></td>
       </p>
     </tr>
     <tr>
       <p>
       <td>手机号：</td>
       <td><input type="text" name="phone"></td>
       </p>
     </tr>
     <tr>
       <p>
       <td>收货地址：</td>
       <td><input type="text" name="add"></td>
       </p>
     </tr>
     <tr>
       <p>
       <td>商品编号：</td>
       <td><input type="text" name="id"></td>
       </p>
     </tr>
     <tr>
       <p>
       <td>购买数量：</td>
       <td><input type="text" name="number"></td>
       </p>
     </tr>
     
     <tr align="center">
    
       <td colspan="2"><input type="submit" value="提交订单" style="width:150px;height:30px;"/></td>
     
     </tr>
    
   </table>
  </form>
  </center>
</body>
</html>