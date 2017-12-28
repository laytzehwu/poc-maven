var getFormData = function ($form) {
	var oData = {};
	$form.find(":input").each(function (idx, el) {
		var $el = $(el);
		if ($el.attr("name")) {
			oData[$el.attr("name")] = $el.val();
		}
	});
	return oData;
}

var formPost = function ($form) {
	var oData = getFormData($form);
	console.log("Form post", oData);
	$.ajax({
		url: $form.attr("action"),
		method: $form.attr("method"),
		data: oData
	}).done(function (data) {
		console.log("Form submitted", data);
	});
}

var jsonPost = function ($form, testCase) {
	var oData = getFormData($form);
	var isModel = testCase === "json-model-post";
	$.ajax({
		url: $form.attr("action") + (isModel ? "/json-model" : "/json"),
		method: "POST",
		contentType: "application/json",
		dataType: "json",
		data: JSON.stringify(oData)
	}).done(function (data) {
		console.log("JSON submitted", data);
	});
}

var initForm = function ($form) {
	$form.on("submit", function (evt) {
		evt.preventDefault();
		var testCase = $("input[name='case']").filter(":checked").val();
		switch(testCase) {
			case "form-post":
				formPost($form);
				break;
			case "json-post":
			case "json-model-post":
				jsonPost($form, testCase);
				break;
		}
	});
}

$(document).ready(function () {
	initForm($("form"));
});