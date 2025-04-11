$(document).ready(function () {
  const postId = localStorage.getItem("selectedPostId");
  const defaultImage = "/projet/Frontend/assets/pas-de-photo.png"; // ← Image par défaut

  console.log("Post ID récupéré depuis localStorage :", postId);

  if (!sessionStorage.getItem("loggedUser")) {
    window.location.href = "login.html";
    return;
  }

  if (!postId) {
    alert("Aucun post sélectionné.");
    window.location.href = "home.html";
    return;
  }

  // Fonction pour afficher les infos du post
  function displayPost(post) {
    $(".main-post").addClass(post.couleur); // couleur du post
    const imageUrl = post.imageUrl && post.imageUrl.trim() !== "" ? post.imageUrl : defaultImage;

    $(".post-image").attr("src", imageUrl);
    $(".post-title").text(post.title);
    $(".post-description").text(post.description);
    $(".category-tag").text(post.category);
    $(".post-author").text("By " + (post.username || post.creatorId));
  }

  // Fonction pour afficher les messages
  function displayMessages(messages) {
    const $container = $(".comments");
    $container.empty();

    if (messages.length === 0) {
      $container.append("<p>Aucun message pour ce post.</p>");
      return;
    }

    // Afficher les derniers en haut
    messages.reverse().forEach(msg => {
      const comment = `
        <div class="comment">
          <span class="username">${msg.creatorUsername || msg.creatorId} :</span>
          <p>${msg.text}</p>
        </div>`;
      $container.append(comment);
    });
  }

  // Charger tous les posts et afficher le post sélectionné
  getAllPosts(function (posts) {
    const selectedPost = posts.find(p => p.postId == postId);
    if (!selectedPost) {
      alert("Post introuvable");
      return;
    }
    displayPost(selectedPost);
  });

  // Charger les messages du post
  getMessagesByPost(postId, displayMessages);

  // Envoi d'un message
  $(".reply-form").on("submit", function (e) {
    e.preventDefault();
    const text = $("textarea").val().trim();
    const username = sessionStorage.getItem("loggedUser");

    if (!text) return;

    getUserByUsername(username, function (user) {
      const message = {
        text: text,
        creatorId: user.id,
        postId: postId
      };

      addMsg(message, function () {
        $("textarea").val("");
        getMessagesByPost(postId, displayMessages);
      }, function () {
        alert("Erreur lors de l'envoi du message.");
      });
    });
  });
});
