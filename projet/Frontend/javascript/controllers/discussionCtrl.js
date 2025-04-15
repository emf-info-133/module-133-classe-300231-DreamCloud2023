$(document).ready(function () {
  // Chemin de l'image par défaut
  const defaultImage = "/projet/Frontend/assets/pas-de-photo.png";
  
  // Récupération des données du post et de l'utilisateur connecté
  const post = JSON.parse(sessionStorage.getItem("Post"));
  const loggedUser  = sessionStorage.getItem("loggedUser");

  // Vérifications de sécurité et redirections
  if (!loggedUser ) {
    alert("Veuillez vous connecter.");
    window.location.href = "login.html";
    return;
  }

  if (!post) {
    alert("Aucun post sélectionné.");
    window.location.href = "home.html";
    return;
  }

  // Fonction pour afficher les informations du post
  function displayPost(post) {
    // Ajout de la couleur du post ou d'une couleur par défaut
    $(".main-post").addClass(post.couleur || "default");

    // Détermination de l'URL de l'image à afficher
    const imageUrl = post.imageUrl && post.imageUrl.trim() !== "" ? post.imageUrl : defaultImage;
    $(".post-image").attr("src", imageUrl);

    // Affichage des informations du post
    $(".post-title").text(post.title);
    $(".post-description").text(post.description);
    $(".category-tag").text(post.category);
    $(".post-author").text("By " + (post.creatorUsername || post.creatorId || "Auteur inconnu"));
  }

  // Fonction pour afficher les commentaires
  function displayMessages(messages) {
    const $container = $(".comments");
    $container.empty(); // Vider le conteneur avant d'ajouter les nouveaux messages

    // Vérification s'il y a des messages à afficher
    if (!messages.length) {
      $container.append("<p>Aucun message pour ce post.</p>");
      return;
    }

    // Affichage des messages, le dernier message en haut
    messages.forEach(msg => {
      const comment = `
        <div class="comment">
          <span class="username">${msg.creatorUsername || msg.creatorId} :</span>
          <p>${msg.text}</p>
        </div>`;
      $container.append(comment);
    });
  }

  // Afficher le post sélectionné
  displayPost(post);

  // Charger les messages du post
  getMessagesByPost(post.postId, displayMessages);

  // Gestion de l'envoi de message
  $(".reply-form").on("submit", function (e) {
    e.preventDefault(); // Empêcher le rechargement de la page
    const text = $("textarea").val().trim(); // Récupérer le texte du message

    // Vérifier si le champ de texte n'est pas vide
    if (!text) return;

    // Récupérer les informations de l'utilisateur connecté
    getUserByUsername(loggedUser , function (user) {
      const message = {
        text: text,
        creatorId: user.id,
        postId: post.postId
      };

      // Ajouter le message
      addMsg(
        message,
        function () {
          $("textarea").val(""); // Vider le champ de texte après l'envoi
          getMessagesByPost(post.postId, displayMessages); // Recharger les messages
        },
        function () {
          alert("Erreur lors de l'envoi du message."); // Gérer l'erreur d'envoi
        }
      );
    });
  });
});