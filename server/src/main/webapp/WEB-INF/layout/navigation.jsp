<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page session="false" %>
<%@ page isELIgnored="false" %>

<nav class="navbar navbar-fixed-top navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Dainbow</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active">
                    <a href='<s:url value="/books" />'>Books</a>
                </li>
                <li>
                    <a href="#about">About</a>
                </li>
            </ul>
        </div>
    </div>
</nav>