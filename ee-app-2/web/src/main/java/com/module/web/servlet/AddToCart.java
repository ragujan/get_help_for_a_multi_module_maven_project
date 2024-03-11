package com.module.web.servlet;

import ejb.remote.Cart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;

@WebServlet(name = "AddToCart", value = "/add-cart")
public class AddToCart extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        Cart cart;
        try {
            InitialContext context = new InitialContext();
            HttpSession session = request.getSession();
            if (session.getAttribute("cart-session") == null){
                cart =(Cart) context.lookup("java:global/ear/app/CartBean");
                session.setAttribute("cart-session",cart);
            }else {
                cart =(Cart) session.getAttribute("cart-session");
            }

            cart.addItem(name);
            response.sendRedirect("cart");

        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}
