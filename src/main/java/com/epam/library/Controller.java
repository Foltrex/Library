package com.epam.library;

import com.epam.library.command.Command;
import com.epam.library.command.CommandFactory;
import com.epam.library.command.CommandResult;
import com.epam.library.command.Page;
import com.epam.library.connection.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(Controller.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (resp.isCommitted()) {
            return;
        }

        CommandFactory client = new CommandFactory();
        String commandLine = req.getParameter("command");
        Command command = client.defineCommand(commandLine);

        try {
            CommandResult result = command.execute(req);
            dispatch(req, resp, result);
        } catch (Exception e) {
            LOGGER.warn(e);
            req.setAttribute("errorMessage", e.getMessage());
            dispatch(req, resp, CommandResult.forward(Page.ERROR.getName()));
        }

        // TODO: add message el
    }

    private void dispatch(HttpServletRequest req, HttpServletResponse resp, CommandResult result) throws ServletException, IOException {
        String page = result.getPage();
        if (!result.isRedirect()) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect(page);
        }
    }
}
