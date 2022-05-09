package hw3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class StudentTest {
    Stream<GitHubComment> getTestData() {
        return Stream.of(
            new GitHubComment(
                "22422968",
                "a9f297a8f06d98cdaf9246a776ddea3d8e5b1634",
                "https://github.com/javaproject/javaproject/pull/590#discussion_r22422968",
                "Crichton",
                "2015-01-02T21:10:32Z",
                "Version 1.0, I don't remember enough Java. May contain a few errors"),
            new GitHubComment(
                "22423062",
                "c6343604d6532d85cd531dcc7336d4626cbeba2e",
                "https://github.com/javaproject/javaproject/pull/376#discussion_r22423062",
                "Tolstoy",
                "2015-01-02T21:13:33Z",
                "Not quite sure about the Draw part of method getDrawMetric. Can it just be getMetrics()?"),
            new GitHubComment(
                "22422664",
                "c6333604d6532d85cd531dcc7336d4626cbeba2e",
                "https://github.com/javaproject/javaproject/pull/376#discussion_r22422662",
                "Hemmingway",
                "2015-01-02T21:00:14Z",
                "This is contains errors : printStackElement prints out the entire stack trace when button is pressed."),
            new GitHubComment(
                "22425855",
                "c6333604d6532d85cd531dcc7336d4626cbeba2e",
                "https://github.com/javaproject/javaproject/pull/376#discussion_r22422663",
                "Hemmingway",
                "2015-01-02T21:00:14Z",
                "This is a project really makes C++ look really good. I create less errors in C++."),
            new GitHubComment(
                "22422664",
                "c6333604d6532d85cd531dcc7336d4626cbeba2e",
                "https://github.com/javaproject/javaproject/pull/376#discussion_r22422662",
                "Tolstoy",
                "2015-01-02T21:00:14Z",
                "toString() override, reference: https://www.stackoverflow.com"),
            new GitHubComment(
                "22422664",
                "c6333604d6532d85cd531dcc7336d4626cbeba2e",
                "https://github.com/javaproject/javaproject/pull/376#discussion_r22422662",
                "Crichton",
                "2015-01-02T21:00:14Z",
                "I don't want to work on this project anymore, there's too many errors"),
            new GitHubComment(
                "22422664",
                "c6333604d6532d85cd531dcc7336d4626cbeba2e",
                "https://github.com/javaproject/javaproject/pull/376#discussion_r22422662",
                "Crichton",
                "2015-01-02T21:00:14Z",
                "Okay i fixed the code, check out this playlist btw: https://www.spotify.com/playlist/random"),
            new GitHubComment(
                "22422664",
                "c6333604d6532d85cd531dcc7336d4626cbeba2e",
                "https://github.com/javaproject/javaproject/pull/376#discussion_r22422662",
                "Hemmingway",
                "2015-01-02T21:00:14Z",
                "No Crichton, you just added more errors"),
            new GitHubComment(
                "22422664",
                "c6333604d6532d85cd531dcc7336d4626cbeba2e",
                "https://github.com/javaproject/javaproject/pull/376#discussion_r22422662",
                "Crichton",
                "2015-01-02T21:00:14Z",
                "I am finding a new team, yall should look at this: http://www.howtocode.com"));
      }


    @Test
    public void shouldCountWordUsageManyAuthorsSingularProjectTest() { 
        var testMap = GitHubProc.getAuthorWordCountPerProject(getTestData(), "errors");
        var expectedMap = new HashMap<String, Map<String, Long>>();

        expectedMap.put(
            "javaproject/javaproject",
            new HashMap<String, Long>() {
              {
                put("Crichton", 2L);
                put("Tolstoy", 0L);
                put("Hemmingway", 3L);
              }
            });

        assertEquals(testMap, expectedMap);
    }
}
