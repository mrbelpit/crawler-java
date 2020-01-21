import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PageMapper {

  private List<String> jobsOnListPage;


  private static int unParsableURLs = 0;

  PageMapper() {
    jobsOnListPage = new ArrayList<>();
  }


  public PageMapper(String url) {
    this();
  }


  public String extract(int counter, String url)
      throws IOException, InterruptedException {
    if (getNextJobListPageURL(url).isEmpty()) {
      getNumbeOfUnparsableURLToFile();
      return null;
    } else {
      sleep(10);
      counter = extractJobURLs(counter, url);
      url = getNextJobListPageURL(url);
      return extract(counter, url);
    }
  }

/*  public ArrayList<String> getAllFormFirstPage(String url)
      throws IOException, InterruptedException {
    sleep(10);
    System.out.println("Next page will be: " + getNextJobListPageURL(url));
    ArrayList<String> jobContentContainer = callGetJoPropertiesFuncionForEachJobInListing(url);
    return jobContentContainer;
  }*/


  private String getNextJobListPageURL(String url) {
    try {
      Document document = Jsoup.connect(url).get();
      return String
          .valueOf(document.getElementsByClass("next btn btn-outline-primary").attr("href"));
    } catch (Exception e) {
      System.out.println("Vmi más exception");
      e.printStackTrace();
    }
    return null;
  }

  private int extractJobURLs(int counter, String url)
      throws IOException, InterruptedException {
    String destinationFileName = "files/jobPage" + String.valueOf(counter) + ".tsv";
    Document document = Jsoup.connect(url).get();
    sleep(5);
    Elements jobCards = document.getElementsByClass("job-card__title");
    ArrayList<String> jobContentContainer = new ArrayList<>();
    for (Element jobCard : jobCards) {
      jobContentContainer
          .add(getJobProperties(jobContentContainer, jobCard.select("a").attr("href")));
    }
    writreContentToFile(destinationFileName, jobContentContainer);
    System.out.println(counter);
    ++counter;
    return counter;
  }

  private String getJobProperties(ArrayList<String> jobContentContainer,
      String url) //TODO erre igazán írhatnék egy osztályt
      throws IOException, InterruptedException {
    StringBuilder row = new StringBuilder();
    Random r = new Random();
    Document document = Jsoup.parse(String.valueOf(Jsoup.connect(String.valueOf(url)).get()));
    TimeUnit.SECONDS.sleep(r.nextInt(22) + 18);
    String jobTitle = null;
    String tasks = null;
    String requirements = null;
    String other = null;
    String offer = null;
    String onlyText1 = null;
    String onlyText2 = null;
    String requiredExperience = null;
    String sideBox = null;
    if (document.getElementsByClass("tasks").isEmpty()) {
      ++unParsableURLs;
    } else {
      jobTitle = document.getElementsByTag("h1").text() + "\t";
      tasks = document.getElementsByClass("tasks").text() + "\t";
      requirements = document.getElementsByClass("requirements").text() + "\t";
      other = document.getElementsByClass("other").text() + "\t";
      offer = document.getElementsByClass("offer").text() + "\t";
      onlyText1 = document.getElementsByClass("only_text_1").text() + "\t";
      onlyText2 = document.getElementsByClass("only_text_2").text() + "\t";
      requiredExperience =
          document.getElementsByClass("text--inline-block text--requirements").text() + "\t";
      sideBox = String.valueOf(document.getElementsByClass("side-box__content").text());
      row.append(jobTitle).append(tasks).append(requirements).append(other).append(offer)
          .append(onlyText1).append(onlyText2).append(requiredExperience).append(sideBox);
      System.out.println(row);
    }
    return String.valueOf(row);

  }

  private void sleep(int timeToSleepInSeconds) {
    try {
      TimeUnit.SECONDS.sleep(timeToSleepInSeconds);
    } catch (InterruptedException e) {
      System.out.println("Nem sikerült aludni");
      e.printStackTrace();
    }
  }

  private void writreContentToFile(String filePath, ArrayList<String> content) {
    try {
      Files.write(Paths.get(filePath), content);
    } catch (Exception e) {
      System.out.println("Uh-oh, could not write the file!");
    }
  }

  private void getNumbeOfUnparsableURLToFile() {
    try {
      Files.write(Paths.get("files/unparsables.txt"),
          Collections.singleton(String.valueOf(PageMapper.unParsableURLs)));
    } catch (Exception e) {
      System.out.println("Uh-oh, could not write the file!");
    }
  }
}