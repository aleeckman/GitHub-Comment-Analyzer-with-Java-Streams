package hw3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

// If you are using VS code, you should see a run button
//  on the left to run these tests. You can also go to
//  View>Testing to view the tests and run from there.

public class TestGitHubProc {
  Stream<GitHubComment> getTestData() {
    return Stream.of(
        new GitHubComment(
            "22422659",
            "b6af12b3e4586e24d88ae9843d731926c4070c25",
            "https://github.com/deis/deis/pull/2826#discussion_r22422659",
            "bacongobbler",
            "2015-01-02T21:00:12Z",
            "d'oh you meant X_DEIS_API_VERSION. In the response the patch version gets cut off"
                + " (clients should not care what happens in patch releases as they're just"
                + " patches) so `1.1` is intended:"
                + " https://github.com/deis/deis/blob/6f00ed544d8d008ae8f5769b2581e7e1897f9249/controller/api/middleware.py#L32No"
                + " change necessary then oops typo :)"),
        new GitHubComment(
            "22422662",
            "c6343604d6532d85cd531dcc7336d4626cbeba2e",
            "https://github.com/azkaban/azkaban/pull/376#discussion_r22422662",
            "hluu",
            "2015-01-02T21:00:14Z",
            "It is better not to call toString() method.  Let logger.error prints out the entire"
                + " stack trace."),
        new GitHubComment(
            "22422664",
            "c6333604d6532d85cd531dcc7336d4626cbeba2e",
            "https://github.com/azkaban/azkaban/pull/376#discussion_r22422662",
            "hluu",
            "2015-01-02T21:00:14Z",
            "It is better not to call toString() method.  Let logger.error prints out the entire"
                + " stack trace on typo here."));
  }

  @Test
  public void shouldCountAuthorWordCount() {
    var testMap = GitHubProc.getWordCount(getTestData(), "stack");
    assertEquals(2, testMap);
  }

  @Test
  public void shouldCountCommentsPerProject() {
    var testMap = GitHubProc.getPerProjectCount(getTestData());
    var expectedMap = new HashMap<String, Long>();
    expectedMap.put("azkaban/azkaban", 2L);
    expectedMap.put("deis/deis", 1L);
    assertEquals(expectedMap, testMap);
  }

  @Test
  public void shouldCountCommentsPerAuthor() {
    var testMap = GitHubProc.getAuthorActivity(getTestData());
    var expectedMap = new HashMap<String, Long>();
    expectedMap.put("hluu", 2L);
    expectedMap.put("bacongobbler", 1L);
    assertEquals(expectedMap, testMap);
  }

  @Test
  public void shouldCountCommentsWithUrlsPerAuthor() {
    var testMap = GitHubProc.getCommentUrlAuthorCount(getTestData());
    var expectedMap = new HashMap<String, Long>();
    expectedMap.put("bacongobbler", 1L);
    assertEquals(expectedMap, testMap);
  }

  @Test
  public void shouldCountAuthorWordCountPerProject() {
    var testMap = GitHubProc.getAuthorWordCountPerProject(getTestData(), "typo");
    var expectedProjectMap = new HashMap<String, Map<String, Long>>();
    expectedProjectMap.put(
        "deis/deis",
        new HashMap<String, Long>() {
          {
            put("bacongobbler", 1L);
          }
        });
    expectedProjectMap.put(
        "azkaban/azkaban",
        new HashMap<String, Long>() {
          {
            put("hluu", 1L);
          }
        });
    assertEquals(expectedProjectMap, testMap);
  }

  @Test
  public void shouldCountAuthorAverageVerbosity() {
    var tinyTest = Stream.of(new GitHubComment("", "", "", "test", "", "Hi, I’m a comment!"));
    var testMap = GitHubProc.getAuthorAverageVerbosity(tinyTest);
    var expectedMap = new HashMap<String, Double>();
    expectedMap.put("test", 4.0);
    assertEquals(expectedMap, testMap);

    testMap = GitHubProc.getAuthorAverageVerbosity(getTestData());
    expectedMap = new HashMap<String, Double>();
    expectedMap.put("bacongobbler", 37.0);
    expectedMap.put("hluu", 17.5);
    assertEquals(expectedMap, testMap);
  }

  @Test
  public void shouldFilterCommentsWithUrl() {
    var testStream = GitHubProc.filterCommentsWithUrl(getTestData());
    var match =
        testStream.allMatch(
            comment -> {
              return comment.body().contains("https://") || comment.body().contains("http://");
            });
    assertTrue(match);
  }
}
