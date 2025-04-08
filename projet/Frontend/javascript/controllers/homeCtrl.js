$(document).ready(function () {
    // Redirige si l'utilisateur n'est pas connecté
    if (!sessionStorage.getItem("loggedUser")) {
      window.location.href = "login.html";
      return;
    }
  
    // Affiche le nom d'utilisateur connecté
    $("#logged-username").text(sessionStorage.getItem("loggedUser"));
  });