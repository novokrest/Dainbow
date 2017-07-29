<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page isELIgnored="false" %>
<%@ page session="false" %>


<h4>Register Book</h4>
<hr />

<sf:form commandName="viewModel" method="post" enctype="multipart/form-data">
    <div class="form-group row">
        <sf:label path="title" cssErrorClass="col-md-2 col-form-label text-danger" class="col-md-2 col-form-label">Title</sf:label>
        <div class="col-md-10">
            <sf:input path="title" class="form-control" type="text" placeholder="Book's title" />
            <sf:errors path="title" cssClass="text-danger" />
        </div>
    </div>
    <div class="form-group row">
        <sf:label path="author" cssErrorClass="col-md-2 col-form-label text-danger" class="col-md-2 col-form-label">Author</sf:label>
        <div class="col-md-10">
            <sf:input path="author" class="form-control" type="text" placeholder="Book's author" />
            <sf:errors path="author" cssClass="text-danger" />
        </div>
    </div>
    <div class="form-group row">
        <sf:label path="totalPagesCount" cssErrorClass="col-md-2 col-form-label text-danger" class="col-md-2 col-form-label">Total Pages</sf:label>
        <div class="col-md-10">
            <sf:input path="totalPagesCount" class="form-control" type="number" placeholder="500" />
            <sf:errors path="totalPagesCount" cssClass="text-danger" />
        </div>
    </div>
    <div class="form-group row">
        <label class="col-md-2 col-form-label">Cover</label>
        <div class="col-md-10">
            <div class="input-group choose-cover">
                <input type="text" class="form-control" readonly/>
                <label class="input-group-btn">
                    <label class="btn btn-default btn-file">
                        Browse&hellip;
                        <input name="coverImage" class="form-control" type="file" accept="image/gif, image/jpeg, image/png" hidden />
                    </label>
                </label>
            </div>
            <div class="cover-viewer">
                <img src='<c:out value="${viewModel.coverBase64}" />' />
                <sf:hidden path="coverBase64" />
            </div>
        </div>
    </div>
    <hr />
    <div class="form-group row">
        <div class="col-md-offset-2 col-md-10">
            <div class="input-sized">
                <input type="submit" class="btn btn-primary pull-right" value="Register" />
            </div>
        </div>
    </div>
</sf:form>