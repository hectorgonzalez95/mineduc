[#include '*/commons/page-structure.ftl' /] [@main_page_login]

<div class="navbar navbar-default" role="navigation">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse"></button>
	</div>
	<div class="navbar-collapse collapse">
		<ul class="nav navbar-nav">

			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown">Men&uacute; Aplicaci&oacute;n<b
					class="caret"></b></a>
				<ul class="dropdown-menu">
					<li class="dropdown-header">Alumnos</li>
					<li><a href="${context}/alumno/registrar">Agregar alumnos</a></li>
					<li class="divider"></li>
					<li class="dropdown-header">Cursos</li>
					<li><a href="${context}/alumno/registrarCurso">Agregar cursos</a></li>
				
					<li><a href="${context}/alumno/cursos">Ver cursos</a></li>
				</ul></li>
		</ul>
	</div>

	</br>

	<form id="form" class="form-horizontal" method="get">
		<div>
			<table id="ejemploDatatable" class="table mineduc" cellspacing="0"
				width="100%">
				<thead>
					<tr>
						<th>Nombre</th>
						<th>Fecha</th>
						<th>Curso</th>
						<th>Accion</th>
					</tr>
				</thead>
				<tbody>
					[#list datos as dato]
					<tr>
						<td>${dato.nombre?capitalize!''}</td>
						<td>${dato.fecha?date?string("dd-MM-yyyy")!''}</td>
						<td>${dato.curso.nombre!''}</td>
						<td><a href="javascript:editar('${dato.id?c}')">
								<button type="button" class="btn btn-primary btn-xs">
									<span class="glyphicon glyphicon-pencil"></span>&nbsp;<span
										class="font-gob">Editar</span>
								</button>
						</a> &nbsp;&nbsp; <a href="javascript:eliminar('${dato.id?c}')">
								<button type="button" class="btn btn-danger btn-xs">
									<span class="glyphicon glyphicon-remove"></span>&nbsp;<span
										class="font-gob">Eliminar</span>
								</button>
						</a>
					</tr>
					[/#list]
				</tbody>
			</table>

			<!-- Modal confirm  -->
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
		</div>

		<input type="hidden" id="id" name="id" />
	</form>
	<script type="text/javascript">
		function editar(id) {
			$("#id").val(id);
			$("#form").prop("action", "edit").submit();
		}

		function eliminar(id) {
			confirmDialog("Esta seguro que desea eliminar?", function() {
				$("#id").val(id);
				$("#form").prop("action", "eliminar").submit();
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

		$(document)
				.ready(
						function() {
							$('#ejemploDatatable')
									.DataTable(
											{
												"language" : {
													"url" : "${context}/locals/json/i18n/dataTables.spanish.json"
												}
											});
						});
		
		
		
	</script>

	[/@main_page_login]