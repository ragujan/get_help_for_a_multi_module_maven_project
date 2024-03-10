package com.jiat.web.servlet;

import ejb.remote.Cart;
import ejb.remote.Tax;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;

@WebServlet(name = "CalculateTax", value = "/calculate-tax")
public class CalculateTax extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart;
        Tax taxObject = null;
        try {
            InitialContext context = new InitialContext();
            HttpSession session = request.getSession();
            if (session.getAttribute("cart-session") == null){
//                cart =(Cart) context.lookup("java:global/ear/app/CartBean");
                taxObject =(Tax) context.lookup("java:global/ear/app/TaxCalculateBean");

//                session.setAttribute("cart-session",taxObject);
                session.setAttribute("tax-object",taxObject);
            }else {
//                cart =(Cart) session.getAttribute("cart-session");
                taxObject =(Tax) session.getAttribute("tax-object");
            }
            System.out.println("tax object in calculation hashcode "+taxObject.calculate(100,200));
            System.out.println("tax object hashcode in servlet "+taxObject.hashCode());
//            taxObject.setValue(150);
            System.out.println("tax value is "+ taxObject.getValue());
            response.getWriter().write("Hey");
//            response.sendRedirect("Hey");

//            response.sendRedirect("cart");

        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}
