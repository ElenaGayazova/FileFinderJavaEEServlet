<%@ page language="java" contentType="text/html; charset=UTF-8"
                         pageEncoding="UTF-8"%>
<%@ page import="javax.ejb.EJB" %>
<%@ page import="servlet.Utils" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Start Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
<%!
  @EJB
  private course.DirectoryService service;

  private final String tag = "ol";
%>
        <h1>Home Dir</h1>
        <p>Path=<b><%=System.getProperty("user.home") %></b></p>
        <%=(service!=null)?Utils.wrapList( Utils.writeTree(service.getHomeDir(), tag), tag ):"service not injected"%>
    </body>
</html>
