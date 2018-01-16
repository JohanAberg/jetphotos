import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!"); // Display the string.

        Document doc = Jsoup.connect("https://www.jetphotos.com/registration/ZK-AMY").get();
        Elements photo = doc.getElementsByClass("result__section--photo-wrapper");
        Element url = photo.select("result__photo").first();

    }
}
