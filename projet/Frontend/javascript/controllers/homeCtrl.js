$(document).ready(function () {
  if (!sessionStorage.getItem("loggedUser")) {
    window.location.href = "login.html";
    return;
  }

  let allPosts = [];

  // Charger tous les posts
  getAllPosts(
    function (posts) {
      console.log('Posts chargés:', posts);  // Log de tous les posts
      allPosts = posts;

      // Directement utiliser les posts sans récupérer le nom de l'utilisateur
      displayPosts(allPosts);
    },
    function () {
      alert("Erreur lors du chargement des posts.");
    }
  );

  $("#logged-username").text(sessionStorage.getItem("loggedUser"));

  $("#searchInput").on("input", function () {
    const searchTerm = $(this).val().toLowerCase();
    const filteredPosts = allPosts.filter(post =>
      (post.title || "").toLowerCase().includes(searchTerm)
    );
    displayPosts(filteredPosts);
  });

  $("#resetFilter").on("click", function () {
    displayPosts(allPosts);
  });

  $(".sidebar ul li").on("click", function () {
    const selectedCategory = $(this).text().trim().toLowerCase();
    const filteredPosts = allPosts.filter(post =>
      (post.category || "").toLowerCase() === selectedCategory
    );
    displayPosts(filteredPosts);
  });

  // Affiche les posts sans nom d’auteur
  function displayPosts(posts) {
    const $postList = $(".post-list");
    $postList.empty();

    if (posts.length === 0) {
      $postList.append("<p>Aucun post trouvé pour cette catégorie.</p>");
      return;
    }

    posts.forEach(function (post) {
      const postHtml = $(`
        <a href="discussion.html" class="post ${post.couleur}">
          <div class="thumbnail">
            <img src="${post.imageUrl}" alt="Image" style="width: 100px; height: 80px; object-fit: cover;">
          </div>
          <div class="details">
            <h3>${post.title}</h3>
            <p>${post.description}</p>
            <span class="category-tag">${post.category}</span>
          </div>
        </a>
      `);

      $postList.append(postHtml);
    });
  }
});
