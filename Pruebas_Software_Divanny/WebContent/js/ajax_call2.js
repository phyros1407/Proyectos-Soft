function cargar2() {
	var dniJ = $('#txtDNI').val();
	var accion = $('#accion').val();
	var vacio = "";
	$.post('Seguro', {
		dniJ : dniJ,
		accion : accion
	}, function(response) {

		if (response['object'] == null) {

			$('#dni').text(vacio);
			$('#dni_t').val(vacio);
			$('#nombre').text(vacio);
			$('#apellidos').text(vacio);
			$('#seguro').text(vacio);
			$('#porcentaje').val(vacio)

			$('#formulario').hide();
			alert("Empleado no encontrado");
		}

		var dni = response['object']['dni'];
		var nombre = response['object']['nombre'];
		var apellido = response['object']['apellido'];
		var seguro = response['object']['NomSeguro'];
		var porcentaje = response['object']['segVid'];
		var conta = "";

		$('#dni').text(dni);
		$('#dni_t').val(dni);
		$('#nombre').text(nombre);
		$('#apellidos').text(apellido);
		$('#seguro').text(seguro);
		$('#porcentaje').prop("disabled",false);
		$('#porcentaje').val(porcentaje)
		$('#submit').css("visibility", "visible")
		console.log("muestra boton");
	});
}
