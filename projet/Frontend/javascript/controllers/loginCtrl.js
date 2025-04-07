$(document).ready(function () {

    $('#loginButton').on('click', function () {
        const username = $('#username').val();
        const password = $('#password').val();

        if (!username || !password) {
            alert("Merci de remplir tous les champs.");
            return;
        }

        const userData = {
            username: username,
            password: password
        };

        login(userData, function (response) {
            console.log("Réponse reçue : ", response);
            var data = JSON.parse(response);
            if (data.success) {
                sessionStorage.setItem("logged", data.username);
                window.location.href = "home.html";
            } else {
                alert("Nom d'utilisateur ou mot de passe incorrect");
            }
        }, function () {
            alert("Erreur lors de la connexion à l'API");
        });
        
    });

    $('#registerButton').on('click', function () {
        window.location.href = "register.html";
        console.log("Test");
    });

});
