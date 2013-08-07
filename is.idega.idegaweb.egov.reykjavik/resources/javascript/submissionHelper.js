var SubmissionHelper = {};

jQuery(window).load(function() {
	jQuery('.decision_dropdown').each(function() {
		SubmissionHelper.changeButtonAvailability();
				
		jQuery(this).change(function() {
			SubmissionHelper.changeButtonAvailability();
		});
	});

	closeAllLoadingMessages();
});

SubmissionHelper.changeButtonAvailability = function() {
	var value = jQuery('.decision_dropdown').find(':selected').val();
	if (value == 'true') {
		jQuery('input', jQuery('.fbc_button_grant')).removeAttr('disabled');
		jQuery('input', jQuery('.fbc_button_deny')).attr('disabled', 'disabled');
	} else if (value == 'false') {
		jQuery('input', jQuery('.fbc_button_deny')).removeAttr('disabled');
		jQuery('input', jQuery('.fbc_button_grant')).attr('disabled', 'disabled');
	} else {
		jQuery('input', jQuery('.fbc_button_deny')).attr('disabled', 'disabled');
		jQuery('input', jQuery('.fbc_button_grant')).attr('disabled', 'disabled');

	}
};