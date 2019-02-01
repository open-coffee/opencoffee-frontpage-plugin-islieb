package rocks.coffeenet.frontpage.plugin.islieb;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

class IsLiebFeedEntryMapper {

    private IsLiebFeedEntryMapper() {
        // ok
    }

    static ComicDTO mapToComicDTO(IsLiebRssFeedEntry entry) {
        String title = entry.getTitle();
        String content = entry.getContent();
        Element image = getImageFromContent(content);
        ImageDTO imageDTO = new ImageDTO(image.attr("src"), image.attr("alt"));
        return new ComicDTO(title, imageDTO);
    }

    private static Element getImageFromContent(String htmlString) {
        Document rssFeed = Jsoup.parse(htmlString);
        return rssFeed.select("img").get(0);
    }
}
