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
						
						
						
						
						
						var dni = response['object']['dni'];
						var nombre = response['object']['nombre'];
						var apellido = response['object']['apellido'];
						var residencia = response['object']['residencia'];
						var perfil = response['object']['perfil'];
						var correo = response['object']['correo'];
						var sueldo = response['object']['sueldo'];
						var sexo = response ['object']['sexo'];
						var segMed = response ['object']['segMed'];
						var segVid = response ['object']['segVid'];
						var cel =response['object']['contactos'][0]['telefono'];
						var fijo=response['object']['contactos'][1]['telefono'];
						
						/*
						for(var i=0;i<response['object']['contactos'][i]['telefono'].length;i++){
							
							if(response['object']['contactos'][i]['telefono'].length=9){
								cel = response['object']['contactos'][i]['telefono'];
							}
							
							else{
								fijo = response['object']['contactos'][i]['telefono'];
							}
							
						}
						
						*/
						
						if(sexo=="M"){
							$("input:radio[name=sexoAct]")[0].checked = true;
							
						}
						if(sexo=="F"){
							
							$("input:radio[name=sexoAct]")[1].checked = true;
						}
						
						
						$("#dniAct").val(dni);
						$("#errorNombre2").val(nombre);
						$("#errorApellido2").val(apellido);
						$("#perfilAct").val(perfil);
						$("#comAct").val();
						$("#errorDireccion2").val(residencia);
						$("#contactoAct1").val(cel);
						$("#contactoAct2").val(tel);
						$("#emailAct").val(correo);
						$("#sueldoAct").val(sueldo);
						$("#medicoAct").val(segMed);
						$("#seguroAct").val(segVid);
						
						
					});
			  }