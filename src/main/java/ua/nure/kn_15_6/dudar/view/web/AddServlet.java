package ua.nure.kn_15_6.dudar.view.web;

import ua.nure.kn_15_6.dudar.User;
import ua.nure.kn_15_6.dudar.db.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddServlet extends EditServlet {
    @Override
    protected void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/add.jsp").forward(req, resp);
    }

    @Override
    protected void passToDB(User user) throws SQLException {
        DaoFactory.getInstance().getUserDao().create(user);
    }
}
