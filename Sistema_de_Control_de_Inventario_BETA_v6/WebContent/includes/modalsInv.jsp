<%@page import="beans.PersonaBean"%>
<%@page session="true" %>
	<%PersonaBean persona = null;
	HttpSession sesionOk=request.getSession();
	if(sesionOk.getAttribute("misesion")==null){%>
		<jsp:forward page="index.jsp">
			<jsp:param name="error" value="Debe iniciar Sesion"/>
		</jsp:forward>
	<%}else{
	
		persona=(PersonaBean)session.getAttribute("misesion");
		
	}%>
	
<script type="text/javascript">
//CALCULA AGREGADO
function calcularA(cantidad,retirada,inputtext,boton){	
		// Calculo del subtotal		
		var subtotal = parseInt(cantidad)+parseInt(retirada);
			
			
		if(retirada<=0 ||isNaN(retirada)||isNaN(subtotal)){
			inputtext.value="Ingrese una cantidad valida";
			boton.disabled=true;
			$('body').removeClass('glyphicon glyphicon-ok');
			$('body').removeClass('verde');
			$('#spanS1').addClass('glyphicon glyphicon-remove');
			$('#spanS1').addClass('rojo');
				
		}else{
			inputtext.value=parseInt(subtotal);
			boton.disabled=false;
			$('#spanS1').removeClass('glyphicon glyphicon-remove');
			$('#spanS1').removeClass('rojo');
			$('#spanS1').addClass('glyphicon glyphicon-ok');
			$('#spanS1').addClass('verde');
				
		}
}

</script>	
<!-- MODAL RETIRAR  -->
<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog  modal-lg" id="modal-dial">
      <div class="modal-content">
        <div class="modal-header" style="background-color:#8A0829;">
          <button type="button" class="close" data-dismiss="modal" style="color:white;">&times;</button>
          <h3 class="modal-title" style="color:#FFFFFF;"><b>Retirar</b></h3>
        </div>
        <div class="modal-body" style="height: 510px;">
        	
        	<div class="form-group col-md-12 col-xs-12">
        		<label id="label"  >Nombre del Articulo </label>
        		<input id="nombreArt" class="form-control" type="text" disabled>
        	</div>
        	
        	<div class="form-group col-md-6 col-xs-6">
        		<label id="label2" for="cantidadArt">Cantidad Actual</label>
        		<input id="cantidadArt" class="form-control" type="text" disabled form="ret">
        	</div>
        	<div class="form-group col-md-6 col-xs-6">
        		<label id="label1" for="medida">Medida</label>
        		<input id="medida" class="form-control" type="text" disabled >
        	</div>
        	
        	<div class="form-group col-md-11 col-xs-11">
        		<label id="label3" >Cantidad a Retirar</label>
        		<input id="retirada" class="form-control" type="text" min="0" name="retirada" form="ret" maxlength="3" onkeyup="calcular(parseInt(cantidadArt.value),parseInt(this.value),restante,btnRetirar)" onchange="calcular(parseInt(cantidadArt.value),parseInt(this.value),restante,btnRetirar)" onkeypress="return validar(event)" required>
        	</div>
        	<div class=" form-group col-md-1 col-xs-1">
        	     <span id="spanS" style="text-align:center;"></span>
        	</div>
        	<div class="form-group col-md-12 col-xs-12" >
        		<label id="total">Cantidad Restante</label>
        		<input class="form-control" type="text" id="restante"  disabled>
        	</div>
        	 <div class="form-group col-md-12 col-xs-12" >
  				<label for="comment">Comentario:</label>
  				<textarea class="form-control" rows="5" name ="comentario" cols="12" id="comment" form="ret" placeholder=""  style="resize:vertical; max-height:150px; min-height:150px;"></textarea>
			</div>
			
        </div>
        <div class="modal-footer">
          <form method="post" action="Articulo" id="ret" oninput="x.value=parseInt(cantidadArt.value)+parseInt(retirada.value)">
          	<input type="hidden" name="codigo" id="codigo" />
          	<input type="hidden" name="ph" value="ifsRa">
          	<input type="hidden" name="codigoP" value="<%=persona.getId() %>">
          	<button type="submit" class="btn btn-default btn btn-success btn-md" id="btnRetirar"  >Retirar Cantidad</button>
          	<button type="button" class="btn btn-primary btn btn-md" data-dismiss="modal">Cancelar</button>
          </form>
        </div>
      </div>
   </div>
</div>

<!-- MODAL AGREGAR ARTICULO -->
<div class="modal fade" id="mod3" role="dialog">
    <div class="modal-dialog" id="modal-dial3">
      <div class="modal-content">
        <div class="modal-header" style="background-color:#8A0829;">
          <button type="button" class="close" data-dismiss="modal" style="color:white;">&times;</button>
          <h3 class="modal-title" style="color:white;" ><span class="glyphicon glyphicon-inbox" style="width: 31px; height: 29px; "></span><strong> Nuevo Articulo</strong></h3>
        </div>
        <div class="modal-body">
         <form class="form-horizontal" method="post" action="Articulo" id="formAgr" onsubmit="return validar()">
         			<input type="hidden" name="ph" value="ifsAa">
                   <div class="form-group">
                    <div class="col-xs-10 col-md-10">
                        <input required type="text" class="form-control" id="nombre" placeholder="Nombre del Articulo" name="nombre" required/>
                    </div>
                  </div>
                  <div class="form-group">
                  	<div class="col-xs-10 col-md-10">
                    <select  name="medida" class="form-control" id="sel1" required>
                    	<option selected value="">Seleccione Medida</option>
                    	<option value="UNIDAD" >UNIDAD</option>
                    	<option value="UNIDAD" >UNIDAD X 10</option>
                    	<option value="PAR">PAR</option>
                    	<option value="PAQUETE X 50">PAQUETE X 50</option>
                    	<option value="PAQUETE X 500">PAQUETE X 100</option>
                    	<option value="PAQUETE X 500">PAQUETE X 200</option>
                    	<option value="PAQUETE X 500">PAQUETE X 500</option>
                    	<option value="PAQUETE X 500">PAQUETE X 5000</option>
                    	<option value="CAJA X 1000">CAJA X 1000</option>
                    	<option value="ESTUCHE X 10">ESTUCHE x 10</option>
                    </select>
                    </div>
                  </div>
                   <div class="form-group">
                    <div class="col-xs-10 col-md-10">
                        <input required type="text" class="form-control" id="cantidad" placeholder="Cantidad" maxlength="3" name="cantidad" max="999" required onkeypress="return validar(event)"/>
                    </div>
                  </div>
                  
                  <br/>
              </form>  
              <div class="modal-footer">
	               <button type="submit" class="btn btn-default btn btn-success btn-md" form="formAgr" >Agregar Articulo</button>
	         	   <button type="button" class="btn btn-primary btn btn-md" data-dismiss="modal">Cancelar</button>  
         	   </div>
        </div>
        
      </div>
    </div>
  </div>
<!-- MODAL MODIFICAR ARTICULO -->
<div class="modal fade" id="mod2" role="dialog">
	 <div class="modal-dialog" id="modal-dial3">
      <div class="modal-content">
        <div class="modal-header" style="background-color:#8A0829;">
          <button type="button" class="close" data-dismiss="modal" style="color:white;">&times;</button>
          <h3 class="modal-title" style="color:white;" ><span class="glyphicon glyphicon-inbox" style="width: 31px; height: 29px; "></span><strong> Agregar a Stock</strong></h3>
        </div>
        <div class="modal-body" style="height: 320px;">
         <form class="form-horizontal" role="form" method="post" action="Articulo" id="formMod" onsubmit="return validar()">
         		   <input type="hidden" name="ph" value="ifsMa">
                   <div class="form-group">
                    <div class="col-xs-10 col-md-10">
                        <input required type="text" class="form-control" id="nombre" placeholder="Nombre del Articulo" name="nombre" disabled/>
                    </div>
                  </div>
                  <div class="form-group">
                  	<div class="col-xs-10 col-md-10">
                    <select  name="medida" class="form-control" id="sel1"  disabled>
                    	<option selected value="">Seleccione Medida</option>
                    	<option value="UNIDAD" >UNIDAD</option>
                    	<option value="UNIDAD" >UNIDAD X 10</option>
                    	<option value="PAR">PAR</option>
                    	<option value="PAQUETE X 50">PAQUETE X 50</option>
                    	<option value="PAQUETE X 500">PAQUETE X 100</option>
                    	<option value="PAQUETE X 500">PAQUETE X 200</option>
                    	<option value="PAQUETE X 500">PAQUETE X 500</option>
                    	<option value="PAQUETE X 500">PAQUETE X 5000</option>
                    	<option value="CAJA X 1000">CAJA X 1000</option>
                    	<option value="ESTUCHE X 10">ESTUCHE x 10</option>
                    </select>
                    </div>
                  </div>
                   <div class="form-group">
                    <div class="col-xs-10 col-md-10">
                    	<input type="hidden" name="codigo4" id="codigo4">
                        <input type="text" class="form-control" id="cantidadAct"  form="formMod" disabled/>
                    </div>
                  </div>
                  <div class="form-group col-md-11 col-xs-11">
	        		  <label id="label3" >Cantidad a agregar a stock</label>
	        		  <input id="retirada" class="form-control" type="text" min="0" name="c1" form="formMod" maxlength="3" onkeyup="calcularA(parseInt(cantidadAct.value),parseInt(this.value),restante1,btnAact);"  onkeypress="return validar(event)" required>
        		  </div>
	        		  <div class=" form-group col-md-1 col-xs-1">
	        	      <span id="spanS1" style="text-align:center;margin-top:29px;margin-left:5px"></span>
        		  </div>
        		  <div class="form-group col-md-11 col-xs-11" >
	        		  <label id="total">Cantidad actual</label>
	        		  <input class="form-control" type="text" id="restante1"  disabled>
        		  </div>
                  <br/> 
              </form>    
        </div>
        <div class="modal-footer">
         <button type="submit" class="btn btn-default btn btn-success btn-md" form="formMod" id="btnAact">Actualizar Informacion</button>
         <button type="button" class="btn btn-primary btn btn-md" data-dismiss="modal">Cancelar</button>
        </div>
      </div>
    </div>
</div>

<!-- MODAL DESHABILITAR  -->
<div class="modal fade" id="modDA" role="dialog">
    <div class="modal-dialog modal-sm" id="modal-dial">
      <div class="modal-content">
        <div class="modal-header" style="background-color:#8A0829;">
          <button type="button" class="close" data-dismiss="modal" style="color:white;">&times;</button>
          <h3 class="modal-title"><strong style="color:white;">Deshabilitar Articulo</strong></h3>
        </div>
        <div class="modal-body">
        
          <p>¿Esta seguro que desea deshabilitar el articulo seleccionado?</p>
        	
        </div>
        <div class="modal-footer">
          <form method="post" action="Articulo">
          <input type="hidden" name="ph" value="ifsDa">
          <input type="hidden" name="codigo" id="codigo" />
          <button type="submit" class="btn btn-default">Deshabilitar</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
          </form>
        </div>
      </div>
    </div>
  </div>
  
 <!-- MODAL HABILITAR  -->
<div class="modal fade" id="modHA" role="dialog">
    <div class="modal-dialog modal-sm" id="modal-dial">
      <div class="modal-content">
        <div class="modal-header" style="background-color:#8A0829;">
          <button type="button" class="close" data-dismiss="modal" style="color:white;">&times;</button>
          <h3 class="modal-title"><strong style="color:white;">Habilitar Articulo</strong></h3>
        </div>
        <div class="modal-body">
        
          <p>¿Esta seguro que desea habilitar el articulo seleccionado?</p>
        	
        </div>
        <div class="modal-footer">
          <form method="post" action="Articulo">
          <input type="hidden" name="ph" value="ifsHa">
          <input type="hidden" name="codigo" id="codigo2" />
          <button type="submit" class="btn btn-default">Habilitar</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
          </form>
        </div>
      </div>
   </div>
</div>
  

