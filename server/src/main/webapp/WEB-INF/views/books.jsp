<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page isELIgnored="false" %>

<div class="container starter-template">
    <div class="row">
        <c:forEach items="${bookList}" var="book">
            <div class="col-md-4 col-xs-4">
                <div class="book-card">
                    <a href="#" class="thumbnail">
                        <img src='<c:url value="/books/${book.id}/cover" />' />
                    </a>
                    <p>
                        <c:out value="${book.title}" />
                    </p>
                    <p>
                        <c:out value="${book.author}" />
                    </p>
                    <p>
                        <c:out value="${book.totalPagesCount}" />
                    </p>
                </div>
            </div>
        </c:forEach>
    </div>
</div>