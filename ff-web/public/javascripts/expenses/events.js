var usersData = {};
function populateUsers() {
	$.ajax({
		type: "GET",
		url:  "/users/",
		success: function (data) {
			if(data && $(data).size() > 0) {
				$('#employeeName').append('<option value=""></option>');
				for(var i = 0; i < $(data).size(); i++) {

					$('#employeeName').append('<option value="' + data[i].id + '">' + data[i].name + '</option>');
			});
				}
				/* ---------- Choosen ---------- */
				$('[data-rel="chosen"],[rel="chosen"]').chosen({allow_single_deselect: true});
			}
		},
		error: function(jqXHR, textStatus, errorthrown) {
			console.log("EVENT JS ERROR THROWN: " + JSON.stringify(jqXHR));
		}
	});
}
