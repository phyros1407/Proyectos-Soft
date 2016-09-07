


<script>
function ver(){

	alert($('archivo').val());


}



</script>



<!-- MODAL MENSAJE -->
	
<div id="modEmail" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header" style="background-color:#8A0829;">
        <button type="button" class="close" data-dismiss="modal" style="color:white;">&times;</button>
        <h4 class="modal-title" style="color:white;"><span class=" glyphicon glyphicon-envelope" style="width: 31px; height: 29px; "></span><strong>Enviar Mensaje</strong></h4>
      </div>
      <div class="modal-body">
        <form action="Enviar" method="post" id="formM" class="form-horizontal" enctype="multiform/form-data">
        <div class="form-group">
        	<div class="col-xs-12">
        		<label for="receptor">Para:</label>
        		<input type="text" id="receptor" name="receptor" class="form-control"  aria-describedby="sizing-addon1" >
        	</div> 
        </div>		
		<!--  <div class="form-group">
			<div class="col-xs-12">
				<label for="receptor">Cc:</label>
				<input type="text" id="receptor" name="receptors" class="form-control"  aria-describedby="sizing-addon1" >	
			</div>
		</div>-->
		<div class="form-group">
			<div class="col-xs-12">
				<label for="asunto">Asunto:</label>
				<input type="text" id="asunto"  name="asunto"class="form-control"  aria-describedby="sizing-addon1" >
				<input type="file" multiple name="ruta" id="archivo" onchange="ver();">
				
			</div>
		</div>
		<br>
		<div class="form-group">
			<div class="col-xs-12">
				<textarea class="form-control" id="comment" name="mensajeP" rows="5"  cols="12"  style="resize:vertical; max-height:150px; min-height:150px;"></textarea>	
			</div>
		</div>
		</form>
      </div>
      <div class="modal-footer">
      <button id="boton" type="submit" name="button" form="formM" class="btn btn-default btn btn-success btn-md">Enviar Mensaje</button>
        <button type="button" class="btn btn-primary btn-md" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>
