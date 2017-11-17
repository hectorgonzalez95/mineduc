[#include '*/commons/page-structure.ftl' /] [@main_page_login]

<div>
	<ul class="nav nav-tabs nav-justified">
		<li role="presentation" class="active" id="tab-1"><a href="#">Datos&nbsp;Establecimiento</a>
		</li>
		<li role="presentation" id="tab-2"><a href="#">Datos&nbsp;Docentes</a>
		</li>
		<li role="presentation" id="tab-3"><a href="#">Datos&nbsp;SEP</a>
		</li>
	</ul>
</div>

</br>

<div id="otro-menu-mineduc" class="navbar navbar-default"
	role="navigation">

	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="index.html"><span
			class="glyphicon glyphicon-home icon-small"></span> Static 2</a>
	</div>
	<div class="navbar-collapse collapse">
		<ul class="nav navbar-nav">
			<li><a href="#about">Acerca de</a></li>
			<li><a href="#contact">Contacto</a></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown">Men&uacute; Aplicaci&oacute;n<b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li class="dropdown-header">Reportes</li>
					<li><a href="#">Reporte 1</a></li>
					<li><a href="#">Reporte 2</a></li>
					<li class="divider"></li>
					<li class="dropdown-header">Usuarios</li>
					<li><a href="#">Listado</a></li>
					<li><a href="#">Otros</a></li>
				</ul></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li class="disabled"><a href="#">Estático Inactivo</a></li>
			<li class="active"><a href="#">Estático Activo</a></li>
			<li><a href="#">Fijo top</a></li>
		</ul>
	</div>
	<!--/.nav-collapse -->
</div>


[/@main_page_login]
