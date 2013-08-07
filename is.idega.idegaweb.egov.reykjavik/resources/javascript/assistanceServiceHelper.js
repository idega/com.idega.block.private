var AssistanceServiceHelper = {};

AssistanceServiceHelper.RULES = {
	string_engagedSocialActivity : {
		required: true
	},
	
	string_aidReceivedBefore : {
		required: true
	},
	
	string_aidReceivedBeforeTime : {
		required: true
	},
	
	long_numberOfTimesInMonthHelpIsNeeded : {
		required: true,
		number: true
	},
	
	long_numberOfMonthWhenHelpIsNeeded : {
		required: true,
		number: true
	},
	
	long_percentOfDisability : {
		required: true,
		number: true,
		max: 100
	}
};

ReykjavikForms.validationRules = jQuery.extend(
		ReykjavikForms.validationRules,
		AssistanceServiceHelper.RULES);

ReykjavikForms.advancedParametersFunctionList.push('AssistanceServiceHelper.collectHabitants();');

AssistanceServiceHelper.collectHabitants = function() {
	var relations = [];
	
	var rows = jQuery('tr.repeat-item', jQuery('table.habitantTable'));
	for (var i = 0; i < rows.length; i++) {
		var result = AssistanceServiceHelper.collectHabitant(rows[i]);
		if (result != null) {
			relations.push(result);
		}
	}

	var relationId = jQuery('table.habitantTable').parent().attr('id');
	var map = {};
	map[relationId] = relations;
	return map;
};

AssistanceServiceHelper.collectHabitant = function(row) {
	if (row == null || row.length == 0) {
		return null;
	}
	
	var relation = jQuery("select[name*='relation']", jQuery(row)).val();
	if (relation == null || relation == '' || relation == '-1') {
		return null;
	}
	
	var name = jQuery("input[name*='name']", jQuery(row)).val();
	if (name == null || name == '') {
		return null;
	}
	
	var personalId = jQuery("input[name*='personalId']", jQuery(row)).val();
	if (name == null || name == '') {
		return null;
	}
	
	return {
		'relation': relation,
		'name': name,
		'personalId': personalId
	};
};

AssistanceServiceHelper.insert = function() {
	var row = jQuery('tr.repeat-item', jQuery('table.habitantTable'))[0];
	var clone = jQuery(row).clone(true);
	jQuery(clone).appendTo('table.habitantTable > tbody:last');
	jQuery(clone).removeClass('hiddenInput');
};

AssistanceServiceHelper.remove = function(commandButton) {
	jQuery(commandButton).parent().parent().parent().remove();
};