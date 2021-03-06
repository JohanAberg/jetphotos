import nz.co.aberg.blip.JetPhoto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
public class JetParser {

    public static ArrayList<JetPhoto> parsePage(String URL) throws IOException {
        //This ArrayList<JetPhoto> jetPhotos it uses to store JetPhoto objects
        ArrayList<nz.co.aberg.blip.JetPhoto> jetPhotos = new ArrayList<>();
        //Retrieving specific resources from the URL
        Element thumbnailSrc, modelSrc, authorSrc, imageSrc;
        //Strings are used to store each detail that's already done filter
        String  thumbnail, mainImage, author, model;


        Document doc = Jsoup.connect(URL).get();

        Elements photoWrapper = doc.getElementsByClass("result");
        for (Element element : photoWrapper) {
            //Filter and parse to String

            thumbnailSrc = element.select(".result__photo").first();
            modelSrc = element.select(".mobile-only > ul > li:nth-child(2)").first();
            authorSrc = element.select(".result__infoListText--photographer > a").first();
            imageSrc = element.select(".result__photoLink").first();

            try {
                if( ( thumbnailSrc != null && modelSrc != null) && (authorSrc != null && imageSrc != null ) ) {
                    thumbnail = thumbnailSrc.attr("abs:src");
                    mainImage = imageSrc.attr("abs:href");
                    author = authorSrc.text();
                    model = modelSrc.text();

                    nz.co.aberg.blip.JetPhoto photo = new nz.co.aberg.blip.JetPhoto();
                    photo.setAuthor(author);
                    photo.setThumbnail(new URL(thumbnail));
                    photo.setPhotoUrl(new URL(mainImage));
                    photo.setAircraftModel(model);

                    jetPhotos.add(photo);
                }
            }catch(NullPointerException e)
            {
                System.out.print("NullPointerException Caught");
            }
        }
        return jetPhotos;
    }

}
