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
  function loginSuccess(user) {
    console.log("User reçu du backend :", user);

    sessionStorage.setItem("loggedUser", user.username);
    sessionStorage.setItem("isAdmin", user.admin);

    if (user.admin) {
      console.log(user.admin)
      window.location.href = "home.html";
    } else {
      console.log("wtf : "+ user.admin)
      window.location.href = "home.html";
    }

  }




  // Callback en cas d'erreur
  function loginError(xhr) {
    if (xhr.status === 401) {
      alert("Nom d'utilisateur ou mot de passe incorrect.");
    } else {
      alert("Erreur serveur : Username ou mot de passse incorrect");
    }
  }

  // Redirection vers la page d'inscription
  $(document).on("click", "#registerButton", function () {
    window.location.href = "register.html";
  });
});
