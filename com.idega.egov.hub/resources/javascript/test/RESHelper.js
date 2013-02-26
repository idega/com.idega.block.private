var RESHelPer = {};

RESHelPer.addService = function(input){
	var container = jQuery(input).parent();
	var parameters = RESHelPer.getParameters(container);
	
	RestfulEGovServicesTest.addMyService(parameters,{
		callback : function(reply){
			closeAllLoadingMessages();
			var console = jQuery(".console-content");
			console.append("<p>" + reply.message + "</p>");
			jQuery(".test-content").animate({"scrollTop" : console.height()});
			if(reply.status != "OK"){
				// Actions for saviong failure
				return;
			}
			return;
		},
		errorHandler:function(message) {
			closeAllLoadingMessages();
			alert(message);
		}
	});
}

RESHelPer.readMessage = function(input){
	
	var userId = jQuery("#userId").val();
	var serviceId = jQuery("#serviceId").val();
	var messageId = jQuery("#messageId").val();
	var read = jQuery("#read").filter(":checked").size() > 0;
	
	RestfulEGovServicesTest.setMessageRead(serviceId,userId,messageId,read,{
		callback : function(reply){
			closeAllLoadingMessages();
			var console = jQuery(".console-content");
			console.append("<p>" + reply.message + "</p>");
			jQuery(".test-content").animate({"scrollTop" : console.height()});
			if(reply.status != "OK"){
				// Actions for saviong failure
				return;
			}
			return;
		},
		errorHandler:function(message) {
			closeAllLoadingMessages();
			alert(message);
		}
	});
}

RESHelPer.readCase = function(input){
	
	var userId = jQuery("#userId").val();
	var serviceId = jQuery("#serviceId").val();
	var caseId = jQuery("#caseId").val();
	var read = jQuery("#read").filter(":checked").size() > 0;
	
	RestfulEGovServicesTest.setCaseRead(serviceId,userId,caseId,read,{
		callback : function(reply){
			closeAllLoadingMessages();
			var console = jQuery(".console-content");
			console.append("<p>" + reply.message + "</p>");
			jQuery(".test-content").animate({"scrollTop" : console.height()});
			if(reply.status != "OK"){
				// Actions for saviong failure
				return;
			}
			return;
		},
		errorHandler:function(message) {
			closeAllLoadingMessages();
			alert(message);
		}
	});
}

RESHelPer.getParameters = function(container){
	var parameters = container.serializeArray();
	
	var map = {};
	for(var i = 0;i < parameters.length;i++){
		var element = parameters[i];
		if(map[element.name] == undefined){
			map[element.name] = [];
		}
		map[element.name].push(element.value);
	}
	return map;
}


