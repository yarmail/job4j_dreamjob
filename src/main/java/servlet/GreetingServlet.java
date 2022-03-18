package servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Email;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Проверить работу сервлета
 * http://localhost:8080/dreamjob/greet?name=Petr
 *
 * При работе с текстом можно сделать так:
 * protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
 * resp.setContentType("text/plain");
 * resp.setCharacterEncoding("UTF-8");
 * String name = req.getParameter("name");
 * PrintWriter writer = new PrintWriter(resp.getOutputStream());
 * writer.println("Nice to meet you, " + name);
 * writer.flush();
 *
 * При работе с GSON меняем код
 * При обработке POST запроса производится десериализация модели.
 * Далее объект сохраняется в список.
 * При обработке GET запроса происходит
 * сериализация списка добавленных почтовых адресов.
 */
public class GreetingServlet extends HttpServlet {

    private final List<Email> emails = new CopyOnWriteArrayList<>();

    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Email email = GSON.fromJson(req.getReader(), Email.class);
        emails.add(email);
        resp.setContentType("application/json; charset=utf-8");
        OutputStream output = resp.getOutputStream();
        String json = GSON.toJson(email);
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json; charset=utf-8");
        OutputStream output = resp.getOutputStream();
        String json = GSON.toJson(emails);
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }
}