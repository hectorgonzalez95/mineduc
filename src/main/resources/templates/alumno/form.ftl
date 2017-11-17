[#include '*/commons/page-structure.ftl' /] [@main_page_login]

<a href="${context}/alumno/menu">
	<button type="button" class="btn-primary-gradient btn-lg">
		<span class="glyphicon glyphicon-plus"></span> <span class="font-gob">Probando menu</span>
	</button>
</a>

<a href="${context}/alumno/registrar">
	<button type="button" class="btn-primary-gradient btn-lg">
		<span class="glyphicon glyphicon-plus"></span> <span class="font-gob">Agregar</span>
	</button>
</a>

<form id="form" class="form-horizontal" method="get">

	<div>
		<table class="table mineduc">
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Fecha</th>
					<th>Accion</th>
				</tr>
			</thead>
			<tbody>
				[#list datos as dato]
				<tr>
					<td>${dato.nombre?capitalize!''}</td>
					<td>${dato.fecha?date?string("dd-MM-yyyy")!''}</td>

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
</script>

[/@main_page_login]

