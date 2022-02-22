package hw3;

import de.siegmar.fastcsv.reader.NamedCsvReader;
import de.siegmar.fastcsv.reader.NamedCsvRow;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Util {
  public static Stream<GitHubComment> readComments(Path csvPath) {
    try {
      return NamedCsvReader.builder().build(csvPath).stream()
          .map(
              (NamedCsvRow csvRow) -> {
                return new GitHubComment(
                    csvRow.getField("COMMENT_ID"),
                    csvRow.getField("COMMIT_ID"),
                    csvRow.getField("URL"),
                    csvRow.getField("AUTHOR"),
                    csvRow.getField("CREATED_AT"),
                    csvRow.getField("BODY"));
              });
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  public static String getProject(GitHubComment issue) {
    String[] items = issue.url().split("/");
    if (items.length < 3) {
      return null;
    }
    return (items[3] + "/" + items[4]);
  }

  public static String[] getWords(String string) {
    return string.split("\\s+");
  }
}
