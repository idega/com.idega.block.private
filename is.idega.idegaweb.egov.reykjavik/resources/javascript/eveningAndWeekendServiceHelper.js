var EveningAndWeekendServiceHelper = {};

jQuery(document).ready(function(){
	jQuery('input:checkbox.value').each(function() {
				
		jQuery(this).change(function() {
			EveningAndWeekendServiceHelper.changeVisibility(this);
		});
	});
});

EveningAndWeekendServiceHelper.changeVisibility = function(checkbox) {
	
};
