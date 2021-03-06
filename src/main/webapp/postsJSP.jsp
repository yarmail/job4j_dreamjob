<%--
Эта страница написана на обычном jsp
есть другая версия этой страницы, переписанной на jstl
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="model.Post" %>
<%@ page import="java.util.Collection" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <!-- Библиотека иконок -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<%--
Примечания
To change this template use File | Settings | File Templates.
+++++
В сервлете PostServlet мы загружали в  request список вакансий.
req.setAttribute("posts", Store.instOf().findAllPosts());
Теперь их можно прочитать на JSP.
Было:
    for (Post post : Store.instOf().findAllPosts())
Стало:
    for (Post post : (Collection<Post>) request.getAttribute("posts")
Теперь JSP ничего не знает о Store.

--%>

    <title>Работа мечты</title>
</head>
<body>
<div class="container pt-3">

    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Вакансии
            </div>
            <div class="card-body">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Названия</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% for (Post post : (Collection<Post>) request.getAttribute("posts")) { %>
                    <tr>
                        <td>
                            <a href="<%=request.getContextPath()%>/post/edit.jsp?id=<%=post.getId()%>">
                                <i class="fa fa-edit mr-3"></i>
                            </a>
                            <%=post.getName()%>
                        </td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>