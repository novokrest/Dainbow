<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>
<%@ page isELIgnored="false" %>

<div class="row">
    <div class="col-md-9 pull-md-3 bd-content">
        <sf:form commandName="book">
            <div class="form-group row">
                <sf:label path="title" class="col-md-2 col-form-label">Title</sf:label>
                <div class="col-md-10">
                    <sf:input path="title" class="form-control" type="text" placeholder="Book's author" readonly="true" />
                </div>
            </div>
            <div class="form-group row">
                <sf:label path="author" class="col-md-2 col-form-label">Author</sf:label>
                <div class="col-md-10">
                    <sf:input path="author" class="form-control" type="text" placeholder="Book's author" readonly="true" />
                </div>
            </div>
            <div class="form-group row">
                <sf:label path="totalPagesCount" class="col-md-2 col-form-label">Total Pages</sf:label>
                <div class="col-md-10">
                    <sf:input path="totalPagesCount" class="form-control" type="number" placeholder="500" readonly="true" />
                </div>
            </div>
        </sf:form>
        <sf:form commandName="readSummary">
            <div class="form-group row">
                <sf:label path="totalReadPages" class="col-md-2 col-form-label">Read Pages</sf:label>
                <div class="col-md-10">
                    <sf:input path="totalReadPages" class="form-control" type="number" placeholder="351" readonly="true" />
                </div>
            </div>
            <div class="form-group row">
                <sf:label path="totalReadTime" class="col-md-2 col-form-label">Read Time</sf:label>
                <div class="col-md-10">
                    <sf:input path="totalReadTime" class="form-control" type="text" placeholder="13h 25m" readonly="true" />
                </div>
            </div>
        </sf:form>
    </div>
    <div class="col-md-3 push-md-9 bd-sidebar">
        <img src='<s:url value="/books/${book.id}/cover" />' />
    </div>
</div>
<hr />

<div class="log panel panel-default">
    <div class="panel-heading">
        <h3>
            <span class="sign hideSign"></span>
            <span>Log</span>
        </h3>    
    </div>
    <div class="panel-body">
        <sf:form commandName="log" class="form-inline" action='${pageContext.request.contextPath}/books/${book.id}/log' method="post">
            <div class="form-group">
                <sf:label path="pages">Pages</sf:label>
                <sf:input path="pages" type="text" class="form-control" placeholder="Last Read Pages" />
            </div>
            <div class="form-group">
                <sf:label path="day">Day</sf:label>
                <sf:input path="day" type="date" class="form-control" />
            </div>
            <div class="form-group">
                <sf:label path="beginTime">From</sf:label>
                <sf:input path="beginTime" type="time" class="form-control" />
            </div>
            <div class="form-group">
                <sf:label path="endTime">To</sf:label>
                <sf:input path="endTime" type="time" class="form-control" />
            </div>
            <input type="submit" class="btn btn-primary" value="Log" />
        </sf:form>
    </div>
</div>

<div class="pages panel panel-default">
    <div class="panel-heading">
        <h3>
            <span class="sign hideSign"></span>
            <span>Pages</span>
        </h3>
    </div>
    <div class="panel-body">
        <div class="row">
            <c:forEach items="${book.pages}" var="page">
                <div class="col-md-1 page">
                    <div class="content <c:if test="${page.read}">read</c:if>">
                        <span><c:out value="${page.number}" /></span>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<hr />

<div class="history panel panel-default">
    <div class="panel-heading">
        <h3>
            <span class="sign hideSign"></span>
            <span>History</span>
        </h3>
    </div>
    <div class="panel-body">
        <table class="table table-striped table-hover">
            <thead>
                <th>Day</th>
                <th>Pages</th>
                <th>Read Time</th>
            </thead>
            <tbody>
                <c:forEach items="${history.pages}" var="summary">
                    <tr>
                        <td><c:out value="${summary.day}" /></td>
                        <td><c:out value="${summary.pages}" /></td>
                        <td><c:out value="${summary.totalReadTime}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<hr />