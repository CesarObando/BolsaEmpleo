function cambiarCanton() {
    var cantones_Alajuela = new Array("", "Alajuela", "Atenas", "San Ramón", "Grecia", "Guatuso", "Los Chiles", "Naranjo", "Orotina", "Palmares", "Poás", "San Carlos", "San Mateo", "Upala", "Valverde Vega", "Zarcero");
    var cantones_Cartago = new Array("", "Alvarado", "Cartago", "El Guarco", "Jiménez", "La Unión", "Oreamuno", "Paraíso", "Turrialba");
    var cantones_Guanacaste = new Array("", "Abangares", "Bagaces", "Carrillo", "Cañas", "Hojancha", "La Cruz", "Liberia", "Nandayure", "Nicoya", "Santa Cruz", "Tilarán");
    var cantones_Heredia = new Array("", "Barva", "Belén", "Flores", "Heredia", "San Isidro", "San Pablo", "San Rafael", "Santa Bárbara", "Santo Domingo", "Sarapiquí");
    var cantones_Limón = new Array("", "Guácimo", "Limón", "Matina", "Pococí", "Siquirres", "Talamanca");
    var cantones_Puntarenas = new Array("", "Buenos Aires", "Corredores", "Coto Brus", "Esparza", "Garabito", "Golfito", "Montes de Oro", "Osa", "Parrita", "Puntarenas", "Quepos");
    var cantones_SanJose = new Array("", "Acosta", "Alajuelita", "Aserrí", "Curridabat", "Desamparados", "Dota", "Escazú", "Goicoechea", "León Cortés", "Montes de Oca", "Mora", "Moravia", "Pérez Zeledón", "Puriscal", "San José", "Santa Ana", "Tarrazú", "Tibás", "Turrubares", "Vázquez de Coronado");
    //tomo el valor del select del pais elegido 
    var provincia;
    provincia = document.f1.provincia[document.f1.provincia.selectedIndex].value;
    //miro a ver si el pais está definido 
    if (provincia !== "") {
        //si estaba definido, entonces coloco las opciones de la provincia correspondiente. 
        //selecciono el array de provincia adecuado 
        if (provincia === "San José") {
            mis_provincias = cantones_SanJose;
        }
        else {
            mis_provincias = eval("cantones_" + provincia);
        }
        //calculo el numero de provincias 
        num_provincias = mis_provincias.length;
        //marco el número de provincias en el select 
        document.f1.canton.length = num_provincias;
        //para cada provincia del array, la introduzco en el select 
        for (i = 0; i < num_provincias; i++) {
            if (mis_provincias[i].toString() === "") {
                document.f1.canton.options[i].value = "";
                document.f1.canton.options[i].text = "Selecciona un cantón";
            }
            else {
                document.f1.canton.options[i].value = mis_provincias[i];
                document.f1.canton.options[i].text = mis_provincias[i];
            }
        }
    } else {
        //si no había provincia seleccionada, elimino las provincias del select 
        document.f1.canton.length = 1;
        //coloco un guión en la única opción que he dejado 
        document.f1.canton.options[0].value = "";
        document.f1.canton.options[0].text = "Selecciona un cantón";
    }
    //marco como seleccionada la opción primera de provincia 
    document.f1.canton.options[0].selected = true;
}


