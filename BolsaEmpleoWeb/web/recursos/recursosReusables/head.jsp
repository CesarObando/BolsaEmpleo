<script type="text/javascript" src="../js/cantones.js"></script>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="../imagenes/favicon.ico" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no" />
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Cache-Control" content="no-cache"/>
<meta http-equiv="Pragma" content="no-cache" />

<script type="text/javascript">

    Paginador = function (divPaginador, tabla, tamPagina)
    {
        this.miDiv = divPaginador; //un DIV donde irán controles de paginación
        this.tabla = tabla;           //la tabla a paginar
        this.tamPagina = tamPagina; //el tamaño de la página (filas por página)
        this.pagActual = 1;         //asumiendo que se parte en página 1
        this.paginas = Math.floor((this.tabla.rows.length - 1) / this.tamPagina); //¿?

        this.SetPagina = function (num)
        {
            if (num < 0 || num > this.paginas)
                return;

            this.pagActual = num;
            var min = 1 + (this.pagActual - 1) * this.tamPagina;
            var max = min + this.tamPagina - 1;

            for (var i = 1; i < this.tabla.rows.length; i++)
            {
                if (i < min || i > max)
                    this.tabla.rows[i].style.display = 'No hay registros para mostrar';
                else
                    this.tabla.rows[i].style.display = '';
            }
            this.miDiv.firstChild.rows[0].cells[1].innerHTML = this.pagActual;
        };

        this.Mostrar = function ()
        {
            //Crear la tabla
            var tblPaginador = document.createElement('table');

            //Agregar una fila a la tabla
            var fil = tblPaginador.insertRow(tblPaginador.rows.length);

            //Ahora, agregar las celdas que serán los controles
            var ant = fil.insertCell(fil.cells.length);
            ant.innerHTML = 'Anterior';
            ant.className = 'pag_btn'; //con eso le asigno un estilo
            var self = this;
            ant.onclick = function ()
            {
                if (self.pagActual == 1)
                    return;
                self.SetPagina(self.pagActual - 1);
            };

            var num = fil.insertCell(fil.cells.length);
            num.innerHTML = ''; //en rigor, aún no se el número de la página
            num.className = 'pag_num';

            var sig = fil.insertCell(fil.cells.length);
            sig.innerHTML = 'Siguiente';
            sig.className = 'pag_btn';
            sig.onclick = function ()
            {
                if (self.pagActual == self.paginas)
                    return;
                self.SetPagina(self.pagActual + 1);
            };

            //Como ya tengo mi tabla, puedo agregarla al DIV de los controles
            this.miDiv.appendChild(tblPaginador);

            //¿y esto por qué?
            if (this.tabla.rows.length - 1 > this.paginas * this.tamPagina)
                this.paginas = this.paginas + 1;

            this.SetPagina(this.pagActual);
        };
    };
</script>

<link rel="stylesheet" type="text/css" href="../css/Estilo.css">

<link rel="stylesheet" type="text/css" href="../css/system.base.css">
<link rel="stylesheet" type="text/css" href="../css/system.menus.css">
<link rel="stylesheet" type="text/css" href="../css/system.messages.css">
<link rel="stylesheet" type="text/css" href="../css/system.theme.css">

<!--menu-->
<link rel="stylesheet" type="text/css" href="../css/superfish.css" media="screen">       
<link rel="stylesheet" type="text/css" href="../css/superfish-smallscreen.css" media="screen">  

<link rel="stylesheet" type="text/css" href="../css/omega-text.css">
<link rel="stylesheet" type="text/css" href="../css/omega-branding.css">
<link rel="stylesheet" type="text/css" href="../css/omega-menu.css">
<link rel="stylesheet" type="text/css" href="../css/omega-forms.css">
<link rel="stylesheet" type="text/css" href="../css/global.css">
<!--responsive-->
<style type="text/css" media="all and (min-width: 670px) and (min-device-width: 670px), all and (max-device-width: 1024px) and (min-width: 1024px) and (orientation:landscape)">
    @import url("../css/omega-obas-alpha-default_1.css");            
    @import url("../css/alpha-default-normal-12.css");            
</style>