<%@ page import="java.io.*,java.sql.*"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String sn = request.getParameter("ver");

    Class.forName("oracle.jdbc.driver.OracleDriver");
    Connection con = DriverManager.getConnection(
            "jdbc:oracle:thin:@127.0.0.1:1521:XE", "system", "admin");
    Statement st = con.createStatement();
    ResultSet rs = st
            .executeQuery("select * from customer where user_id='" + sn
                    + "'");
    if (rs.next()) {
        out.println("<font color=red>");
        out.println("Not Available");
        out.println("</font>");
    } else {
        out.println("<font color=green>");
        out.println("Available");
        out.println("</font>");
    }

    rs.close();
    st.close();
    con.close();
%>