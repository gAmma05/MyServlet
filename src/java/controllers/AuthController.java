/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Account;
import dao.AccountDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

/**
 *
 * @author vothimaihoa
 */
public class AuthController extends HttpServlet {

    private final String LIST = "Product";
    private final String AUTH = "Auth";
    private final String DASHBOARD = "view/dashboard/dashboard.jsp";
    //private final String LIST_VIEW = "view/product/list.jsp";
    private final String LOGIN_VIEW = "view/account/login.jsp";
    //private final String REGISTER_VIEW = "view/account/register.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession(false);
        if (action != null) {
            switch (action) {
                case "login":
                    login(request, response);
                    break;
                case "logout":
                    logout(request, response);
                    break;
                default:
                    if (session != null && session.getAttribute("isLoggedIn") != null) {
                        request.getRequestDispatcher(DASHBOARD).forward(request, response);
                    } else {
                        request.getRequestDispatcher(LOGIN_VIEW).forward(request, response);
                    }
                    break;
            }
        } else {
            if (session != null && session.getAttribute("isLoggedIn") != null) {
                request.getRequestDispatcher(DASHBOARD).forward(request, response);
            } else {
                request.getRequestDispatcher(LOGIN_VIEW).forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.getByUsernamePassword(username, password);
        if (account != null) {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            if (account.getRole().getValue() == 1) {
                session.setAttribute("role", "staff");
            } else if (account.getRole().getValue() == 0) {
                session.setAttribute("role", "customer");
            }
            session.setAttribute("isLoggedIn", true);
            resp.sendRedirect(LIST);
        } else {
            req.setAttribute("error", "Wrong username or password");
            req.getRequestDispatcher(LOGIN_VIEW).forward(req, resp);
        }
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        resp.sendRedirect(AUTH);
    }
}
