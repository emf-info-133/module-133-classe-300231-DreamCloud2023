$(document).ready(function () {
  const defaultImage = "/projet/Frontend/assets/pas-de-photo.png";
  const post = JSON.parse(sessionStorage.getItem("Post"));
  const loggedUser = sessionStorage.getItem("loggedUser");

  // Vérifications de sécurité et redirections
  if (!loggedUser) {
    alert("Veuillez vous connecter.");
    window.location.href = "login.html";
    return;
  }

  if (!post) {
    alert("Aucun post sélectionné.");
    window.location.href = "home.html";
    return;
  }

  // Affichage des infos du post
  function displayPost(post) {
    $(".main-post").addClass(post.couleur || "default");

    // Appliquer l'image par défaut si aucune image n'est fournie
    const imageUrl = post.imageUrl && post.imageUrl.trim() !== "" ? post.imageUrl : defaultImage;
    $(".post-image").attr("src", imageUrl);

    $(".post-title").text(post.title);
    $(".post-description").text(post.description);
    $(".category-tag").text(post.category);
    $(".post-author").text("By " + (post.creatorUsername || post.creatorId || "Auteur inconnu"));
  }

  // Affichage des commentaires
  function displayMessages(messages) {
    const $container = $(".comments");
    $container.empty();

    if (!messages.length) {
      $container.append("<p>Aucun message pour ce post.</p>");
      return;
    }

    // Dernier message en haut
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
    e.preventDefault();
    const text = $("textarea").val().trim();

    if (!text) return;

    getUserByUsername(loggedUser, function (user) {
      const message = {
        text: text,
        creatorId: user.id,
        postId: post.postId
      };

      addMsg(
        message,
        function () {
          $("textarea").val(""); // Vider le champ
          getMessagesByPost(post.postId, displayMessages); // Recharger les messages
        },
        function () {
          alert("Erreur lors de l'envoi du message.");
        }
      );
    });
  });
});
