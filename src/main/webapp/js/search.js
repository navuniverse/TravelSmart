$(function() {
	var availableTags = [ "AGARTALA", "AGRA", "AHMEDABAD", "ALLAHABAD",
			"AMRITSAR", "BANGALORE", "BHOPAL", "BHUBANESHWAR", "CALICUT",
			"CHANDIGARH", "CHENNAI", "DEHRADUN", "DELHI", "GOA", "HYDERABAD",
			"INDORE", "JAIPUR", "JAMMU", "KANPUR", "KOCHI", "KOLKATA",
			"MUMBAI", "MYSORE", "NAGPUR", "NASIK", "PATHANKOT", "PATNA",
			"PONDICHERRY", "PORBANDAR", "PORT BLAIR", "PUNE", "RAIPUR",
			"RAJKOT", "RANCHI",

	];
	function split(val) {
		return val.split(/,\s*/);
	}
	function extractLast(term) {
		return split(term).pop();
	}
	$("#tags")
	// don't navigate away from the field on tab when selecting an item
	.bind(
			"keydown",
			function(event) {
				if (event.keyCode === $.ui.keyCode.TAB
						&& $(this).data("ui-autocomplete").menu.active) {
					event.preventDefault();
				}
			}).autocomplete(
			{
				minLength : 0,
				source : function(request, response) {
					// delegate back to autocomplete, but extract the last term
					response($.ui.autocomplete.filter(availableTags,
							extractLast(request.term)));
				},
				focus : function() {
					// prevent value inserted on focus
					return false;
				},
				select : function(event, ui) {
					var terms = split(this.value);
					// remove the current input
					terms.pop();
					// add the selected item
					terms.push(ui.item.value);
					// add placeholder to get the comma-and-space at the end
					terms.push("");
					this.value = terms.join("");
					return false;
				}
			});
	$("#tags2")
	// don't navigate away from the field on tab when selecting an item
	.bind(
			"keydown",
			function(event) {
				if (event.keyCode === $.ui.keyCode.TAB
						&& $(this).data("ui-autocomplete").menu.active) {
					event.preventDefault();
				}
			}).autocomplete(
			{
				minLength : 0,
				source : function(request, response) {
					// delegate back to autocomplete, but extract the last term
					response($.ui.autocomplete.filter(availableTags,
							extractLast(request.term)));
				},
				focus : function() {
					// prevent value inserted on focus
					return false;
				},
				select : function(event, ui) {
					var terms = split(this.value);
					// remove the current input
					terms.pop();
					// add the selected item
					terms.push(ui.item.value);
					// add placeholder to get the comma-and-space at the end
					terms.push("");
					this.value = terms.join("");
					return false;
				}
			});
});

/**
 * 
 * JS to apply filter on search result
 * 
 */
$(document).ready(function() {
	$('select').change(function(E) {
		var id = $(this).find(": selected").text();
		if (id != "ALL") {

			$("#search_data").find("tr").hide();
			var data = id.split(" ");
			var jo = $("#search_data").find('tr');
			$.each(data, function(i, v) {
				jo = jo.filter("*:contains('" + v + "')");
			});
			jo.show();
		} else {
			$("#search_data").find("tr").show();
		}
	});
});
