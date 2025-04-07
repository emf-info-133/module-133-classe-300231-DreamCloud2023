$(document).ready(function () {
    // Redirige si l'utilisateur n'est pas connecté
    if (!sessionStorage.getItem("loggedUser")) {
      window.location.href = "login.html";
      return;
    }
  
    // Affiche le nom d'utilisateur connecté
    $("#logged-username").text(sessionStorage.getItem("loggedUser"));
  
    // Gestion du logout
    $(document).on("click", "#logoutButton", function () {
      logout(logoutSuccess, logoutError);
    });
  
    function logoutSuccess(response) {
      sessionStorage.clear();
      alert("Vous êtes déconnecté !");
      window.location.href = "login.html";
    }
  
    function logoutError(error) {
      console.error("Erreur lors de la déconnexion : ", error);
      alert("Une erreur s'est produite lors de la déconnexion.");
    }
  });
  