// define root URL rest API to get a BOT response
var baseURL = "http://localhost:8080/chatbot/rest";

$("#btn-save").click(function() {
	var name = $("#name").val();
	var address = $("#address").val();

	var order = {
		cusName: name,
		cusAddress: address
	};
	
	console.log(order.cusName + " " + order.cusAddress);
	saveOrder(order);
});

function saveOrder(order) {
	$.ajax({
		type: "POST",
		url: baseURL + "/order/save",
		data: JSON.stringify(order),
		dataType: "json",
		contentType: "application/json",
		success: function() {
			console.log("Successfully!");
		}
	});
}