package dev.sanket;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet
{
    private static final long serialVersionUID = 974782962879990860L;

    private static final String NAME = "name";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    {
        String name = Optional.ofNullable(req.getSession(false)).map(session -> (String) session.getAttribute(NAME)).orElse("World");
        String greeting = String.format("Hello %s!", name);

        try (ServletOutputStream out = resp.getOutputStream())
        {
            out.write(greeting.getBytes(StandardCharsets.UTF_8));
            out.flush();
            
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    {
        String name = req.getParameter(NAME);
        req.getSession().setAttribute(NAME, name);
    }
}
