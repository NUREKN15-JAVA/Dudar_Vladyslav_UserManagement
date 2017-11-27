package ua.nure.kn_15_6.dudar.view.web;

import ua.nure.kn_15_6.dudar.Constants;
import ua.nure.kn_15_6.dudar.User;
import ua.nure.kn_15_6.dudar.db.DaoFactory;
import ua.nure.kn_15_6.dudar.util.Message;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDate;

public class EditServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (req.getParameter(Constants.PARAM_OK) != null) {
            doOk(req, resp);
        } else if (req.getParameter(Constants.PARAM_CANCEL) != null) {
            doCancel(resp);
        } else {
            showPage(req, resp);
        }
    }

    private void doOk(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = getUser(req).copy();
            passToDB(user);
        } catch (ValidationException e) {
            req.setAttribute(Constants.KEY_ERR, e.getMessage());
            showPage(req, resp);
            return;
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        resp.sendRedirect("/browse");
    }

    private void doCancel(HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/browse");
    }

    private User getUser(HttpServletRequest req) throws ValidationException {
        User user = new User();

        String idStr = req.getParameter(Constants.PARAM_ID);
        String firstName = req.getParameter(Constants.PARAM_FIRST_NAME);
        String lastName = req.getParameter(Constants.PARAM_LAST_NAME);
        String date = req.getParameter(Constants.PARAM_DATE);

        if (firstName == null || firstName.isEmpty()) {
            throw new ValidationException(Message.getString("exception.missing.firstname"));
        }
        if (lastName == null || lastName.isEmpty()) {
            throw new ValidationException(Message.getString("exception.missing.lastname"));
        }
        if (date == null) {
            throw new ValidationException(Message.getString("exception.missing.dateofbirth"));
        }
        if (idStr != null) {
            user.setId(Long.valueOf(idStr));
        }
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBirthDate(LocalDate.parse(date));
        return user;
    }

    protected void passToDB(User user) throws SQLException {
        DaoFactory.getInstance().getUserDao().update(user);
    }

    protected void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/edit.jsp").forward(req, resp);
    }
}
