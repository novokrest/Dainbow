<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<%@ page session="false" %>
<%@ page isELIgnored="false" %>

<html>
  <head>
    <meta charset="UTF-8">
    <title>Dainbow-Books</title>
    <t:insertAttribute name="base-links" />
    <t:insertAttribute name="extended-links" />
  </head>
  <body>
    <t:insertAttribute name="navigation" />
    <div class="container">
        <t:insertAttribute name="body" />
    </div>
    <t:insertAttribute name="base-scripts" />
    <t:insertAttribute name="extended-scripts" />
  </body>
</html>
