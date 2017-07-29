<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page session="false" %>

<h4>Something went wrong...</h4>
<p>
    <c:out value="${exception.message}" />
</p>