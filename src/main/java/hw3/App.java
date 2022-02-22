package hw3;

import java.io.IOException;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) throws IOException {
        // You may change this method, we will only evaluate methods in GitHubProc
        // you will need to change the path on this next line.
        var csvPath = Paths.get("/Users/devanbu/Downloads/hw32/hw3starter/data/github_issues.csv");
        // Uncomment the following line to see what the stream looks like, and get
        // started.
        // Util.readComments(csvPath).limit(5).forEach(System.out::println);
        // The rest of this is unimplemented, so won't work.
        System.out.println(GitHubProc.getWordCount(Util.readComments(csvPath), "typo"));
        System.out.println(GitHubProc.getPerProjectCount(Util.readComments(csvPath)));
        System.out.println(GitHubProc.getAuthorActivity(Util.readComments(csvPath)));
        System.out.println(GitHubProc.getAuthorAverageVerbosity(Util.readComments(csvPath)));
        System.out.println(
            GitHubProc.getAuthorWordCountPerProject(Util.readComments(csvPath), "typo")
        );
        System.out.println(GitHubProc.getCommentUrlAuthorCount(Util.readComments(csvPath)));
        System.out.println(GitHubProc.filterCommentsWithUrl(Util.readComments(csvPath)).count());
    }
}
