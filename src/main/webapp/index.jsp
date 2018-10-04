<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%
    session=request.getSession(false);
    if(session.getAttribute("authenticated") == null | "fail".equals(session.getAttribute("authenticated"))) {
        response.sendRedirect("/login.jsp");
    }

%>

<t:default>
    <div class="container">
        <h1>Dashboard</h1>
    </div>
</t:default>