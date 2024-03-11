package com.jiat.web.servlet;


import core.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;

@WebServlet(name = "Test", value = "/test")
public class Test extends HttpServlet {

//    not used for this practical
//    @EJB
//    UserBean userBean;
//
//    not used for this practical
//    @EJB
//    Account accountBean;
    private int count=1;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        try {
            InitialContext context = new InitialContext();
            Account account;
            HttpSession session = request.getSession();
            if (session.getAttribute("account") == null) {
//                account = accountBean;
//                account = (Account) context.lookup("java:global/ear-1.0/com.rag-ejb-1.0/AccountBean");
                account = (Account) context.lookup("java:global/ear/app/AccountBean");
//                taxObject =(Tax) context.lookup("java:global/ear/app/TaxCalculateBean");
                session.setAttribute("account", account);

                response.getWriter().write("Session is not there bro");
            } else {
                account = (Account) session.getAttribute("account");
                response.getWriter().write("Session is there bro");
            }
            response.getWriter().write("</br>");
            account.deposit(1000);
            response.getWriter().write("Balance : " + account.getBalance()+"</br>");
            response.getWriter().write("</br>");
            response.getWriter().write("request count is "+this.count);
            response.getWriter().write("</br>");
            response.getWriter().write("account hashcode "+account.hashCode() + " ... "+"</br>");
            response.getWriter().write("</br>");
            response.getWriter().write("</br>");
            this.count++;
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

    }
}
