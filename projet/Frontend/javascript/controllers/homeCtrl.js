$(document).ready(function () {
  // Vérifier si l'utilisateur est connecté et récupérer ses données
  if (!sessionStorage.getItem("loggedUser")) {
      window.location.href = "login.html";
      return;
  }

  // Vérifier si l'utilisateur est admin et afficher ou non le bouton Ban
  const isAdmin = sessionStorage.getItem("isAdmin") === 'true';
  if (isAdmin) {
      $(".ban-button").show();  // Afficher le bouton "Ban Users"
  } else {
      $(".ban-button").hide();  // Cacher le bouton "Ban Users"
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

  // Afficher le nom de l'utilisateur connecté
  $("#logged-username").text(sessionStorage.getItem("loggedUser"));

  // Fonction pour afficher les posts
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

  // Fonction pour afficher les posts dans la section principale
  async function displayPosts(posts) {
      const $postList = $(".post-list");
      $postList.empty();

      if (posts.length === 0) {
          $postList.append("<p>Aucun post trouvé pour cette catégorie.</p>");
          return;
      }

      for (let post of posts) {
          const username = post.creatorUsername || "Auteur inconnu";  // Utiliser "Auteur inconnu" si le nom est manquant

          const postHtml = `
              <a href="#" class="post ${post.couleur}" data-id="${post.postId}">
              <div class="thumbnail">
                  <img src="${post.imageUrl}" alt="Image" style="width: 100px; height: 80px; object-fit: cover;">
              </div>
              <div class="details">
                  <h3>${post.title}</h3>
                  <p>${post.description}</p>
                  <span class="category-tag">${post.category}</span>
              </div>
              <div class="author">By ${username}</div>
              </a>`;

          $postList.append(postHtml);
      }
  }

  // Gérer la navigation vers la discussion d'un post
  $(document).on("click", ".post", function (e) {
      e.preventDefault(); // empêche le saut immédiat
      const postId = $(this).data("id");
      localStorage.setItem("selectedPostId", String(postId));
      window.location.href = "discussion.html"; // on navigue seulement après avoir stocké
  });
});
