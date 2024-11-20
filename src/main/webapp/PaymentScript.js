var RazorpayOrderId;


const CreateOrderId = () => {

	var amount = document.getElementById("amount").value;
	var xhttp = new XMLHttpRequest();

	xhttp.open("GET", "http://localhost:8080/Payment-module_test/OrderCreation?amount=" + amount, true);
	xhttp.onreadystatechange = function() {
		if (this.readyState === 4 && this.status === 200) {
			RazorpayOrderId = this.responseText;
			OpenCheckout();
		}
	};
	xhttp.send();
}




const OpenCheckout = () => {
	var options = {
		"key": "key",
		"name": "Yash Tech",
		"description": "Test Transaction",
		"order_id": RazorpayOrderId,

		"handler": function(response) {
			alert(response.razorpay_payment_id);
			alert(response.razorpay_order_id);
			alert(response.razorpay_signature);
		},
		"prefill": {
			"name": "Gagan Shukla",
			"email": "gagan.shukla@yash.com",
			"contact": "8349061831"
		},
		"notes": {
			"address": "YIT Indore "
		},
		"theme": {
			"color": "#3399cc"
		}
	};

	var rzp1 = new Razorpay(options);
	rzp1.on('payment.failed', function(response) {
		alert(response.error.code);
		alert(response.error.description);
		alert(response.error.source);
		alert(response.error.step);
		alert(response.error.reason);
		alert(response.error.metadata.order_id);
		alert(response.error.metadata.payment_id);
	});
	rzp1.open();
}




