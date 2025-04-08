$(document).ready(function () {
  let allPosts = [];

  if (!sessionStorage.getItem("loggedUser")) {
    window.location.href = "login.html";
    return;
  }

  $("#logged-username").text(sessionStorage.getItem("loggedUser"));

  // Charger tous les posts à l'initialisation
  getAllPosts(function (posts) {
    allPosts = posts;
    displayPosts(posts);
  }, function () {
    alert("Erreur lors du chargement des posts.");
  });

  // Bouton Réinitialiser - Affiche tous les posts
  $("#resetFilter").on("click", function () {
    displayPosts(allPosts);
  });

  // Filtrage par catégorie
  $(".sidebar ul li").on("click", function () {
    const selectedCategory = $(this).text().trim().toLowerCase();
    const filteredPosts = allPosts.filter(p => p.category.toLowerCase() === selectedCategory);
    displayPosts(filteredPosts);
  });

  // Fonction pour afficher les posts
  function displayPosts(posts) {
    $(".post-list").empty();

    if (posts.length === 0) {
      $(".post-list").append("<p>Aucun post trouvé pour cette catégorie.</p>");
      return;
    }

    posts.forEach(function (post) {
      const postHtml = `
        <a href="discussion.html" class="post ${post.couleur}">
          <div class="thumbnail">
            <img src="${post.imageUrl}" alt="Image" style="width: 100px; height: 80px; object-fit: cover;">
          </div>
          <div class="details">
            <h3>${post.title}</h3>
            <p>${post.description}</p>
            <span class="category-tag">${post.category}</span>
          </div>
          <div class="author">By ${post.username || post.creatorId}</div>
        </a>`;
      $(".post-list").append(postHtml);
    });
  }
});
