[#include '*/commons/page-structure.ftl' /] [@main_page_login]

<form id="myForm" method="post">
	<div>
		<button type="button" class="btn btn-primary-gradient btn-lg"
			id="btnAgregarFilas">
			<span class="glyphicon glyphicon-plus"></span><span class="font-gob">Agregar
				Fila</span>
		</button>

	</div>
	</br>




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
				<td></td>
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

	});

	var i = 0;
		
	function funcAgregarFilas() {
		$("#DatatableCursos").append(
				$('<tr>').append(
						$('<td>').append(
								$('<input>').attr('type', 'checkbox').addClass(
										'form-control').attr('name',
										'idCurso[' + i + ']'))).append(
						$('<td>').append(
								$('<input>').attr('type', 'text').addClass(
										'form-control').attr('name',
										'curso[' + i + '].id')
						.attr("id","i"+i)				
						)).append(
						$('<td>').append(
								$('<input>').attr('type', 'text').addClass(
										'form-control').attr('name',
										'curso[' + i + '].nombre')
										.attr("id","c"+i))).append(
						$('<td>').append(
								"<td><button type='button' class='btn btn-primary btn-sm' onclick='agregarCursos("+i+")'><span class='glyphicon glyphicon-pencil'></span>&nbsp;<span class='font-gob'>Agregar</span></button></td>").append(
						$('<td>'))))
		i++;
	};
	
	function agregarCursos(id) {
		$.ajax({
			url: "registrarCursoJSON",
			dataType: "json",
			type: "post",
			data: {id:$("#i"+id).val(), curso:$("#c"+id).val()},
			success: function(data){
				iniciarTabla(data);
			}
		});
	}
	
	function iniciarTabla(data){
		$("#DatatableCursos").DataTable({
			"processing"	: true,
			"destroy": true,
			"lengthChange"	: 10,
			"searching"		: true,
			"info"			: true,
			"paging"		: true,
			"ordering"		: true,
			"pagingType" 	: "full_numbers",
			"lengthMenu"	: [[10, 25, 50, 100, -1], [10, 25, 50, 100, "Todos"]],
			"columns": [
			            { "data": "",
			            	"render" : function(data,type,full){
			            		return '<input type="checkbox" name="idCurso" class="chkCheckBoxId" value="'+full.id+'" />';
			            	}},
			            { "data": "id"},
			            { "data": "nombre"},
			            {"data":""}
			            ], 
			"language": {
				"url": "${context}/locals/json/i18n/dataTables.spanish.json"
			}
		});
	}
</script>
[/@main_page_login]
