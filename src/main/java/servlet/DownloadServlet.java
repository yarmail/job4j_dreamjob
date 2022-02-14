package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Задача этого сервлета отдать файл, который лежит на сервере
 * Давайте создадим папку c:\images\.
 * В этой папке создадим файл users.txt.
 * Вставьте произвольный текст внутрь файла users.txt.
 * Теперь мы можем внутри сервлета прочитать файл и отправить его на клиента.
 *
 * Запустите приложение и откройте страницу http://localhost:8080/dreamjob/download
 * Браузер отобразит содержимое файла. Почему так произошло?
 * HTTP - это текстовый протокол. По умолчанию все данные в
 * протоколе считаются браузером, как текст.
 *
 * Чтобы указать, что сервер отправляет файл нужно установить тип данных.
 * resp.setContentType("application/octet-stream");
 * resp.setHeader("Content-Disposition", "attachment; filename=\"" + users.getName() + "\"");
 * После этого, при выполнении команды
 * http://localhost:8080/dreamjob/download
 * откроется окно сохранения файла users.txt
 *
 * 1. Мы выставляем заголовок ответа в протоколе.
 * Таким образом мы сообщаем браузеру, что будем отправлять файл.
 *
 * 2. Открываем поток и записываем его в выходной поток servlet.
 * resp.getOutputStream().write(in.readAllBytes());
 *
 */
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        File downloadFile = null;
        for (File file : new File("c:\\images\\").listFiles()) {
            if (name.equals(file.getName())) {
                downloadFile = file;
                break;
            }
        }
        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + downloadFile.getName() + "\"");
        try (FileInputStream stream = new FileInputStream(downloadFile)) {
            resp.getOutputStream().write(stream.readAllBytes());
        }
    }
}