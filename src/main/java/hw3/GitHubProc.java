package hw3;

import java.util.Map;
import java.util.stream.Stream;

public class GitHubProc {
  public static Long getWordCount(Stream<GitHubComment> stream, String word) {
    return null;
  }

  public static Map<String, Long> getPerProjectCount(Stream<GitHubComment> stream) {
    return null;
  }

  public static Map<String, Long> getAuthorActivity(Stream<GitHubComment> stream) {
    return null;
  }

  public static Map<String, Long> getCommentUrlAuthorCount(Stream<GitHubComment> stream) {
    return null;
  }

  public static Stream<GitHubComment> filterCommentsWithUrl(Stream<GitHubComment> comments) {
    return null;
  }

  public static Map<String, Double> getAuthorAverageVerbosity(Stream<GitHubComment> stream) {
    return null;
  }

  public static Map<String, Map<String, Long>> getAuthorWordCountPerProject(
      Stream<GitHubComment> stream, String word) {
    return null;
  }
}
