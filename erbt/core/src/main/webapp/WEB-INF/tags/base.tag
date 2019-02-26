<!DOCTYPE html>
<%@tag description="Base Template" pageEncoding="UTF-8"%>
<%@attribute name="title"%>
<%@attribute name="head_area" fragment="true" %>
<%@attribute name="body_area" fragment="true" %>
<%@attribute name="foot_area" fragment="true" %>

<html>
    <head>
        <title>${title}</title>
        <base href="<%=request.getContextPath()%>"></base>
        <jsp:invoke fragment="head_area"/>
        <link rel="stylesheet" type="text/css" href="/resources/css/home.css"></link> 
    </head>
    <body>
        <div>
            <jsp:invoke fragment="top_area"/>
            <jsp:invoke fragment="main_area"/>
            <jsp:invoke fragment="foot_area"/>
        </div>
    </body>
</html>