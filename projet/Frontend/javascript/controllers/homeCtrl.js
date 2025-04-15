$(document).ready(function () {
    // Initialisation de l'application
    initializeApp();
});

/**
 * Initialise l'application et configure les événements
 */
function initializeApp() {
    // Supprimer les données de post précédent
    sessionStorage.removeItem("Post");
    
    // Variables globales d'application
    let allUsers = [];
    let allPosts = [];
    let currentUser = null;
    const defaultImage = "/projet/Frontend/assets/pas-de-photo.png";
    
    // Vérifier l'authentification utilisateur
    const loggedUsername = sessionStorage.getItem("loggedUser");
    if (!loggedUsername) {
        window.location.href = "login.html";
        return;
    }
    
    // Afficher le nom d'utilisateur
    $("#logged-username").text(loggedUsername);
    
    // Charger les utilisateurs
    getAllUsers(
        function(users) {
            allUsers = users;
            // Trouver l'utilisateur courant et vérifier ses droits admin
            currentUser = allUsers.find(user => user.username === loggedUsername);
            
            console.log(currentUser);
            if (currentUser.admin == true) {
                $(".ban-button").show();
                console.log("Check admin : admin" );
            } else {
                console.log("Check admin : userSimple" );
                $(".ban-button").hide();
            }
        },
        function() {
            alert("Erreur lors du chargement des utilisateurs.");
        }
    );
    
    // Charger les posts
    getAllPosts(
        function(posts) {
            console.log('Posts chargés:', posts);
            allPosts = posts;
            displayPosts(allPosts);
        },
        function() {
            alert("Erreur lors du chargement des posts.");
        }
    );
    
    // Configurer les écouteurs d'événements pour la recherche
    $("#searchInput").on("input", function() {
        const searchTerm = $(this).val().toLowerCase();
        const filteredPosts = allPosts.filter(post =>
            (post.title || "").toLowerCase().includes(searchTerm)
        );
        displayPosts(filteredPosts);
    });
    
    // Réinitialisation du filtre
    $("#resetFilter").on("click", function() {
        displayPosts(allPosts);
    });
    
    // Filtrage par catégorie
    $(".sidebar ul li").on("click", function() {
        const selectedCategory = $(this).text().trim().toLowerCase();
        const filteredPosts = allPosts.filter(post =>
            (post.category || "").toLowerCase() === selectedCategory
        );
        displayPosts(filteredPosts);
    });
    
    // Clic sur un post
    $(document).on("click", ".post", function(e) {
        e.preventDefault();
        const postId = $(this).data("id");
        const selectedPost = allPosts.find(p => p.postId === postId);
        if (selectedPost) {
            sessionStorage.setItem("Post", JSON.stringify(selectedPost));
        }
        window.location.href = "discussion.html";
    });
    
    // Modal de bannissement
    $("#openBanModal").click(function() {
        // Vérifier à nouveau les permissions admin avant d'ouvrir le modal
        checkAdminPermissions(function() {
            $("#banModal").fadeIn();
            const $select = $("#userSelect").empty();
            allUsers.forEach(u => {
                $select.append(`<option value="${u.username}">${u.username}</option>`);
            });
        });
    });
    
    $("#closeBanModal").click(function() {
        $("#banModal").fadeOut();
    });
    
    $("#banUserBtn").click(function() {
        // Vérifier à nouveau les permissions admin avant de bannir
        checkAdminPermissions(function() {
            const username = $("#userSelect").val();
            const remarque = $("#banReason").val();
            
            if (!remarque.trim()) {
                alert("Merci d'ajouter une remarque.");
                return;
            }
            
            banUser(
                { username, remarque },
                function() {
                    alert("Utilisateur banni avec succès !");
                    deleteUser(
                        { username },
                        function() {
                            alert("Utilisateur supprimé avec succès !");
                            $("#banModal").fadeOut();
                        },
                        function() {
                            alert("Erreur lors de la suppression de l'utilisateur.");
                        }
                    );
                },
                function() {
                    alert("Erreur lors du bannissement.");
                }
            );
        });
    });
    
    /**
     * Vérifie les permissions d'administrateur
     * @param {Function} callback Fonction à exécuter si l'utilisateur est admin
     */
    function checkAdminPermissions(callback) {
        // Rafraîchir les informations utilisateur pour plus de sécurité
        const loggedUsername = sessionStorage.getItem("loggedUser");
        
        // Vérifier dans la liste des utilisateurs chargée
        const user = allUsers.find(u => u.username === loggedUsername);
        
        if (user && user.admin) {
            callback();
        } else {
            alert("Vous n'avez pas les permissions nécessaires.");
            window.location.reload(); // Recharger la page pour actualiser l'interface
        }
    }
    
    /**
     * Affiche les posts
     * @param {Array} posts Liste des posts à afficher
     */
    function displayPosts(posts) {
        const $postList = $(".post-list");
        $postList.empty();
        
        if (posts.length === 0) {
            $postList.append("<p>Aucun post trouvé pour cette catégorie.</p>");
            return;
        }
        
        for (let post of posts) {
            const username = post.creatorUsername || "Auteur inconnu";
            const imageUrl = post.imageUrl && post.imageUrl.trim() !== "" 
                ? post.imageUrl 
                : defaultImage;
            
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
};