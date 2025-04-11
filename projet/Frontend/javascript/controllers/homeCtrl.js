$(document).ready(function () {
  if (!sessionStorage.getItem("loggedUser")) {
    window.location.href = "login.html";
    return;
  }

  const defaultImage = "/projet/Frontend/assets/pas-de-photo.png"; // ← Image par défaut

  let allPosts = [];

  // Charger tous les posts
  getAllPosts(
    function (posts) {
      console.log('Posts chargés:', posts);  // Log de tous les posts
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

  $(document).on("click", ".post", function (e) {
    e.preventDefault();
    const postId = $(this).data("id");
    localStorage.setItem("selectedPostId", String(postId));
    window.location.href = "discussion.html";
  });
});
