[#include '*/commons/page-structure.ftl' /] [@main_page_login]


<div class="alert alert-danger alert-dismissible hide" role="alert"
	id="alerta">
	<span class="glyphicon glyphicon-exclamation-sign"></span>
	<p>
		<strong>Sr. Usuario:</strong>&nbsp;Algunos datos del formulario son
		incorrectos o están incompletos. Por favor revise la siguiente lista
		para continuar:
	</p>
	<ul id="listado_errores"></ul>
</div>
<div>
	[#if errores??]
	<div class="alert alert-warning alert-dismissible" role="alert">
		<span class="glyphicon glyphicon-warning-sign"></span>
		<p>
			<br />Errores
		</p>
		[#list errores as error]
		<p>
			<br>&nbsp;&nbsp;[@spring.message error.codes[0] /]
		</p>
		[/#list]
	</div>
	[/#if]
</div>

<div class="row">
	<div class="col-md-4 col-md-offset-4">
		<div
			class="container-fluid content with-outline rounded-top rounded-bottom">
			<div class="row">
				<div class="col-xs-10 col-xs-offset-1">
					<h3 class="font-gob">Registrar Curso</h3>
				</div>
			</div>
			<br />
			<form id="formulario" method="post">

				<div class="row">
					<div class="col-xs-10 col-xs-offset-1">
						<label for="form_name">ID</label> <input type="text"
							class="form-control" name="id" id="id" placeholder="Id" />
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-xs-10 col-xs-offset-1">
						<label for="nombre">Nombre</label> <input type="text"
							class="form-control" name="nombre" id="nombre"
							placeholder="Nombre" />
					</div>
					<br />
				</div>
				<br />			
				
				<div class="row">
					<div class="col-xs-10 col-xs-offset-1">
						<p>
							<button type="button" id="ingresar"
								class="btn btn-primary-gradient">
								<span class="glyphicon glyphicon-log-in"></span>&nbsp;&nbsp;<span
									class="font-gob">Registrar</span>
							</button>
						</p>

					</div>
				</div>


			</form>
		</div>
	</div>
</div>
</br>

<script type="text/javascript">
	$(function() {

		$("#ingresar").on(
				"click",
				function() {
					var i = 0;
					$("#listado_errores").empty();
					$("#ingresar").prop("disabled", true);

					if ($("#id").val() == "") {
						$("<li>Debe indicar Id</li>").appendTo(
								"#listado_errores");
						
						$("#id").css("borderColor","#ff0000");
						$("#id").css("borderWidth","1px");
						$("#id").css("borderStyle","dotted");							
						i++;
					}
					if ($.trim($("#nombre").val()) == "") {
						$("<li>Debe indicar nombre</li>").appendTo(
								"#listado_errores");
						$("#nombre").css("borderColor","#ff0000");
						$("#nombre").css("borderWidth","1px");
						$("#nombre").css("borderStyle","dotted");
						i++;
					}						

					if (i == 0) {
						$("#formulario").attr("action", "${context}/curso/registrar").submit();
						$("<li>Curso registrado exitosamente</li>").appendTo(
								"#listado_errores");
						$("#alerta").removeClass("alert alert-danger hide");
						$("#alerta").addClass('alert alert-success');
				   
						    setTimeout(function() {
						        $("#alerta").fadeIn(1500);
						    },5000);
						
					} else {
						$("#alerta").removeClass("hide");
						$("#alerta").fadeIn("slow");
						$("html, body").scrollTop($("#alerta").offset().top);
						$("#ingresar").prop("disabled", false);
					}
				});
	});
	
	$("#id").on('input', function () { 
	    this.value = this.value.replace(/[^0-9]/g,'');
	});
	
	
	
	
</script>

[/@main_page_login]
