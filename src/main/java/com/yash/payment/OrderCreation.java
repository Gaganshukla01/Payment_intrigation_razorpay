package com.yash.payment;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.*;
import com.razorpay.*;

@WebServlet("/OrderCreation")
public class OrderCreation extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public OrderCreation() 
    {
    	
      
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		 RazorpayClient client=null;
	       String orderId=null;
	       
	       try 
	       {
	    	   client = new RazorpayClient("keyid", "secret");
	    	    JSONObject options = new JSONObject();
	    	    options.put("amount", 100);
	    	    options.put("currency", "INR");
	    	    options.put("receipt", "redwsq"); 
	    	    options.put("payment_capture", true);
	    	    Order order = client.Orders.create(options);
	    	    orderId = order.get("id");
	    
	    	      
	       }
	       catch(RazorpayException e) 
	       {
	    	   e.printStackTrace();
	       }
	       response.getWriter().append(orderId);
	       
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        
		RazorpayClient client=null;
		try {
			client =new RazorpayClient("keyid","secret");
			JSONObject options =new JSONObject();
			options.put("razorpay_payment_id",request.getParameter("razorpay_payment_id"));
			options.put("razorpay_order_id",request.getParameter("razorpay_order_id"));
			options.put("razorpay_signature",request.getParameter("razorpay_signature"));
			boolean SignRes=Utils.verifyPaymentSignature(options,"secret");
			if(SignRes) {
				response.getWriter().append("PAYMENT SUCESSFULL ");
			}
			else {
				response.getWriter().append("PAYMENT Failed ");
			}
		}
		
		catch(RazorpayException e) {
			e.printStackTrace();
		}
	}

}
