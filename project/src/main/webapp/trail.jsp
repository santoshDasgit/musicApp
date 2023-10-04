<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = DriverManager.getConnection(dburl, dbuser, dbpass);
	PreparedStatement ps = con.prepareStatement("select * from student2");
	ResultSet rs = ps.executeQuery();
	%>
	<table class="table table-striped">
		<thead>
			<tr>
				<th scope="col">id</th>
				<th scope="col">email</th>
				<th scope="col">name</th>
				<th scope="col">age</th>
				<th scope="col">phone</th>
				<th scope="col">Action</th>
			</tr>
		</thead>
		<tbody>
			<%
			while (rs.next()) {
			%>
			<tr>
				<th scope="row"><%=rs.getInt(1)%></th>
				<td><%=rs.getString("email")%></td>
				<td><%=rs.getString("name")%></td>
				<td><%=rs.getInt("age")%></td>
				<td><%=rs.getLong("phone")%></td>
				<td><a href="delete.jsp?id=<%=rs.getInt(1)%>"
					class="btn btn-sm btn-danger">Delete</a> <a
					href="edit.jsp?id=<%=rs.getInt(1)%>"
					class="btn btn-sm btn-primary">Edit</a></td>
			</tr>
			'
			<%
			}
			%>
		</tbody>
	</table>
	<%

	%>
</body>
</html>