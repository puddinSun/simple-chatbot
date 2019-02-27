// define root URL rest API to get a BOT response
var baseURL = "http://localhost:8080/chatbot/rest";
var enterKeyValue = "13"; // button ENTER

$("#btn-send").attr("disabled", "disabled");
$("#input").attr("disabled", "disabled");

// handle input values before send message
$("#order-form").on("keyup", "input", function() {
	var isEmpty = false;
	$("#order-form input[type='text']").each(function() {
		if ($(this).val().trim() == 0) {
			isEmpty = true;
		}
	})

	if (!isEmpty) {
		$("#btn-send").removeAttr("disabled");
		$("#input").removeAttr("disabled");
	} else {
		$("#btn-send").attr("disabled", "disabled");
		$("#input").attr("disabled", "disabled");
	}
});

$("#btn-send").click(function() {
	handleConversation();
});

$("#input").on('keypress', function(e) {
	if (e.which == enterKeyValue) {
		handleConversation();
	}
});

function handleConversation() {
	// get user input value
	var input = $("#input").val();

	if (input.trim() != "") {
		// create a user message from input text to display
		var messDisplay = $("<p></p>").text(input);
		messDisplay.addClass("user-message");

		// clear float element
		var clearElement = $("<div style='clear:right'></div>");

		// append message to message box
		$("#message-box").append(messDisplay, clearElement);

		// scroll top
		scrollTop();

		// call API to get BOT response
		handleBotResponse(input);

		// clear user input
		$("#message-entrance input[type='text']").val("");
	}
}

function handleBotResponse(input) {
	$.ajax({
		type : "GET",
		url : baseURL + "/room/text=" + input,
		dataType : "json",
		success : function(data) {
			if (data.generic[0].text != null) {
				displayBotResponse(data.generic[0].text);
			} else if (data.generic[0].options.length > 0) {
				displayBotOptions(data.generic[0].options);
			}
			
			/* check any entities */
//			else if (data.entities.length > 0) {
//				alert("holla");
//				saveOrder(data.entities);
//			}
		}
	});
}

function displayBotResponse(response) {
	console.log("message: " + response);
	var botMessage = $("<p></p>").text(response);
	botMessage.addClass("bot-message");

	$("#message-box").append(botMessage);
	scrollTop();
}

function displayBotOptions(options) {
	console.log("options size: " + options.length);
	for (var i = 0; i < options.length; i++) {
		displayBotResponse(options[i].label);
	}
}

function saveOrder(entities) {
	console.log("Exist entities ...");
	// for (var i = 0; i < entities.length; i++) {
	// if(entities[i].entity == "sys-number") {
	// console.log(entities[0].value);
	// }
	// }
}

function scrollTop() {
	// calculate elements height
	var height = 0;
	$('#message-box p').each(function(i, value) {
		height += parseInt($(this).height()) + height / 3;
	});

	$("#message-box").animate({
		scrollTop : height
	});
}
