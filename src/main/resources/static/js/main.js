/**
 *
 */
function showhideArch() {
    var x = document.getElementById("activos");
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}

function showhideAct() {
    var x = document.getElementById("archivados");
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}
