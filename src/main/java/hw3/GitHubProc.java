package hw3;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.summingLong;

public class GitHubProc {
  public static Long getWordCount(Stream<GitHubComment> stream, String word) {
    long wc = stream.parallel()
      .map(
        (t) -> Stream.of(Util.getWords(t.body()))
          .filter(w -> w.equals(word))
          .count()
      )
      .reduce((long)0, (x, y) -> x + y);

    return wc;
  }

  public static Map<String, Long> getPerProjectCount(Stream<GitHubComment> stream) {
    Map<String, Long> map = new HashMap<String, Long>();
    map = stream.parallel()
      .collect(groupingBy(Util::getProject, counting()));

    return map;
  }

  public static Map<String, Long> getAuthorActivity(Stream<GitHubComment> stream) {
    Map<String, Long> map = new HashMap<String, Long>();

    map = stream.parallel()
      .collect(groupingBy(GitHubComment::author, counting()));

    return map;
  }

  public static Map<String, Long> getCommentUrlAuthorCount(Stream<GitHubComment> stream) {
    Map<String, Long> map = new HashMap<String, Long>();
    map = filterCommentsWithUrl(stream).parallel()
      .collect(groupingBy(GitHubComment::author, counting()));
    
    return map;
  }

  public static Stream<GitHubComment> filterCommentsWithUrl(Stream<GitHubComment> comments) {
    return comments.parallel()
      .filter((t) -> t.body().contains("http://") || t.body().contains("https://"));
  }

  public static Map<String, Double> getAuthorAverageVerbosity(Stream<GitHubComment> stream) {
    Map<String, Double> map = new HashMap<String, Double>();
    map = stream.parallel()
      .collect(groupingBy(GitHubComment::author, averagingDouble((GitHubComment t) -> Util.getWords(t.body()).length)));

    return map;
  }

  public static Map<String, Map<String, Long>> getAuthorWordCountPerProject(Stream<GitHubComment> stream, String word) {

    return stream.parallel().collect(groupingBy(Util::getProject, 
      groupingBy(GitHubComment::author, 
        summingLong((t) -> Stream.of(Util.getWords(t.body()))
          .filter(words -> words.equals(word))
          .count()))));
  }
}
