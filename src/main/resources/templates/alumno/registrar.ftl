[#include '*/commons/page-structure.ftl' /] [@main_page_login]

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>registrar</title>
<meta charset="utf-8" />
</head>
<body>

	<div class="row">
		<div class="col-md-4 col-md-offset-4">
			<div
				class="container-fluid content with-outline rounded-top rounded-bottom">
				<div class="row">
					<div class="col-xs-10 col-xs-offset-1">
						<h3 class="font-gob">Registro</h3>
					</div>
				</div>
				<br />
				<form id="form" method="post" action="${context}/alumno/registrar">

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
								class="form-control" name="fecha" id="fecha" placeholder="Fecha Nacimiento" />
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

</body>
</html>


[/@main_page_login]
