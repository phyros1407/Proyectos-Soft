
<!-- MODAL DESACTIVAR  -->


<div class="modal fade" id="mod" role="dialog">
    <div class="modal-dialog modal-sm" id="modal-dial">
      <div class="modal-content">
        <div class="modal-header" style="background-color:#8A0829;">
          <button type="button" class="close" data-dismiss="modal" style="color:white;">&times;</button>
          <h3 class="modal-title"><strong style="color:white;">Desactivar Account</strong></h3>
        </div>
        <div class="modal-body">
        
          <p>¿Esta seguro que desea desactivar la cuenta de este usuario?</p>
        	
        </div>
        <div class="modal-footer">
          <form method="post" action="Persona">
          <input type="hidden" name="ph" value="ifsDu">
          <input type="hidden" name="codigo" id="codigo2" />
          <button type="submit" class="btn btn-default">Desactivar</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
          </form>
        </div>
      </div>
    </div>
  </div>


<!-- MODAL REACTIVAR  -->


<div class="modal fade" id="mod1" role="dialog">
    <div class="modal-dialog modal-sm" id="modal-dial">
      <div class="modal-content">
        <div class="modal-header" style="background-color:#8A0829;">
          <button type="button" class="close" data-dismiss="modal" style="color:white;">&times;</button>
          <h3 class="modal-title" style="color:white;"><b style="color:white;">Reactivar Account</b></h3>
        </div>
        <div class="modal-body">
        
          <p>¿Esta seguro que desea reactivar esta cuenta?</p>
        	
        </div>
        <div class="modal-footer">
          <form method="post" action="Persona">
          <input type="hidden" name="codigo" id="codigo2" />
          <input type="hidden" name="ph" value="ifsCu">
          <button type="submit" class="btn btn-default">Reactivar</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
          </form>
        </div>
      </div>
    </div>
  </div>



<!-- MODAL AGREGAR -->

<div class="modal fade" id="mod2" role="dialog">
    <div class="modal-dialog" id="modal-dial2">
      <div class="modal-content">
        <div class="modal-header" style="background-color:#8A0829;">
          <button type="button" class="close" data-dismiss="modal" style="color:white;">&times;</button>
          <h3 class="modal-title" style="color:white;" ><span class="glyphicon glyphicon-user" style="width: 31px; height: 29px; "  ></span><strong style="color:white;" > Nuevo Usuario</strong></h3>
        </div>
        <div class="modal-body">
         <form class="form-horizontal" role="form" method="post" action="Persona" id="agregar" onsubmit="return validar();">
         		  <input type="hidden" name="ph" value="ifsAu">
                  <div class="form-group">
                    
                    <div class="col-sm-12">
                        <input type="text" class="form-control"  id="nombre" placeholder="Nombres" name="nombre" required maxlength="25" onkeypress="return soloLetras(event)"/>
                    </div>
                  </div>
                  <div class="form-group">
                    
                    <div class="col-sm-6"> 
                        <input type="text" class="form-control" id="apeP" placeholder="Apellido Paterno" name="apellidoP" required maxlength="25" onkeypress="return soloLetras(event)"/>
                    </div>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="apeM" placeholder="Apellido Materno" name="apellidoM" required maxlength="25" onkeypress="return soloLetras(event)"/>
                    </div>
                  </div>
                  <div class="form-group">
                    
                    <div class="col-sm-12">
                        <input type="email" class="form-control" id="correo" placeholder="Correo @" name="correo" required/>
                    </div>
                  </div>
                   <div class="form-group">
                    
                    <div class="col-sm-12">
                        <input type="text" class="form-control" id="celular" placeholder="Celular o Telefono" name="celular" required maxlength="9"/>
                    	<b id="errorC" style="color:red;"></b>
                    </div>
                  </div>
                  <div class="form-group">
                  	<div class="col-sm-12">
                    <select name="permiso"  class="form-control" id="sel1" required>
                    	<option selected>Seleccionar Permisos del Usuarios</option>
                    	<option value="1" >Administrador del Sistema</option>
                    	<option value="2">Administrador de Inventario</option>
                    	<option value="3">Analista</option>
                    </select>
                    <b id="errorS" style="color:red;"></b>
                    </div>
                  </div> 
              </form>    
        </div>
        <div class="modal-footer"> 
        	<button type="submit" class="btn btn-default btn btn-success btn-md" form="agregar" >Agregar Usuario</button>
        	<button type="button" class="btn btn-primary btn-md" data-dismiss="modal">Cancelar</button> 
        </div>
      </div>
    </div>
  </div>


<!-- MODAL ACTUALIZAR -->

<div class="modal fade" id="mod3" role="dialog">
    <div class="modal-dialog" id="modal-dial2">
      <div class="modal-content">
        <div class="modal-header" style="background-color:#8A0829;">
          <button type="button" class="close" data-dismiss="modal" style="color:white;">&times;</button>
          <h3 class="modal-title" style="color:white;" ><span class="glyphicon glyphicon-user" style="width: 31px; height: 29px; "  ></span><strong style="color:white;" > Actualizar informacion del Usuario</strong></h3>
        </div>
        <div class="modal-body" >
         <form class="form-horizontal" role="form" method="post" action="Persona" id="act" onsubmit="return validar2()">
         		  <input type="hidden" name="ph" value="ifsMu">
                  <div class="form-group">
                    <div class="col-sm-12">
                    	<input type="hidden" id="codigo3" name="codigo">
                    	<label for="sel1">Nombre Completo</label>
                        <input type="text" class="form-control"  id="nombre" placeholder="Nombres" name="nombre" required maxlength="25" onkeypress="return soloLetras(event)"/>
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-sm-6"> 
                    	<label for="sel1">Apellido Paterno</label>
                        <input type="text" class="form-control" id="apeP" placeholder="Apellido Paterno" name="apellidoP" required maxlength="25" onkeypress="return soloLetras(event)"/>
                    </div>
                    <div class="col-sm-6">
                    	<label for="sel1">Apellido Materno</label>
                        <input type="text" class="form-control" id="apeM" placeholder="Apellido Materno" name="apellidoM" required maxlength="25" onkeypress="return soloLetras(event)"/>
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-sm-12">
                    	<label for="sel1">Correo</label>
                        <input type="email" class="form-control" id="correo" placeholder="Correo @" name="correo" required/>
                    </div>
                  </div>
                   <div class="form-group">
                    <div class="col-sm-12">
                    	<label for="sel1">Celular</label>
                        <input type="text" class="form-control" id="celular1" placeholder="Celular o Telefono" name="celular" required maxlength="9"/>
                    	<b id="errorC1" style="color:red;"></b>
                    </div>
                  </div>
                  <div class="form-group">
                  	<div class="col-sm-12">
                  	<label for="sel1">Perfil del Usuario</label>
                    <select name="permiso"  class="form-control" id="sel2" >
                    	<option selected>Seleccionar Permisos del Usuarios</option>
                    	<option value="1" >Administrador del Sistema</option>
                    	<option value="2">Administrador de Inventario</option>
                    	<option value="3">Analista</option>
                    </select>
                    <b id="errorS1" style="color:red;"></b>
                    </div>
                  </div> 
              </form>    
        </div>
        <div class="modal-footer"> 
        	<button type="submit" class="btn btn-default btn btn-success btn-md" form="act" >Actualizar Informacion</button>
        	<button type="button" class="btn btn-primary btn-md" data-dismiss="modal">Cancelar</button> 
        </div>
      </div>
    </div>
  </div>