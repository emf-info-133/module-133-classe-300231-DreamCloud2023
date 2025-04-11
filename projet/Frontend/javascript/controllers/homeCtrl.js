$(document).ready(function () {
  if (!sessionStorage.getItem("loggedUser")) {
    window.location.href = "login.html";
    return;
  }

  let allPosts = [];
  let userCache = {}; // Pour éviter les appels redondants à la BDD

  // Charger tous les posts
  getAllPosts(
    function (posts) {
      allPosts = posts;
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

  // Nouvelle fonction pour récupérer un nom via un ID
  function getUsernameById(userId, callback) {
    if (userCache[userId]) {
      callback(userCache[userId]);
    } else {
      $.get(`/api/users/${userId}`, function (user) {
        userCache[userId] = user.username;
        callback(user.username);
      }).fail(function () {
        callback("Inconnu");
      });
    }
  }

  // Affiche les posts avec les bons noms d’auteurs
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
          <div class="author">By <span class="author-name">Chargement...</span></div>
        </a>
      `);

      $postList.append(postHtml);

      const $authorName = postHtml.find(".author-name");

      if (post.username) {
        $authorName.text(post.username);
      } else {
        console.log(post.creatorId);
        getUsernameById(post.creatorId, function (name) {
          $authorName.text(name);
        });
      }
    });
  }
});
