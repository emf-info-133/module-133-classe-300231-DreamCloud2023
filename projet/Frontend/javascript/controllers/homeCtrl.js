$(document).ready(function () {
    // Vérifier si l'utilisateur est connecté et récupérer ses données
    sessionStorage.removeItem("Post");
    if (!sessionStorage.getItem("loggedUser")) {
      window.location.href = "login.html";
      return;
    }
  
    const defaultImage = "/projet/Frontend/assets/pas-de-photo.png"; // ← Image par défaut
  
    // Vérifier si l'utilisateur est admin et afficher ou non le bouton Ban
    const isAdmin = sessionStorage.getItem("isAdmin") === 'true';
    if (isAdmin) {
      $(".ban-button").show();  // Afficher le bouton "Ban Users"
    } else {
      $(".ban-button").hide();  // Cacher le bouton "Ban Users"
    }
  
    let allUsers = [];  // Liste globale des utilisateurs
    let allPosts = [];  // Liste des posts
  
    // Charger tous les utilisateurs
    getAllUsers(
      function (users) {
        allUsers = users;
      },
      function () {
        alert("Erreur lors du chargement des utilisateurs.");
      }
    );
  
    // Charger tous les posts
    getAllPosts(
      function (posts) {
        console.log('Posts chargés:', posts);
        allPosts = posts;
        displayPosts(allPosts);
      },
      function () {
        alert("Erreur lors du chargement des posts.");
      }
    );
  
    $("#logged-username").text(sessionStorage.getItem("loggedUser"));
  
    // Recherche dynamique
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
        const username = post.creatorUsername || "Auteur inconnu";
        const imageUrl = post.imageUrl && post.imageUrl.trim() !== "" ? post.imageUrl : defaultImage;
  
        const postHtml = `
          <a href="#" class="post ${post.couleur}" data-id="${post.postId}">
            <div class="thumbnail">
              <img src="${imageUrl}" alt="Image" style="width: 100px; height: 80px; object-fit: cover;">
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
  
    // Stockage du post sélectionné et redirection
    $(document).on("click", ".post", function (e) {
      e.preventDefault();
      const postId = $(this).data("id");
      const currentPost = allPosts.find(p => p.postId === postId);
      if (currentPost) {
        sessionStorage.setItem("Post", JSON.stringify(currentPost));
      }
      window.location.href = "discussion.html";
    });
  
    // Bannissement
    $("#openBanModal").click(function () {
      $("#banModal").fadeIn();
      const $select = $("#userSelect").empty();
      allUsers.forEach(u => {
        $select.append(`<option value="${u.username}">${u.username}</option>`);
      });
    });
  
    $("#closeBanModal").click(function () {
      $("#banModal").fadeOut();
    });
  
    $("#banUserBtn").click(function () {
      const username = $("#userSelect").val();
      const remarque = $("#banReason").val();
  
      if (!remarque.trim()) {
        alert("Merci d'ajouter une remarque.");
        return;
      }
  
      banUser({ username, remarque }, function () {
        alert("Utilisateur banni avec succès !");
        deleteUser({ username }, function () {
          alert("Utilisateur supprimé avec succès !");
          $("#banModal").fadeOut();
        }, function () {
          alert("Erreur lors de la suppression de l'utilisateur.");
        });
      }, function () {
        alert("Erreur lors du bannissement.");
      });
    });
  });
  