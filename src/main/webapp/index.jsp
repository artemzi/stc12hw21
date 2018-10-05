<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
    session=request.getSession(false);
    if(session.getAttribute("authenticated") == null | "fail".equals(session.getAttribute("authenticated"))) {
        response.sendRedirect("/login.jsp");
    }

%>

<t:default>
    <jsp:body>
        <h1>Dashboard</h1>
        <c:choose>
            <c:when test="${sessionScope.user != null}">
                <p>
                    <t:userdetail user="${sessionScope.user}"/>
                </p>
                <c:choose>
                    <c:when test="${sessionScope.user.role == \"teacher\"}">
                        <h3>Your are a teacher and can create tasks for students.</h3>
                    </c:when>
                    <c:otherwise>
                        <h3>Your are a student and can solve tasks from teachers.</h3>
                    </c:otherwise>
                </c:choose>
            </c:when>
        </c:choose>
    </jsp:body>
</t:default>