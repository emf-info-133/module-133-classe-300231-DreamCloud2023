package doudix.ch.apigateway.dto;

public class MessageDTO {
    private Long id;
    private String text;
    private Long creatorId;
    private String creatorUsername; 
    private Long postId;

    // Constructeurs
    public MessageDTO() {}

    public MessageDTO(Long id, String text, Long creatorId, String creatorUsername, Long postId) {
        this.id = id;
        this.text = text;
        this.creatorId = creatorId;
        this.creatorUsername = creatorUsername;
        this.postId = postId;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public Long getCreatorId() { return creatorId; }
    public void setCreatorId(Long creatorId) { this.creatorId = creatorId; }

    public String getCreatorUsername() { return creatorUsername; }
    public void setCreatorUsername(String creatorUsername) { this.creatorUsername = creatorUsername; }

    public Long getPostId() { return postId; }
    public void setPostId(Long postId) { this.postId = postId; }
}
