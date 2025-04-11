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
  
    async function displayPosts(posts) {
      const $postList = $(".post-list");
      $postList.empty();
  
      if (posts.length === 0) {
          $postList.append("<p>Aucun post trouvé pour cette catégorie.</p>");
          return;
      }
  
      for (let post of posts) {
          // On suppose que le nom du créateur est maintenant dans post.creatorName
          const username = post.creatorUsername || "Auteur inconnu"; // Utiliser "Auteur inconnu" si le nom est manquant
  
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
  
    $(document).on("click", ".post", function (e) {
      e.preventDefault(); // empêche le saut immédiat
      const postId = $(this).data("id");
      localStorage.setItem("selectedPostId", String(postId));
      window.location.href = "discussion.html"; // on navigue seulement après avoir stocké
    });
  });
  