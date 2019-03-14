function getResponseValue(data)
{
	var gaiaResponse = $.parseJSON(JSON.stringify(data));
	var responseValue = gaiaResponse.responseValue.gaiaresponse;
	return responseValue;
}

function getResponseType(data)
{
	var gaiaResponse = $.parseJSON(JSON.stringify(data));
	var responseObj = $.parseJSON((JSON
			.stringify(gaiaResponse['gaiaResponse'])));
	var responseValue = (responseObj['responseType']);
	if(responseValue == undefined)
		{
		responseValue = responseMsg(responseValue);
		}
	return responseValue;
}

function generatedidValue(responseValue)
{
	var responseObject = $.parseJSON((JSON.stringify(responseValue)));
	return responseObject['id'];
}
function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}
function specialcharactercheck(textValue)
{
	var specialCharacterCount = 0;
		var iChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?";
		for (var i = 0; i < textValue.length; i++) {
		    if (iChars.indexOf(textValue.charAt(i)) != -1) {
		    	specialCharacterCount = parseInt(specialCharacterCount) + 1;
		   
		        }
		    }
		 return specialCharacterCount;	
}