var ReykjavikForms = {};

jQuery(window).load(function(){
	closeAllLoadingMessages();
	ReykjavikForms.buttonsFunctions();
});

jQuery(document).ready(function(){
	// masked inputs
	jQuery.mask.definitions['2'] = '[0-2]';
	jQuery.mask.definitions['5'] = '[0-5]';
	jQuery('.xFormInputMask_time').each(function() {
		var input = jQuery(this);
		input.mask('29:59');
		var data = {input:input};
		input.change(data,function(e){
			var input = e.data.input;
			var value = input.val();
			if(!value){
				return;
			}
			var val = value[0];
			if(val != 2) return;
			val = parseInt(value[1]);
			if(val < 4) return;
			var newValue = value[0] + 3 + value.slice(2,5);
			input.val(newValue);
		});
		input.attr('placeholder','00:00');
	});
	ReykjavikForms.createConditionalSelects(".conditional-selects");
});

jQuery.validator.addMethod("validate-number-checked", function(value, element) {
	var el = jQuery(element);
	var ret = false;
	if(!el.hasClass('conditional')){
		var all = el.parents('.threeColumns').first().find('[name="'+el.attr('name')+'"]');
		var selects = all.filter('[type="'+el.attr('type')+'"]');
		for(var i = 0;i < selects.length;i++){
			var select = jQuery(selects[i]);
			if(select.is(":checked")){
				ret =  true;
				break;
			}
		}
		if(!ret){
			return true;
		}
		el = all.filter('[type="text"]');
	}
	for(var i = 0;i < el.length;i++){
		var input = jQuery(el[i]);
		if(!input.hasClass('conditional')){
			continue;
		}
		var check = input.parents('.conditions-div').first().parent().find('.condition');
		if(check.is(':checked')){
			var isWhole_re = /^\s*\d+\s*$/;
			var val = input.val();
			if(!val || (val.search(isWhole_re) == -1)){
				return false;
			}
		}
	}
	return true;
});

ReykjavikForms.isConditionChecked = function(element){
	var el = jQuery(element);
	var check = el.parents('.conditions-div').first().parent().find('.condition');
	return check.is(':checked');
}

// Add your advanced parameters fuctions here
ReykjavikForms.advancedParametersFunctionList = [];

ReykjavikForms.messages = {};
ReykjavikForms.messages.validation = {};
ReykjavikForms.validationRules = {};


ReykjavikForms.afterLoadPreview = function(params){
	var page = params.page;
	page.empty();
	var output = jQuery('.chibaform').children(".switch").clone();
	output.children('.preview').remove();
	output.children('.case').removeClass('deselected-case');
	output.children('.case').addClass('selected-case');
	
	output.find('input.check-row').each(function(){
		var input = jQuery(this);
		if(input.is(':checked')){
			return;
		}
		input.parents('tr').first().remove();
	});
	
	output.find('input[type="text"]').each(function(){
		var input = jQuery(this);
		var span = jQuery('<span/>');
		input.after(span);
		span.append(input.val());
		input.remove();
	});
	
	output.find('input[type="checkbox"]').each(function(){
		var input = jQuery(this);
		if(input.is(':checked')){
			input.remove();
		}else{
			input.parent().remove();
		}
	});
	output.find('input[type="radio"]').each(function(){
		var input = jQuery(this);
		if(input.is(':checked')){
			input.remove();
		}else{
			input.parent().remove();
		}
	});
	output.find('textarea').each(function(){
		var textarea = jQuery(this);
		var div = jQuery('<div/>');
		textarea.after(div);
		div.append(textarea.val());
		textarea.remove();
	});
	output.find('input').remove();
	output.find('button').remove();
	output.find('script').remove();
	page.append(output);
};

ReykjavikForms.afterLoadNavigationFunctions = {
		"preview"	: {
			callback : ReykjavikForms.afterLoadPreview,
			params : {}
		}
};

ReykjavikForms.afterHidePreview = function(params){
	var page = params.page;
	page.empty();
};

ReykjavikForms.afterHideNavigationFunctions = {
		"preview"	: {
			callback : ReykjavikForms.afterHidePreview,
			params : {}
		}
};

ReykjavikForms.afterLoadNavigation = function(next,previous){
	var keys = Object.keys(ReykjavikForms.afterLoadNavigationFunctions);
	for(var i = 0;i < keys.length;i++){
		var key = keys[i];
		if(next.hasClass(key)){
			var callback = ReykjavikForms.afterLoadNavigationFunctions[key].callback;
			parameters = ReykjavikForms.afterLoadNavigationFunctions[key].params;
			if(!parameters){
				parameters = {};
			}
			var params = jQuery.extend({}, {page : next}, parameters)
			callback(params);
		}
	}
	keys = Object.keys(ReykjavikForms.afterHideNavigationFunctions);
	for(var i = 0;i < keys.length;i++){
		var key = keys[i];
		if(previous.hasClass(key)){
			var callback = ReykjavikForms.afterHideNavigationFunctions[key].callback;
			parameters = ReykjavikForms.afterHideNavigationFunctions[key].params;
			if(!parameters){
				parameters = {};
			}
			var params = jQuery.extend({}, {page : previous}, parameters)
			callback(params);
		}
	}
};

ReykjavikForms.loadStage = function(nextStage,previousStage){
	if(!previousStage){
		previousStage = jQuery('nothing');
	}
	var previous = jQuery('#previous-button');
	var next = jQuery('#next-button');
	var submit = jQuery('#chiba-submit-button');
	
	previousStage.removeClass('selected-case');
	previousStage.addClass('deselected-case');
	nextStage.removeClass('deselected-case');
	nextStage.addClass('selected-case');
	if(nextStage.next(".case").length > 0){
		next.parent().show();
		submit.parent().hide();
	}else{
		next.parent().hide();
		submit.parent().show();
	}
	if(nextStage.prev(".case").length > 0){
		previous.parent().show();
	}else{
		previous.parent().hide();
	}
	ReykjavikForms.afterLoadNavigation(nextStage,previousStage);
}

ReykjavikForms.buttonsFunctions = function(){
	var previous = jQuery('#previous-button');
	var next = jQuery('#next-button');
	var fieldsets = ReykjavikForms.selectFieldsets();
	if(fieldsets.length < 1){
		return;
	}
	var first = fieldsets.first();
	fieldsets.splice(0,1);
	fieldsets.removeClass('selected-case');
	fieldsets.addClass('deselected-case');
	ReykjavikForms.loadStage(first);
	next.click(function(){
		var fieldsets = ReykjavikForms.selectFieldsets();
		for(var i = 0;i<fieldsets.length;i++){
			var fieldset = jQuery(fieldsets[i]);
			if(!fieldset.hasClass('selected-case')){
				continue;
			}
			var nextFieldset = jQuery(fieldsets[i+1]);
			ReykjavikForms.loadStage(nextFieldset,fieldset);
			break;
		}
	});
	previous.click(function(){
		var fieldsets = ReykjavikForms.selectFieldsets();
		for(var i = (fieldsets.length - 1);i > -1;i--){
			var fieldset = jQuery(fieldsets[i]);
			if(!fieldset.hasClass('selected-case')){
				continue;
			}
			var nextFieldset = jQuery(fieldsets[i-1]);
			ReykjavikForms.loadStage(nextFieldset,fieldset);
			break;
		}
	});
};

ReykjavikForms.selectFieldsets = function(){
	return jQuery('.chibaform').children('.switch').children(".case");
};

ReykjavikForms.showErrors = function(errorMap, errorList) {
	ReykjavikForms.navigateToErrorStage(errorList);
	this.defaultShowErrors();
}
ReykjavikForms.navigateToErrorStage = function(errorList){
	if(!errorList || (errorList.length == 0)){
		return;
	}
	var error = jQuery(errorList[0].element);
	var parent = error.parents('.deselected-case').last();
	if(parent.length == 0){
		return;
	}
	var current = parent.parent().children('.selected-case');
	ReykjavikForms.loadStage(parent, current);
	jQuery('#chiba-submit-button').parent().show();
	if(error.hasClass('row-check')){
		jQuery(ReykjavikForms.focusElement).focus();
	}
}
ReykjavikForms.focusElement = null;
ReykjavikForms.submitFunction = function(formObject){
	var form = jQuery(formObject);
	if(!form.valid()){
		return false;
	}
	var parameters = form.serializeArray();
	
	var map = {};
	var functionList = ReykjavikForms.advancedParametersFunctionList;
	var advancedParameters = {};
	for(var i = 0;i < functionList.length;i++){
		var advancedParameterMap = eval(functionList[i]);
		if(advancedParameterMap){
			advancedParameters = jQuery.extend(advancedParameters, advancedParameterMap);
		}
	}
	for(var i = 0;i < parameters.length;i++){
		var element = parameters[i];
		if(ReykjavikForms.isBlank(element.value)){
			continue;
		}
		if(map[element.name] == undefined){
			map[element.name] = [];
		}
		map[element.name].push(element.value);
	}
	showLoadingMessage(ReykjavikForms.messages.loading);
	ReykjavikServices.saveProcess(advancedParameters,map,{
		callback : function(reply){
			if(reply.success != "true"){
				// Actions for saving failure
				closeAllLoadingMessages();
				humanMsg.displayMsg(reply.message[0]);
				return;
			}
			var caseId = reply['case-id'][0];
			var taskInstanceId = reply['taskInstanceId'][0];
			form.find('[name="prm_case_pk"]').val(caseId);
			form.find('[name="taskInstanceId"]').val(taskInstanceId);
			humanMsg.displayMsg(reply.message[0]);
			closeAllLoadingMessages();
			window.location.href = reply['redirect-uri'];
			return;
		},
		errorHandler:function(message) {
			closeAllLoadingMessages();
			alert(message);
		}
	});
	return false;
};


// Contact list
ReykjavikForms.createContactsList = function(containerSelector){
	var container = jQuery(containerSelector);
	var add = container.find('.add-btn');
	var rows = container.find("table").first().find("tr").filter(".repeat-item");
	var addRow = rows.length == 0;
	ReykjavikForms.prepareContactRows(rows);
	var data = {container : containerSelector};
	add.click(data,function(e){
		var container = jQuery(e.data.container);
		var row = container.find(".component-div").find("tr").clone();
		ReykjavikForms.prepareContactRows(row);
		var tbody = container.find("table").first().children();
		tbody.append(row);
		tbody.find(".delete-btn").parent().show();
	});
	if(addRow){
		add.trigger("click");
		container.find(".delete-btn").parent().hide();
	}
	ReykjavikForms.advancedParametersFunctionList.push('ReykjavikForms.getContacts("' + containerSelector + '");');
};

ReykjavikForms.prepareContactRows = function(rows){
	rows.find(".delete-btn").click(function(){
		var btn = jQuery(this);
		var row = btn.parents("tr").first();
		var rows = row.parent().children(".repeat-item");
		if(rows.length < 3){
			rows.find(".delete-btn").parent().hide();
		}
		row.remove();
	});
};

ReykjavikForms.getContacts = function(container){
	var main = jQuery(container);
	var rows = main.find("table").first().find("tr");
	var list = [];
	for(var i = 0;i < rows.length;i++){
		var row = jQuery(rows[i]);
		var contact = {};
		contact.name = row.find(".name").val();
		contact.relation = row.find(".relation").val();
		contact.phone = row.find(".phone").val();
		var bl = ReykjavikForms.isBlank;
		if(bl(contact.name) && bl(contact.relation) && bl(contact.phone)){
			continue;
		}
		list.push(contact);
	}
	var map = {};
	map[main.attr('id')] = list;
	return map;
};
ReykjavikForms.validateContacts = function(checkbox){
	var row = jQuery(checkbox).parents("tr").first();
	var name = row.find(".name");
	var relation = row.find(".relation");
	var phone = row.find(".phone");
	if(ReykjavikForms.isBlank(name.val())){
		ReykjavikForms.focusElement = name; 
		return false;
	}
	return true;
}
//-------------------------------------------

// TODO: make general with contact list
//Resident list
ReykjavikForms.createResidentList = function(containerSelector){
	var container = jQuery(containerSelector);
	var add = container.find('.add-btn');
	var rows = container.find("table").first().find("tr").filter(".repeat-item");
	var addRow = rows.length == 0;
	ReykjavikForms.prepareResidentsRows(rows);
	var data = {container : containerSelector};
	add.click(data,function(e){
		var container = jQuery(e.data.container);
		var row = container.find(".component-div").find("tr").clone();
		ReykjavikForms.prepareResidentsRows(row);
		var tbody = container.find("table").first().children();
		tbody.append(row);
		tbody.find(".delete-btn").parent().show();
	});
	if(addRow){
		add.trigger("click");
		container.find(".delete-btn").parent().hide();
	}
	ReykjavikForms.advancedParametersFunctionList.push('ReykjavikForms.getResidents("' + containerSelector + '");');
};

ReykjavikForms.prepareResidentsRows = function(rows){
	rows.find(".delete-btn").click(function(){
		var btn = jQuery(this);
		var row = btn.parents("tr").first();
		var rows = row.parent().children(".repeat-item");
		if(rows.length < 3){
			rows.find(".delete-btn").parent().hide();
		}
		row.remove();
	});
};

ReykjavikForms.getResidents = function(container){
	var main = jQuery(container);
	var rows = main.find("table").first().find("tr");
	var list = [];
	for(var i = 0;i < rows.length;i++){
		var row = jQuery(rows[i]);
		var contact = {};
		contact.name = row.find(".name").val();
		contact.personalId = row.find(".personal-id").val();
		contact.relation = row.find(".relation").val();
		contact.occupation = row.find(".occupation").val();
		var bl = ReykjavikForms.isBlank;
		if(bl(contact.name) && bl(contact.relation) && bl(contact.phone)){
			continue;
		}
		list.push(contact);
	}
	var map = {};
	map[main.attr('id')] = list;
	return map;
};
ReykjavikForms.validateResidents= function(checkbox){
	var row = jQuery(checkbox).parents("tr").first();
	var name = row.find(".name");
	var relation = row.find(".relation");
	var phone = row.find(".phone");
	if(ReykjavikForms.isBlank(name.val())){
		ReykjavikForms.focusElement = name; 
		return false;
	}
	return true;
}
//-------------------------------------------

// Week list
ReykjavikForms.createWeekList = function(containerSelector){
	var container = jQuery(containerSelector);
	var rows = container.find("table").first().find("tr").filter(".repeat-item");
	ReykjavikForms.createTableCheckHiding(container);
	ReykjavikForms.advancedParametersFunctionList.push('ReykjavikForms.getWeekDays("' + containerSelector + '");');
};

ReykjavikForms.getWeekDays = function(container){
	var main = jQuery(container);
	var rows = main.find("table").first().find("tr");
	var list = [];
	for(var i = 0;i < rows.length;i++){
		var row = jQuery(rows[i]);
		if(row.find('input[type="checkbox"]:checked').length == 0){
			continue;
		}
		var dayData = {};
		dayData.number = i;
		dayData.from = row.find(".from-time").val();
		dayData.to = row.find(".to-time").val();
		var bl = ReykjavikForms.isBlank;
		list.push(dayData);
	}
	var map = {};
	map[main.attr('id')] = list;
	return map;
};

//-------------------------------------------
//Uploader
ReykjavikForms.afterUpload = function(selector){
	var container = jQuery(selector);
	var uri = FileUploadHelper.allUploadedFiles[0];
	FileUploadHelper.allUploadedFiles = [];
	var name = uri.split("/");
	name = name[name.length - 1];
	var tr = container.find(".uploader-div").parents('tr').first();
	var label = tr.find(".name-label");
	var urlInput = tr.find('.url-input');
	urlInput.val(uri);
	tr.find('.upload').hide();
	label.empty();
	var link = jQuery('<a/>');
	label.append(link);
	link.attr('href',uri);
	link.attr('target','_blank');
	link.append(name);
	FileUploadHelper.properties.localizations.UPLOADING_FILE_PROGRESS_BOX_FILE_UPLOADED_TEXT = "";
}
ReykjavikForms.createUploader = function(selector){
	var container = jQuery(selector);
	var add = container.find(".add-attachments");
	var data= {c : container};
	add.click(data,function(e){
		var container = e.data.c;
		var tbody = container.find(".uploads-table").children();
		var tdDiv = container.find('.td-div');
		var row = tdDiv.find("tr").clone();
		tbody.append(row);
		ReykjavikForms.prepareUploaderRows(row,container);
	});
	var uploader = container.find('.uploader-div');
	var file = uploader.find('input[type="file"]');
	file.css("opacity","0");
	file.addClass("value");
	var rows = container.find(".uploads-table").find("tr");
	ReykjavikForms.prepareUploaderRows(rows,container);
	if(rows.length == 0){
		add.click();
	}
	ReykjavikForms.advancedParametersFunctionList.push("ReykjavikForms.getUploadedFiles('" + selector + "')");
}
ReykjavikForms.getUploadedFiles = function(selector){
	var main = jQuery(selector);
	var rows = main.find(".uploads-table").find("tr");
	var list = [];
	for(var i = 0;i < rows.length;i++){
		var row = jQuery(rows[i]);
		var file = {};
		file.name = row.find(".file-name").val();
		file.path = row.find(".url-input").val();
		var bl = ReykjavikForms.isBlank;
		if(bl(file.path)){
			continue;
		}
		list.push(file);
	}
	var map = {};
	map[main.attr('id')] = list;
	return map;
}
ReykjavikForms.prepareUploaderRows = function(rows,container){
	rows.find('.remove').click(function(){
		var tr = jQuery(this).parents("tr").first();
		var url = tr.find('.url-input').val();
		if(!ReykjavikForms.isBlank(url)){
			showLoadingMessage(ReykjavikForms.messages.loading);
			FileUploadHelper.deleteUploadedFile('nothing',url,false,function(result){
				if(result.id == 'false'){
					closeAllLoadingMessages();
					humanMsg.displayMsg(result.value);
					return false;
				}
				var uploader = tr.find('.uploader-div');
				tr.parents('.uploads-table').parent().children('.components-div').append(uploader);
				tr.remove();
				closeAllLoadingMessages();
				humanMsg.displayMsg(result.value);
			});
			return;
		}
		var uploader = tr.find('.uploader-div');
		tr.parents('.uploads-table').parent().children('.components-div').append(uploader);
		tr.remove();
	});
	var data = {c : container};
	rows.find('input[type="file"]').each(function(){
		var input = jQuery(this);
		var div = jQuery('<div/>');
		div.width(input.parent().width());
		div.height(input.parent().height());
		div.css({
			'background-color'	:	"transparent",
			'overflow' : 'visible'
		});
		input.after(div);
		div.offset(input.parent().offset());
		div.on("mouseenter",data,function(e){
			var uploader = e.data.c.find('.uploader-div');
			jQuery(this).append(uploader);
		});
	});
}
////------------------

// conditional selects
ReykjavikForms.createConditionalSelects = function(selector){
	var container = jQuery(selector);
	container.each(function(){
		var container = jQuery(this);
		var radios = container.find('input.condition');
		radios.each(function(){
			var input = jQuery(this);
			var hider = input.parent().children(".hiding-element");
			hider.remove();
			if(hider.length > 0){
				input.parent().children(".conditions-div").slideUp(0);
			}
		});
		var data = {container : container,radios : radios};
		radios.change(data,function(e){
			var container = e.data.container;
			var radios = e.data.radios;
			for(var i = 0; i < radios.length;i++){
				var input = jQuery(radios[i]);
				ReykjavikForms.conditionsCheckState(input,container);
			}
		});
	});
}
ReykjavikForms.conditionsCheckState = function(input,container){
	if(input.is(':checked')){
		var component = input.parent().children(".conditions-div");
		component.slideDown(400);
	}else{
		var component = input.parent().children(".conditions-div");
		component.slideUp(400);
		if(input.hasClass("clean-fields")){
			component.find('input[type="checkbox"]').removeAttr("checked");
			component.find('input[type="radio"]').removeAttr("checked");
			component.find('input[type="text"]').val("");
			component.find('textarea').val("");
		}
	}
}

//---------------------
ReykjavikForms.id = 0;
ReykjavikForms.generateId = function(){
	return ReykjavikForms.id;
}
// service frequencies
ReykjavikForms.createServiceFrequencyList = function(selector){
	var container = jQuery(selector);
	container.each(function(){
		var container = jQuery(this);
		ReykjavikForms.createTableCheckHiding(container);
		var id = container.attr('id');
		ReykjavikForms.advancedParametersFunctionList.push('ReykjavikForms.getServiceFrequencies("#' + id + '");');
	});
}

ReykjavikForms.getServiceFrequencies = function(selector){
	var container = jQuery(selector);
	var rows = container.find("table").first().find("tr");
	var list = [];
	for(var i = 0;i < rows.length;i++){
		var row = jQuery(rows[i]);
		var checkbox = row.find(".service-id");
		if(!checkbox.is(':checked')){
			continue;
		}
		var frequency = {};
		frequency.id = checkbox.val();
		frequency.frequency = row.find(".service-frequency").val();
		if(ReykjavikForms.isBlank(frequency.frequency)){
			continue;
		}
		list.push(frequency);
	}
	var map = {};
	map[container.attr('id')] = list;
	return map;
}
// -------

ReykjavikForms.createTableCheckHiding = function(container){
	var c = jQuery(container);
	c.find(".hide-item").fadeOut(0);
	c.find("table.repeat").find('input.check-row').each(function(){
		jQuery(this).change(function(){
			var input = jQuery(this);
			if(input.is(":checked")){
				input.parents("tr").first().children().children(".hide-item").fadeIn(400);
			}else{
				input.parents("tr").first().children().children(".hide-item").fadeOut(400);
			}
		});
	});
}

ReykjavikForms.isBlank = function(str) {
    return (!str || /^\s*$/.test(str));
};

//Textarea and select clone() bug workaround | Spencer Tipping
//Licensed under the terms of the MIT source code license

//Motivation.
//jQuery's clone() method works in most cases, but it fails to copy the value of textareas and select elements. This patch replaces jQuery's clone() method with a wrapper that fills in the
//values after the fact.

//An interesting error case submitted by Piotr Przybył: If two <select> options had the same value, the clone() method would select the wrong one in the cloned box. The fix, suggested by Piotr
//and implemented here, is to use the selectedIndex property on the <select> box itself rather than relying on jQuery's value-based val().

ReykjavikForms.jQueryCloneFix = function (original) {
	jQuery.fn.clone = function () {
		 var result           = original.apply(this, arguments),
		     my_textareas     = this.find('textarea').add(this.filter('textarea')),
		     result_textareas = result.find('textarea').add(result.filter('textarea')),
		     my_selects       = this.find('select').add(this.filter('select')),
		     result_selects   = result.find('select').add(result.filter('select'));
		
		 for (var i = 0, l = my_textareas.length; i < l; ++i) jQuery(result_textareas[i]).val(jQuery(my_textareas[i]).val());
		 for (var i = 0, l = my_selects.length;   i < l; ++i) result_selects[i].selectedIndex = my_selects[i].selectedIndex;
		
		 return result;
	};
};

ReykjavikForms.jQueryCloneFix(jQuery.fn.clone);
