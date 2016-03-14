//Lanza un popup para que el usuario confirme sus acciones o las cancele
function confirmBox(mensaje) {
    var answer;
    answer = window.confirm(mensaje);
    if (answer === true) {
        return true;
    }
    else {
        return false;
    }
}