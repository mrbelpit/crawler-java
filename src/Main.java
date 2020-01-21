import java.io.IOException;
import java.util.ArrayList;

public class Main {

  public static void main(String[] args) throws IOException, InterruptedException {

    ArrayList<String> listOfJobSearchPages = new ArrayList();

    PageMapper getAllJobs = new PageMapper();

    int counter = 1;
    getAllJobs.extract(counter ,"");


  }
}
