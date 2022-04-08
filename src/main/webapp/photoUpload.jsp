<%@ page import="model.Candidate" %>
<%@ page import="store.DbStore" %>
<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Upload</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<c:out value="${candidate}"/>
<table class="table">
    <thead>
    <tr>
        <th>View</th>
    </tr>
    </thead>
    <tbody>
    <tr valign="top">
        <td>
            <img src="<c:url value='/download.do?id=${candidate.id}'/>" width="100px" height="100px"/>
        </td>
    </tr>
    </tbody>
</table>
<h2>Upload image</h2>
<form action="<c:url value='/upload.do?id=${candidate.id}'/>" method="post" enctype="multipart/form-data">
    <div class="checkbox">
        <input type="file" name="file">
    </div>
    <button type="submit" class="btn btn-default">Submit</button>
</form>
<li class="nav-item">
    <a class="nav-link" href="<%=request.getContextPath()%>/candidates.do">Назад</a>
</li>
</div>

</body>
</html>