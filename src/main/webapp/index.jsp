<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<script>
    var xhttp = new XMLHttpRequest();
    var RazorpayOrderId;

    function CreateOrderId() {
        xhttp.open("GET", "http://localhost:8080/Payment-module_test/OrderCreation", true); // Use true for async
        xhttp.onreadystatechange = function() {
            if (this.readyState === 4 && this.status === 200) {
                RazorpayOrderId = this.responseText; 
                OpenCheckout();
            }
        };
        xhttp.send();
    }
</script>

<script src="https://checkout.razorpay.com/v1/checkout.js"></script>
<script>

function OpenCheckout()
{
    var options = {
        "key": "keyid", 
        "amount": "50000", 
        "currency": "INR",
        "name": "Acme Corp",
        "description": "Test Transaction",
        "image": "https://example.com/your_logo",
        "order_id": RazorpayOrderId, 

        "handler": function (response){
            alert(response.razorpay_payment_id);
            alert(response.razorpay_order_id);
            alert(response.razorpay_signature)
        },
        "prefill": {
            "name": "Gaurav Kumar",
            "email": "gaurav.kumar@example.com",
            "contact": "9000090000"
        },
        "notes": {
            "address": "Razorpay Corporate Office"
        },
        "theme": {
            "color": "#3399cc"
        }
    };

    var rzp1 = new Razorpay(options);
    rzp1.on('payment.failed', function (response){
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

</script>

<button id="payButton" class="btnstyle">Pay Now</button>

<script>
    document.getElementById('payButton').onclick = function(e){
        CreateOrderId();
        e.preventDefault();
    }
</script>

</body>
</html>