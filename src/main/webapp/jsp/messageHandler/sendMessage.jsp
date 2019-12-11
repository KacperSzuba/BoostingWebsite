<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kacpe
  Date: 03.12.2019
  Time: 23:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <nav>
            <ul>
                <security:authorize access="hasAnyRole('USER','ADMIN','BOOSTER')">
                    <li>
                        <a href="${pageContext.request.contextPath}/account">Account page</a>
                    </li>
                </security:authorize>
                <security:authorize access="hasRole('BOOSTER')">
                    <li>
                        <a href="${pageContext.request.contextPath}/booster">Booster page</a>
                    </li>
                </security:authorize>
                <li>
                    <a href="${pageContext.request.contextPath}/order">Order page</a>
                </li>
            </ul>
        </nav>
    </body>
    <h1>Message Handler</h1>
    <form:form action="message/send" modelAttribute="message">
        <form:input path="title" placeholder="Title" />
        <form:input path="message" placeholder="Message" />
        <select name="username">
            <option value="Admin123x">Admin</option>
            <security:authorize access="hasRole('USER')">
                <option value="booster">Booster</option>
            </security:authorize>
            <security:authorize access="hasRole('BOOSTER')">
                    <option value="customer">Customer</option>
            </security:authorize>
        </select>
        <input type="submit" value="Submit"/>
    </form:form>
</html>