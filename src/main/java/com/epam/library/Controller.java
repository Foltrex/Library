package com.epam.library;

import com.epam.library.command.Command;
import com.epam.library.command.CommandFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        CommandFactory client = new CommandFactory();
        String commandLine = req.getParameter("command");
        try {
            Command command = client.defineCommand(commandLine);
            String page = command.execute(req);
            req.getRequestDispatcher(page).forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("jsp/errorPage.jsp").forward(req, resp);
        }
    }
}
