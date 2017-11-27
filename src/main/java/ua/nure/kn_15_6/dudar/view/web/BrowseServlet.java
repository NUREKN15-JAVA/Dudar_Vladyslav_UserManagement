package ua.nure.kn_15_6.dudar.view.web;

import ua.nure.kn_15_6.dudar.Constants;
import ua.nure.kn_15_6.dudar.User;
import ua.nure.kn_15_6.dudar.db.DaoFactory;
import ua.nure.kn_15_6.dudar.db.UserDao;
import ua.nure.kn_15_6.dudar.util.Message;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public class BrowseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (req.getParameter(Constants.PARAM_ADD) != null) {
            add(req, resp);
        } else if (req.getParameter(Constants.PARAM_EDIT) != null) {
            edit(req, resp);
        } else if (req.getParameter(Constants.PARAM_DELETE) != null) {
            delete(req, resp);
        } else if (req.getParameter(Constants.PARAM_DETAILS) != null) {
            details(req, resp);
        } else {
            browse(req, resp);
        }
    }

    private void browse(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            Collection<User> users = DaoFactory.getInstance().getUserDao().findAll();
            req.getSession().setAttribute(Constants.KEY_USERS, users);
            req.getRequestDispatcher("/browse.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/add").forward(req, resp);
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter(Constants.PARAM_ID);
        if (idParam == null || idParam.length() == 0) {
            req.setAttribute(Constants.KEY_ERR, Message.getString("options.warning.selectuser"));
            req.getRequestDispatcher("/browse.jsp").forward(req, resp);
            return;
        }

        try {
            User user = DaoFactory.getInstance().getUserDao().find(new Long(idParam));
            req.getSession().setAttribute(Constants.KEY_USER, user);
        } catch (Exception e) {
            req.setAttribute(Constants.KEY_ERR, e.toString());
            req.getRequestDispatcher("/browse.jsp").forward(req, resp);
            return;
        }
        req.getRequestDispatcher("/edit").forward(req, resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter(Constants.PARAM_ID);
        if (idParam == null || idParam.length() == 0) {
            req.setAttribute(Constants.KEY_ERR, Message.getString("options.warning.selectuser"));
            req.getRequestDispatcher("/browse.jsp").forward(req, resp);
            return;
        }
        try {
            UserDao dao = DaoFactory.getInstance().getUserDao();
            User user = dao.find(new Long(idParam));
            dao.delete(user);
        } catch (Exception e) {
            req.setAttribute(Constants.KEY_ERR, e.toString());
            req.getRequestDispatcher("/browse.jsp").forward(req, resp);
            return;
        }
        resp.sendRedirect("/browse");
    }

    private void details(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter(Constants.PARAM_ID);
        if (idParam == null || idParam.length() == 0) {
            req.setAttribute(Constants.KEY_ERR, Message.getString("options.warning.selectuser"));
            req.getRequestDispatcher("/browse.jsp").forward(req, resp);
            return;
        }
        try {
            User user = DaoFactory.getInstance().getUserDao().find(new Long(idParam));
            req.getSession().setAttribute("user", user);
        } catch (Exception e) {
            req.setAttribute(Constants.KEY_ERR, e.toString());
            req.getRequestDispatcher("/browse.jsp").forward(req, resp);
            return;
        }
        req.getRequestDispatcher("/details").forward(req, resp);
    }
}
