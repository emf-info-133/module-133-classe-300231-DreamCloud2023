$(document).ready(function () {
  // Affiche le nom de l'utilisateur connecté
  $("#logged-username").text(sessionStorage.getItem("loggedUser"));

  // Affichage dynamique de l'image sélectionnée (prévisualisation)
  $("#image").on("change", function () {
    const file = this.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = function (e) {
        $("#image-preview").attr("src", e.target.result).show();
      };
      reader.readAsDataURL(file);
    }
  });

  // Lors du clic sur "Add Post"
  $(".btn:contains('Add Post')").on("click", function (event) {
    event.preventDefault();

    const username = sessionStorage.getItem("loggedUser");
    const title = $("#title").val().trim();
    const description = $("#desc").val().trim();
    const category = $("#category").val();
    const color = $("#color").val();
    const imageFile = $("#image")[0].files[0];

    // Vérification des champs obligatoires
    if (!username || !title || !description || !category || !color) {
      alert("Merci de remplir tous les champs obligatoires !");
      return;
    }

    const sendPost = (imageBase64) => {
      getUserByUsername(username, function (user) {
        const post = {
          creatorId: user.id,
          title: title,
          description: description,
          category: category,
          couleur: color,
          imageUrl: imageBase64 || "" // image optionnelle
        };

        addPost(post, function () {
          alert("Post créé avec succès !");
          window.location.href = "home.html";
        }, function (xhr) {
          alert("Erreur lors de la création du post");
        });

      }, function () {
        alert("Erreur lors de la récupération de l'utilisateur !");
      });
    };

    // Si une image est présente → lire le fichier
    if (imageFile) {
      const reader = new FileReader();
      reader.onload = function (e) {
        const imageBase64 = e.target.result;
        sendPost(imageBase64);
      };
      reader.readAsDataURL(imageFile);
    } else {
      // Pas d’image sélectionnée
      sendPost("");
    }
  });
});
