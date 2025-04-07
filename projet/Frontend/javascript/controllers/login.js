$(document).ready(function () {
  $(document).on("click", "#btnLogin", function (event) {
    event.preventDefault();

    let username = $("#login-username").val().trim();
    let password = $("#login-password").val().trim();

    if (username === "" || password === "") {
      alert("Veuillez remplir tous les champs !");
    } else {
      const loginInfo = { username: username, password: password };

      // Appel à la méthode AJAX dans serviceHttp.js
      login(loginInfo, loginSuccess, loginError);
    }
  });

  // Callback en cas de succès
  function loginSuccess(response) {
    alert("Connexion réussie !");
    sessionStorage.setItem("loggedUser", $("#login-username").val().trim());
    window.location.href = "home.html";
  }

  // Callback en cas d'erreur
  function loginError(xhr) {
    if (xhr.status === 401) {
      alert("Nom d'utilisateur ou mot de passe incorrect.");
    } else {
      alert("Erreur serveur : " + xhr.responseText);
    }
  }

  // Redirection vers la page d'inscription
  $(document).on("click", "#registerButton", function () {
    window.location.href = "register.html";
  });
});
