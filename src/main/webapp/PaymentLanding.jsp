<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="./PaymentScript.js"></script>
<script src="https://checkout.razorpay.com/v1/checkout.js"></script>
</head>
<body>


	<input type="text" id="amount" placeholder="Enter amount">
	</br>
	<button id="payButton" class="btnstyle" onClick="CreateOrderId()">PayNow</button>


</body>
</html>