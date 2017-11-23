[#include '*/commons/page-structure.ftl' /] [@main_page_login]

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
	</a> &nbsp;
	<button type="button" class="btn btn-primary-gradient btn-lg"
		id="btnEliminarCursos">
		<span class="glyphicon glyphicon-minus"></span><span class="font-gob">Eliminar
			Cursos</span>
	</button>
</div>
</br>
<div>
	<form id="myForm" method="post">
		<table id="DatatableCursos" class="table mineduc" cellspacing="0"
			width="100%">
			<thead>
				<tr>
					<th>Id</th>
					<th>Nombre</th>
					<th>Accion</th>
				</tr>
			</thead>
			<tbody>
				[#list cursos as curso]
				<tr>
					<td>${curso.id!''}</td>
					<td>${curso.nombre}</td>
					<td><a href="">
							<button type="button" class="btn btn-primary btn-xs">
								<span class="glyphicon glyphicon-pencil"></span>&nbsp;<span
									class="font-gob">Editar</span>
							</button>
					</a> &nbsp;&nbsp; <a href="">
							<button type="button" class="btn btn-danger btn-xs">
								<span class="glyphicon glyphicon-remove"></span>&nbsp;<span
									class="font-gob">Eliminar</span>
							</button>
					</a>
				</tr>
				[/#list]
			</tbody>
		</table>
	</form>
</div>

<script type="text/javascript">
	function editar(id) {
		$("#id").val(id);
		$("#myForm").prop("action", "edit").submit();
	}

	function eliminar(id) {
		confirmDialog("Esta seguro que desea eliminar?", function() {
			$("#id").val(id);
			$("#myForm").prop("action", "eliminar").submit();
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

		$("#DatatableCursos").blur(function() {

		});

	});
	
	var i=0;

	function funcAgregarFilas() {
		$("#DatatableCursos").append(
				$('<tr>').append(
						$('<td>').append(
								$('<input>').attr('type', 'text').addClass(
										'form-control miClaseId').attr('name',
										'curso['+i+'].id'))).append(
						$('<td>').append(
								$('<input>').attr('type', 'text').addClass(
										'form-control miClaseNombre').attr(
										'name', 'curso['+i+'].nombre')))
						.append($('<td>')));
		i++;
	}

	function agregarCursos() {
		$("#myForm").attr("action", "${context}/alumno/registrarCurso").submit();
	}
</script>
[/@main_page_login]
