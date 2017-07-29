<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page isELIgnored="false" %>

<div class="container starter-template">
    <div class="row">
        <c:forEach items="${bookList}" var="book">
            <div class="col-md-4 col-xs-4">
                <div class="book-card">
                    <a href='<c:url value="/books/${book.id}" />' class="thumbnail">
                        <img src='<c:url value="/books/${book.id}/cover" />' />
                    </a>
                    <p>
                        <b>
                            <c:out value="${book.title}" />
                        </b>
                    </p>
                    <p>
                        <i>
                            <c:out value="${book.author}" />
                        </i>
                    </p>
                </div>
            </div>
        </c:forEach>
    </div>
</div>