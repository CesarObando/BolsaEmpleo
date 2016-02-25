function cambiarCanton() {
    var cantones_Alajuela = new Array("", "Alajuela", "Atenas", "San Ramón", "Grecia", "Guatuso", "Los Chiles", "Naranjo", "Orotina", "Palmares", "Poás", "San Carlos", "San Mateo", "Upala", "Valverde Vega", "Zarcero");
    var cantones_Cartago = new Array("", "Alvarado", "Cartago", "El Guarco", "Jiménez", "La Unión", "Oreamuno", "Paraíso", "Turrialba");
    var cantones_Guanacaste = new Array("", "Abangares", "Bagaces", "Carrillo", "Cañas", "Hojancha", "La Cruz", "Liberia", "Nandayure", "Nicoya", "Santa Cruz", "Tilarán");
    var cantones_Heredia = new Array("", "Barva", "Belén", "Flores", "Heredia", "San Isidro", "San Pablo", "San Rafael", "Santa Bárbara", "Santo Domingo", "Sarapiquí");
    var cantones_Limón = new Array("", "Guácimo", "Limón", "Matina", "Pococí", "Siquirres", "Talamanca");
    var cantones_Puntarenas = new Array("", "Buenos Aires", "Corredores", "Coto Brus", "Esparza", "Garabito", "Golfito", "Montes de Oro", "Osa", "Parrita", "Puntarenas", "Quepos");
    var cantones_SanJose = new Array("", "Acosta", "Alajuelita", "Aserrí", "Curridabat", "Desamparados", "Dota", "Escazú", "Goicoechea", "León Cortés", "Montes de Oca", "Mora", "Moravia", "Pérez Zeledón", "Puriscal", "San José", "Santa Ana", "Tarrazú", "Tibás", "Turrubares", "Vázquez de Coronado");
    //tomo el valor del select de la provincia elegido 
    var provincia;
    provincia = document.f1.provincia[document.f1.provincia.selectedIndex].value;
    //valida si la provincia está elegida
    if (provincia !== "") {
        //si estaba definida, entonces coloco las opciones de los cantones correspondientes. 
        //Se hace esta validacion por el espacio en el nombre
        if (provincia === "San José") {
            //selecciono el array de cantones adecuado
            mis_cantones = cantones_SanJose;
        }
        else {
            //selecciono el array de cantones adecuado
            mis_cantones = eval("cantones_" + provincia);
        }
        //calculo el numero de cantones 
        num_cantones = mis_cantones.length;
        //marco el número de cantones en el select 
        document.f1.canton.length = num_cantones;
        //para cada canton del array, lo introduzco en el select 
        for (i = 0; i < num_cantones; i++) {
            //Valida si es el primer registro del array
            if (mis_cantones[i].toString() === "") {
                //Coloco la primera opcion del select
                document.f1.canton.options[i].value = "";
                document.f1.canton.options[i].text = "Selecciona un cantón";
            }
            else {
                //Coloco el resto de opciones
                document.f1.canton.options[i].value = mis_cantones[i];
                document.f1.canton.options[i].text = mis_cantones[i];
            }
        }
    } else {
        //si no había provincia seleccionada, elimino los cantones del select 
        document.f1.canton.length = 1;
        //coloco la informacion que va a tener el select cuando esta vacio 
        document.f1.canton.options[0].value = "";
        document.f1.canton.options[0].text = "Selecciona un cantón";
    }
    //marco como seleccionada la opción primera del canton 
        document.f1.canton.options[0].selected = true;
}

function editarCanton($canton, $provincia) {
    var cantones_Alajuela = new Array("", "Alajuela", "Atenas", "San Ramón", "Grecia", "Guatuso", "Los Chiles", "Naranjo", "Orotina", "Palmares", "Poás", "San Carlos", "San Mateo", "Upala", "Valverde Vega", "Zarcero");
    var cantones_Cartago = new Array("", "Alvarado", "Cartago", "El Guarco", "Jiménez", "La Unión", "Oreamuno", "Paraíso", "Turrialba");
    var cantones_Guanacaste = new Array("", "Abangares", "Bagaces", "Carrillo", "Cañas", "Hojancha", "La Cruz", "Liberia", "Nandayure", "Nicoya", "Santa Cruz", "Tilarán");
    var cantones_Heredia = new Array("", "Barva", "Belén", "Flores", "Heredia", "San Isidro", "San Pablo", "San Rafael", "Santa Bárbara", "Santo Domingo", "Sarapiquí");
    var cantones_Limón = new Array("", "Guácimo", "Limón", "Matina", "Pococí", "Siquirres", "Talamanca");
    var cantones_Puntarenas = new Array("", "Buenos Aires", "Corredores", "Coto Brus", "Esparza", "Garabito", "Golfito", "Montes de Oro", "Osa", "Parrita", "Puntarenas", "Quepos");
    var cantones_SanJose = new Array("", "Acosta", "Alajuelita", "Aserrí", "Curridabat", "Desamparados", "Dota", "Escazú", "Goicoechea", "León Cortés", "Montes de Oca", "Mora", "Moravia", "Pérez Zeledón", "Puriscal", "San José", "Santa Ana", "Tarrazú", "Tibás", "Turrubares", "Vázquez de Coronado");
    //miro a ver si la provincia está seleccionada
    if ($provincia !== "") {
        //si estaba definida, entonces coloco las opciones de los cantones correspondientes. 
        //Se hace esta validacion por el espacio en el nombre
        if ($provincia === "San José") {
            //selecciono el array de cantones adecuado
            mis_cantones = cantones_SanJose;
        }
        else {
            //selecciono el array de cantones adecuado
            mis_cantones = eval("cantones_" + $provincia);
        }
        //calculo el numero de cantones 
        num_cantones = mis_cantones.length;
        //marco el número de cantones en el select 
        document.f1.canton.length = num_cantones;
        //para cada canton del array, lo introduzco en el select 
        for (i = 0; i < num_cantones; i++) {
            //Valida si es el primer registro del array
            if (mis_cantones[i].toString() === "") {
                //Coloco la primera opcion del select
                document.f1.canton.options[i].value = "";
                document.f1.canton.options[i].text = "Selecciona un cantón";
            }
            else {
                //Coloco el resto de opciones
                document.f1.canton.options[i].value = mis_cantones[i];
                document.f1.canton.options[i].text = mis_cantones[i];
            }
        }
        //Si el canton esta seleccionado
        if ($canton !== null) {
            //coloco el canton seleccionado como el que se va a mostrar
            document.f1.canton[document.f1.canton.selectedIndex].value = $canton;
        }
    } else {
        //si no había provincia seleccionada, elimino los cantones del select
        document.f1.canton.length = 1;
        //coloco la informacion que va a tener el select cuando esta vacio 
        document.f1.canton.options[0].value = "";
        document.f1.canton.options[0].text = "Selecciona un cantón";
    }
    //marco como seleccionada la opción primera del canton 
    document.f1.canton[document.f1.canton.selectedIndex].value = $canton;
    document.f1.canton[document.f1.canton.selectedIndex].text = $canton;
}


