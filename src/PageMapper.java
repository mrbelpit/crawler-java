import JobDescription.JobDescription;
import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class PageMapper {

  private final Gson gson = new Gson();
  private final Random random = new Random();

  public PageMapper() {
  }

  public void extract(String jobListPageUrl) throws IOException, InterruptedException {
    if (getNextJobListPageURL(jobListPageUrl).isEmpty()) {
      System.out.println("Extraction complete");
      System.exit(0);
    } else {
      sleep(random.nextInt(6) + 7);
      getJobPropertiesFromJobListPage(jobListPageUrl);
      String nextJobListPageUrl = getNextJobListPageURL(jobListPageUrl);
      extract(nextJobListPageUrl);
    }
  }

  private String getNextJobListPageURL(String jobListPageUrl) {
    try {
      Document document = Jsoup.connect(jobListPageUrl).get();
      return document.getElementsByClass("next btn btn-outline-primary").attr("href");
    } catch (Exception e) {
      System.out.println("Exception occurred when getting next job list page url. Happened at: " + jobListPageUrl);
      e.printStackTrace();
    }
    return "";
  }

  private void getJobPropertiesFromJobListPage(String jobListPageUrl) throws IOException, InterruptedException {
    Document document = Jsoup.connect(jobListPageUrl).get();
    sleep(random.nextInt(5) + 2);
    Elements jobCards = document.getElementsByClass("job-card__title");
    ArrayList<String> jobPropertyList = new ArrayList<>();
    for (Element jobCard : jobCards) {
      String jobUrl = jobCard.select("a").attr("href");
      String jobProperties = extractJobPropertiesFromJobUrl(jobUrl);
      jobPropertyList.add(jobProperties);
    }
    Path outputFile = Paths.get("output/output.tsv");
    Files.write(outputFile, jobPropertyList, StandardOpenOption.APPEND);
  }

  private String extractJobPropertiesFromJobUrl(String url) throws IOException, InterruptedException {
    TimeUnit.SECONDS.sleep(random.nextInt(22) + 18);
    String json = getJobDescriptionJsonFromHTML(url);
    JobDescription jobDescription = gson.fromJson(json, JobDescription.class);
    return jobDescription.toString();
  }

  private String getJobDescriptionJsonFromHTML(String jobUrl) throws IOException {
    Document jobHtml = Jsoup.connect(jobUrl).get();
    Elements scripts = jobHtml.getElementsByTag("script");
    return scripts.stream()
            .map(String::valueOf)
            .filter(script -> script.contains("JobPosting"))
            .map(string -> string.substring(40, string.length() - 10))
            .collect(Collectors.joining());
  }

  private void sleep(int timeToSleepInSeconds) {
    try {
      TimeUnit.SECONDS.sleep(timeToSleepInSeconds);
    } catch (InterruptedException e) {
      System.out.println("Interrupted by keyboard command");
      e.printStackTrace();
    }
  }
}