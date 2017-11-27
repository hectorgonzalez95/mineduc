[#include '*/commons/page-structure.ftl' /] [@main_page_login]

<form id="myForm" method="post">
<div>
	

		<button type="button" class="btn btn-primary-gradient btn-lg"
			id="btnAgregarFilas">
			<span class="glyphicon glyphicon-plus"></span><span class="font-gob">Agregar
				Fila</span>
		</button>
		&nbsp; <a href="javascript:agregarCursos()">
			<button type="button" class="btn btn-primary-gradient btn-lg"
				id="btnAgregarCursos">
				<span class="glyphicon glyphicon-plus"></span><span class="font-gob">Agregar
					Cursos</span>
			</button>
		</a> &nbsp; <a href="javascript:eliminar()">
			<button type="button" class="btn btn-primary-gradient btn-lg"
				id="btnEliminarCursos">
				<span class="glyphicon glyphicon-minus"></span><span
					class="font-gob">Eliminar Cursos</span>
			</button>
		</a>
</div>
</br>
[#if !cursos?has_content]
<div class="alert alert-info alert-dismissible" role="alert">
	<span class="glyphicon glyphicon-info-sign"></span>
	<button type="button" class="close" data-dismiss="alert"
		aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	&nbsp;
	<p>${error}</p>
</div>

[/#if] 

<div id="divForm">

	<table id="DatatableCursos" class="table mineduc" width="100%" />
	<thead>
		<tr>
			<th><input class="text-center" type="checkbox" id="selectAll" /></th>
			<th>Id</th>
			<th>Nombre</th>
			<th>Accion</th>
		</tr>
	</thead class="thead-dark">
	<tbody>
		[#list cursos as curso]
		<tr>
			<td><input type="checkbox" name="idCurso" class="chkCheckBoxId"
				value="${curso.id!''}" /></td>
			<td>${curso.id!''}</td>
			<td>${curso.nombre}</td>
			<td><a href="javascript:editar(${curso.id!''})">
					<button type="button" class="btn btn-primary btn-xs">
						<span class="glyphicon glyphicon-pencil"></span>&nbsp;<span
							class="font-gob">Editar</span>
					</button>
		</tr>
		[/#list]
	</tbody>
	</table>


	<div class="modal fade" id="confirmModal"
		style="display: none; z-index: 1050;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">X</button>
					<h4 class="modal-title" id="myModalLabel">Confirmaci&oacute;n</h4>
				</div>
				<div class="modal-body" id="confirmMessage"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary-gradient"
						id="confirmOk">Ok</button>
					<button type="button" class="btn btn-danger" id="confirmCancel">Cancelar</button>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" name="id" id="id" />

</div>

</form>



<script type="text/javascript">
	function editar(id) {
		$("#id").val(id);
		$("#myForm").prop("action", "editCurso").submit();
	}

	function eliminar() {
		
		confirmDialog("Esta seguro que desea eliminar?", function() {

			$(".chkCheckBoxId:checked").each(function() {
				$("#myForm").prop("action", "eliminarCursos").submit();
			});

		});
	}

	function confirmDialog(message, onConfirm) {
		var fClose = function() {
			modal.modal("hide");
		};
		var modal = $("#confirmModal");
		modal.modal("show");
		$("#confirmMessage").empty().append(message);
		$("#confirmOk").one('click', onConfirm);
		$("#confirmOk").one('click', fClose);
		$("#confirmCancel").one("click", fClose);
	}

	$(document).ready(function() {
		$('#DatatableCursos').DataTable({

			"language" : {
				"url" : "${context}/locals/json/i18n/dataTables.spanish.json"
			}

		});

		$("#btnAgregarFilas").on('click', funcAgregarFilas);

		$('#selectAll').click(function(e) {
			var table = $(e.target).closest('table');
			$('td input:checkbox', table).prop('checked', this.checked);
		});
		
		
		[#if cursos?has_content]		
			$("#divForm").show();
		[#else]
			$("#divForm").hide();
		[/#if]
		
	});

	var i = 0;
	function funcAgregarFilas() {
		$("#divForm").show();

		$("#DatatableCursos")
				.append(
						$('<tr>')
								.append(
										$('<td>').append(
												$('<input>').attr('type',
														'checkbox').addClass(
														'form-control').attr(
														'name', 'idCurso')))
								.append(
										$('<td>').append(
												$('<input>').attr('type',
														'text').addClass(
														'form-control').attr(
														'name',
														'curso[' + i + '].id')))
								.append(
										$('<td>').append(
												$('<input>').attr('type',
														'text').addClass(
														'form-control').attr(
														'name',
														'curso[' + i
																+ '].nombre')))
								.append($('<td>')))
		i++;
	};

	function agregarCursos() {
		$("#myForm").attr("action", "${context}/alumno/registrarCurso")
				.submit();
	}
</script>
[/@main_page_login]
