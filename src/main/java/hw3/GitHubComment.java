package hw3;

public record GitHubComment(
    String commentId, String commitId, String url, String author, String time, String body) {}
