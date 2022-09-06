package ru.job4j.gc.leak;

import java.util.List;
import java.util.Objects;

public class Post {

    private int id;

    private String text;

    private List<Comment> comments;

    public Post(int id, String text, List<Comment> comments) {
        this.id = id;
        this.text = text;
        this.comments = comments;
    }

    public Post(String text, List<Comment> comments) {
        this.text = text;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public String getList() {
        StringBuilder builder = new StringBuilder();
        comments.forEach(e -> {
            builder.append(e);
            builder.append(" ");
        });
        System.out.println(comments.get(0) + "For test");
        return String.valueOf(builder);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return Objects.equals(id, post.id)
                && Objects.equals(text, post.getText());
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = hash * 31 + Objects.hashCode(getId());
        hash = hash * 31 + Objects.hash(getText());
        return hash;
    }

    @Override
    public String toString() {
        return "Post{"
                + "id=" + id
                + ", text='" + text + '\''
                + ", comments=" + comments
                + '}';
    }
}
