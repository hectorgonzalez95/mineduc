[#include '*/commons/page-structure.ftl' /] [@main_page_login]


<div class="row">
	<div id="div_comuna" class="form-group item_form">
		<label class="col-sm-4">Región</label>
		<div class="col-sm-6">
			<select class="form-control input-sm" id="region" name="idRegion">
				<option value="0">Seleccione</option>
			</select>
		</div>
	</div>
	<br />
	<div id="div_deprovs" class="form-group item_form">
		<label class="col-sm-4">DeProvs</label>
		<div class="col-sm-6">
			<select class="form-control input-sm" id="deprovs" name="idDeprov">
				<option value="0">Seleccione</option>
			</select>
		</div>
	</div>
	<br />
	<div id="div_comuna" class="form-group item_form">
		<label class="col-sm-4">Comuna</label>
		<div class="col-sm-6">
			<select class="form-control input-sm" id="comuna" name="idComuna">
				<option value="0">Seleccione</option>
			</select>
		</div>
	</div>
</div>

<br />
<script>
	$(document).ready(function() {

		cargarRegiones('${jsonRegiones}');

		$("#region").change(function() {
			cargarDeProvs($(this).val());
		});

		$("#deprovs").change(function() {
			cargarComunas($("#region").val(), $(this).val());
		});

	});

	function cargarDeProvs(codigoRegionSeleccionado) {
		$("#deprovs").empty();
		$("#deprovs").append($($("<option>").val("0").html("Seleccione")));
		$("#comuna").empty();
		$("#comuna").append($($("<option>").val("0").html("Seleccione")));

		$.each(objRegiones, function(i, objRegion) {
			if (objRegion.idRegion == codigoRegionSeleccionado) {
				$.each(objRegion.deprovs, function(j, objDeprov) {
					$("#deprovs").append(
							$($("<option>").val(objDeprov.idDeprov).html(
									objDeprov.nombreDeprov)));
				});
			}
		});

	}

	function cargarComunas(codigoRegionSeleccionado, codigoDeprovSeleccionado) {
		$("#comuna").empty();
		$("#comuna").append($($("<option>").val("0").html("Seleccione")));

		$.each(objRegiones, function(i, objRegion) {
			if (objRegion.idRegion == codigoRegionSeleccionado) {
				$.each(objRegion.deprovs, function(j, objDeprov) {
					if (objDeprov.idDeprov == codigoDeprovSeleccionado) {
						$.each(objDeprov.comunas, function(keyComuna,valueComuna) {
							$("#comuna").append(
									$($("<option>").val(valueComuna.idComuna)
											.html(valueComuna.nombreComuna)));

						});
					}
				});
			}
		});
	}

	function cargarRegiones(jsonRegiones) {
		objRegiones = JSON.parse(jsonRegiones);

		$.each(objRegiones, function(key, value) {

			$("#region").append(
					$($("<option>").val(value.idRegion)
							.html(value.nombreRegion)));

		});
	}
</script>


[/@main_page_login]
