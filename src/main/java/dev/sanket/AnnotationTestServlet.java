package dev.sanket;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import javax.servlet.ServletConfig;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/*")
public class AnnotationTestServlet extends HttpServlet {

    private static final long serialVersionUID = -7900075927527719509L;

    private static final String NAME = "name";

    @Override
    public void init(ServletConfig config) {
        System.out.println("Initializing AnnotationTestServlet");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        
        String name = Optional.ofNullable(req.getSession(false)).map(session -> (String) session.getAttribute(NAME))
                .orElse("World");
        String greeting = String.format("Annotated %s!", name);

        try (ServletOutputStream out = resp.getOutputStream()) {
            out.write(greeting.getBytes(StandardCharsets.UTF_8));
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
