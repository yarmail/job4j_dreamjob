<%@ page contentType="text/html;charset=UTF-8"%>

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

<%--
Примечания:
To change this template use File | Settings | File Templates.
++++
Не рекомендуется использовать пакет по умолчанию,
(голый класс без пакета)
Объвление класса внутри JSP запрещено
++++
Для правильной загрузки ссылки используется элемент скриплета.
<a class="nav-link" href="<%=request.getContextPath()%>/posts.jsp">Вакансии</a>
Если его не указать, то адрес будет ссылаться на
корень сервера http://localhost:8080/posts.jsp. Это не верно.
Нам нужно http://localhost:8080/dreamjob/posts.jsp
Откуда появился объект request? Это объект доступен в любой jsp.
Он позволяет получить информацию о сервере.
Здесь мы получаем адрес расположения приложения. /dreamjob
+++++
На странице index.jsp нужно поправить ссылку.
Теперь загрузка будет через Servlet.
Было: .../posts.jsp; Стало... /posts.do

--%>

    <title>Работа мечты</title>
</head>
<body>
<div class="container">
    <jsp:include page="menu.jsp"/>
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Сегодняшние вакансии.
            </div>
            <div class="card-body">
            </div>
        </div>
    </div>
    <div class="row pt-3">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Сегодняшние кандидаты.
            </div>
            <div class="card-body">
            </div>
        </div>
    </div>
</div>
</body>
</html>