$(document).ready(function () {
  // Affiche le nom de l'utilisateur connecté
  $("#logged-username").text(sessionStorage.getItem("loggedUser"));

  // Lors du clic sur "Add Post"
  $(".btn:contains('Add Post')").on("click", function (event) {
    event.preventDefault();

    const username = sessionStorage.getItem("loggedUser");
    const title = $("#title").val().trim();
    const description = $("#desc").val().trim();
    const category = $("#category").val();
    const color = $("#color").val();
    const imageUrl = "https://kazanjiana.emf-informatique.ch/133-Ressource/image.jpg"; // Image fixe par défaut

    // Vérification des champs
    if (!username || !title || !description || !category || !color) {
      alert("Merci de remplir tous les champs !");
      return;
    }

    // Récupère l'utilisateur via son nom
    getUserByUsername(username, function (user) {
      const post = {
        creatorId: user.id, // ← utilise bien l'ID récupéré
        title: title,
        description: description,
        category: category,
        couleur: color,
        imageUrl: imageUrl
      };

      // Envoi vers la Gateway via port 8082
      addPost(post, function () {
        alert("Post créé avec succès !");
        window.location.href = "home.html";
      }, function (xhr) {
        alert("Erreur lors de la création du post : " + xhr.responseText);
      });

    }, function () {
      alert("Erreur lors de la récupération de l'utilisateur !");
    });
  });
});
