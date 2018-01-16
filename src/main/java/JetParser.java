import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class JetParser {

    public static void parsePage() throws IOException {
        Document doc = Jsoup.connect("https://www.jetphotos.com/registration/ZK-AMY").get();
        Elements photoWrapper = doc.getElementsByClass("result__section--photo-wrapper");
        Element imageUrl = photoWrapper.select("result__photo").first();

        JetPhoto photo = new JetPhoto();
        photo.setThumbnail(new URL("url/to/image"));
        photo.setAuthor("Foo");
    }

}
