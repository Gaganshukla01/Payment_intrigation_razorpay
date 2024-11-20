package com.yash.food_delivery.controller;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.*;
import com.razorpay.*;

@WebServlet("/OrderCreation")
public class PaymentController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PaymentController() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String param =request.getParameter("amount");
       int amount=Integer.parseInt(param);
       
    	String orderId = createOrderId(amount);

      
           response.getWriter().append(orderId);
    }

    
    //method to create order ID
    private String createOrderId(int amount) {
        RazorpayClient client = null;
        String orderId = null;

        try {
            client = new RazorpayClient("keyid", "orderid");
            JSONObject options = new JSONObject();
            options.put("amount", amount*100); // Amount in paise
            options.put("currency", "INR");
            options.put("receipt", "redwsq");
            options.put("payment_capture", true);
            Order order = client.Orders.create(options);
            orderId = order.get("id");
        } catch (RazorpayException e) {
            e.printStackTrace();
        }

        return orderId;
    }
}