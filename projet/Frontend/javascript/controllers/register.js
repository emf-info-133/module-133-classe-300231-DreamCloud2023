$(document).ready(function () {
    $(document).on("click", "#btnRegister", function (event) {
        event.preventDefault();

        let username = $("#register-username").val().trim();
        let password = $("#register-password").val().trim();
        let confirmPassword = $("#register-confirm-password").val().trim();

        console.log("Clic sur 'Register'");
        console.log("Nom d'utilisateur :", username);
        console.log("Mot de passe :", password);

        if (!username || !password || !confirmPassword) {
            alert("Veuillez remplir tous les champs !");
            return;
        }

        if (password !== confirmPassword) {
            alert("Les mots de passe ne correspondent pas !");
            return;
        }

        let user = {
            username: username,
            password: password
        };

        addUser(user, registerUserSuccess, registerUserError);
    });

    function registerUserSuccess(response) {
        alert("Inscription réussie !");
        window.location.href = "login.html"; // Redirection si souhaité
    }

    function registerUserError(xhr, status, error) {
        console.error("Erreur AJAX:", xhr.responseText);
        alert("Erreur lors de l'enregistrement de l'utilisateur : " + xhr.responseText);
    }
});
