  var fechas=[];
			  function cargar(){
					var dniJ = $('#txtDNI').val();	
					var accion=$('#accion').val();
					var vacio = "";
					$.post('Justificacion',{	
						dniJ : dniJ,
						accion : accion
					},function(response){
						
						
						if(response['object']==null){
							
							$('#dni').text(vacio);
							$('#dni_t').val(vacio);
							$('#nombre').text(vacio);
							$('#apellidos').text(vacio);
							$('#residencia').text(vacio);
							$('#fechas').html(vacio);
							$('#formulario').hide();
							alert("Empleado no encontrado");
						}
						
						var dni=response['object']['dni'];
						var nombre=response['object']['nombre'];
						var apellido=response['object']['apellido'];
						var residencia=response['object']['residencia'];
						var conta="";
						fechas=new Array(response['object']['asis'].length);
						for(var i=0;i<response['object']['asis'].length;i++){
							fechas[i]=response['object']['asis'][i]['id'];
							conta=conta+("<option value='"+response['object']['asis'][i]['id']+"'>"+response['object']['asis'][i]['horaEnt']+"</option>");
						}
						
						

						$('#dni').text(dni);
						$('#dni_t').val(dni);
						$('#nombre').text(nombre);
						$('#apellidos').text(apellido);
						$('#residencia').text(residencia);
						$('#fechas').html(conta);
						
						if(fechas[0]==null){

							//window.location.href ="justificacion.jsp";
							alert("Error: Faltas injustificadas no encontradas");
							
							$('#formulario').hide();
						}else{
							$('#formulario').show();
						}

				});
			}
			  
			  
			  
			  
			  //ENCONTRAR TRABAJADOR Y JALÑAR DATOS PARA EL MODIFICAR
			  function obtenerDatos(dni){
						
					var accion="obtenerDatos";
					$.post('Empleado',{	
						dniObt : dni,
						accion : accion
					},function(response){
						

					});
			  }