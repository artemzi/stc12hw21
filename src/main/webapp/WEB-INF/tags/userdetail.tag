<%@tag description="User Page template" pageEncoding="UTF-8"%>
<%@tag import="com.github.artemzi.pojo.User" %>
<%@attribute name="user" required="true" type="com.github.artemzi.pojo.User"%>

User Name: ${user.name} <br/>
Email: ${user.email} <br/>
Role: ${user.role}<br/>