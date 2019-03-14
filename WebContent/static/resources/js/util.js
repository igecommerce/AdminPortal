function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}
function modalClose(id){
	$("#"+id).hide();
}

function displayAlertMsg(msg){
	$("#alertMsg").text(msg);
	$("#alertModal").show();
}

function selectoption(id){
	var valueforselect = $("#"+id).attr("value");
	$("#"+id).val(valueforselect);
	$("#"+id).material_select();
}