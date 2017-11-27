[#include '*/commons/page-structure.ftl' /] [@main_page_login]

<div
	class="container-fluid content with-outline rounded-top rounded-bottom">
	<div class="row">
		<div class="col-sm-4 col-sm-offset-4">
			<h3 class="font-gob">Editar</h3>
		</div>
	</div>
	<form id="form" method="post" action="${context}/alumno/editarcurso">
		<div class="row">
			<div class="col-sm-4 col-sm-offset-4">
				<input type="hidden" class="form-control" name="id" id="id"
					value="${id!''}" />
			</div>
		</div>
		<br />
		<div class="row">
			<div class="col-sm-4 col-sm-offset-4">
				<label for="nombre">Nombre</label> <input type="text"
					class="form-control" name="nombre" id="nombre" placeholder="Nombre" />
			</div>
			<br />
		</div>		
		<br />
		<div class="row">
			<div class="col-sm-4 col-sm-offset-4">
				<p>
					<button type="submit" id="Editar" class="btn btn-primary-gradient">
						<span class="glyphicon glyphicon-edit"></span>&nbsp;&nbsp;<span
							class="font-gob">Editar</span>
					</button>
				</p>
			</div>
		</div>
	</form>
</div>
</br>

[/@main_page_login]
