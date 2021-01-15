<%@ page import="org.apache.logging.log4j.Logger" %>
<%@ page import="org.apache.logging.log4j.LogManager" %>
<%@ page import="ua.cn.stu.tpps.buyfly.controller.HomePageController" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>Home page</title>
</head>

<body>

<h2><spring:message code="homepage.title" text="Default text" /></h2>

<p>${message}</p>

<%
    HomePageController.logger.debug("debug message");
    HomePageController.logger.info("info message");
    HomePageController.logger.warn("warn message");
    HomePageController.logger.error("error message");
%>

Current Locale: ${pageContext.response.locale} / ${locale}

<div class="footer">
    <div class="locale"><a href="?locale=en">EN</a> | <a href="?locale=ru_RU">RU</a></div>
</div>

</body>
</html>