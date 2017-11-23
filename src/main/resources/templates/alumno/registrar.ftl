[#include '*/commons/page-structure.ftl' /] [@main_page_login]


<div class="alert alert-danger alert-dismissible hide" role="alert"
	id="alerta">
	<span class="glyphicon glyphicon-exclamation-sign"></span>
	<p>
		<strong>Sr. Usuario:</strong>&nbsp;Algunos datos del formulario son
		incorrectos o estÃ¡n incompletos. Por favor revise la siguiente lista
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
					<h3 class="font-gob">Registrar alumno</h3>
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
						<label for="fecha">Fecha Nacimiento</label> <input type="text"
							class="form-control" name="fecha" id="fecha"
							placeholder="dd-mm-yyyy" />
					</div>
					<br />
				</div>
				</br>
				<div class="row">
					<div class="col-xs-10 col-xs-offset-1">
					<label for="curso">Curso</label> 
						<select id="selectCurso" name="curso.id" class="form-control">
							<option value="-1">Seleccione</option> 
							[#list cursos as curso]				
							<option value='${curso.id}'>${curso.nombre!""}</option>
							[/#list]
						</select>
					</div>
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
						
// 						$("#id").removeClass("form-control");
// 						$("#id").addClass('form-control is-invalid');
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
					if ($("#fecha").val() == "") {
						$("<li>Debe indicar fecha de nacimiento</li>")
								.appendTo("#listado_errores");
						$("#fecha").css("borderColor","#ff0000");
						$("#fecha").css("borderWidth","1px");
						$("#fecha").css("borderStyle","dotted");
						i++;
					}		
					if ($("#selectCurso").val()=="-1"){
						$("<li>Debe seleccionar un curso</li>")
						.appendTo("#listado_errores");
						$("#selectCurso").css("borderColor","#ff0000");
						$("#selectCurso").css("borderWidth","1px");
						$("#selectCurso").css("borderStyle","dotted");
						i++;
					}

					if (i == 0) {
												
						$("#formulario").attr("action", "registrar").submit();
						$("<li>Alumno registrado exitosamente</li>").appendTo(
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
	
	$("#nombre").keypress(function (key) {
        window.console.log(key.charCode)
        if ((key.charCode < 97 || key.charCode > 122)//letras mayusculas
            && (key.charCode < 65 || key.charCode > 90) //letras minusculas
            && (key.charCode != 45) //retroceso
            && (key.charCode != 241) //ñ
             && (key.charCode != 209) //Ñ
             && (key.charCode != 32) //espacio
             && (key.charCode != 225) //á
             && (key.charCode != 233) //é
             && (key.charCode != 237) //í
             && (key.charCode != 243) //ó
             && (key.charCode != 250) //ú
             && (key.charCode != 193) //Á
             && (key.charCode != 201) //É
             && (key.charCode != 205) //Í
             && (key.charCode != 211) //Ó
             && (key.charCode != 218) //Ú

            )
            return false;
    });
	
	
</script>

[/@main_page_login]
