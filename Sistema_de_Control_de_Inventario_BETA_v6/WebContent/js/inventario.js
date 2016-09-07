//MANDA DATOS AL MODAL
		$(document).ready(function (e) {
	 		 $('#myModal').on('show.bs.modal', function(e) {    
	     		var id = $(e.relatedTarget).data().id1;
	     		var nom= $(e.relatedTarget).data().id2;
	     		var cant = $(e.relatedTarget).data().id3;
	     		var med= $(e.relatedTarget).data().id4;
	     
	     		$(e.currentTarget).find('#codigo').val(id);
	      		$(e.currentTarget).find('#nombreArt').val(nom);
	      		$(e.currentTarget).find('#cantidadArt').val(cant);
	      		$(e.currentTarget).find('#medida').val(med);
	  		});
		});
		
		//Pada datos al Modal --- mod2
		$(document).ready(function (e){
			$('#mod2').on('show.bs.modal',function (e){
				var id=$(e.relatedTarget).data().id;
				var name=$(e.relatedTarget).data().name;
				var cantidad=$(e.relatedTarget).data().can;
				var medida=$(e.relatedTarget).data().med;
				
				$(e.currentTarget).find('#codigo4').val(id);
				$(e.currentTarget).find('#nombre').val(name);		
				$(e.currentTarget).find('#cantidadAct').val(cantidad);
				$(e.currentTarget).find('#sel1').val(medida);
			})	
			
		})
		//Pada datos al Modal modDA
		$(document).ready(function (e){
			$('#modDA').on('show.bs.modal',function (e){
				var id=$(e.relatedTarget).data().id;
				$(e.currentTarget).find('#codigo').val(id);	
			})	
			
		})
		
		
		//Pada datos al Modal modHA
		$(document).ready(function (e){
			$('#modHA').on('show.bs.modal',function (e){
				var id=$(e.relatedTarget).data().id;
				$(e.currentTarget).find('#codigo2').val(id);	
			})	
			
		})
		
		//BUSCADOR
		$(document).ready(function () {
		    (function ($) {
		        $('#filter').keyup(function () {
		            var rex = new RegExp($(this).val(), 'i');
		            $('.searchable tr').hide();
		            $('.searchable tr').filter(function () {
		                return rex.test($(this).text());
		            }).show();
		        })
		    }(jQuery));
		});
		
	//CALCULA RESTANTE
	function calcular(cantidad,retirada,inputtext,boton){	
			// Calculo del subtotal		
			var subtotal = parseInt(cantidad)-parseInt(retirada);
				
			if(cantidad<retirada || retirada<=0 || subtotal<0||retirada==null||retirada==""||isNaN(retirada)){
				inputtext.value="Ingrese una cantidad valida";
				boton.disabled=true;
				$('body').removeClass('glyphicon glyphicon-ok');
				$('body').removeClass('verde');
				$('#spanS').addClass('glyphicon glyphicon-remove');
				$('#spanS').addClass('rojo');
					
			}else{
				inputtext.value=parseInt(subtotal);
				boton.disabled=false;
				$('#spanS').removeClass('glyphicon glyphicon-remove');
				$('#spanS').removeClass('rojo');
				$('#spanS').addClass('glyphicon glyphicon-ok');
				$('#spanS').addClass('verde');
					
			}
	}
	
	
	
	function validar(e) { 
		//VALIDA SOLO ENTRADA DE NUMEROS
	    tecla = (document.all) ? e.keyCode : e.which; 
	    if (tecla==8) return true; 
	   	patron = /\d/; 
	    te = String.fromCharCode(tecla); 
	    return patron.test(te); 
	}
	
	