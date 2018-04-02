package coffee.synyx.frontpage.plugin.islieb;

import org.jsoup.nodes.Element;

class IsLiebFeedEntryMapper {

    private IsLiebFeedEntryMapper() {
        // ok
    }

    static ComicDTO mapToComicDTO(IsLiebRssFeedEntry entry) {
        String title = entry.getTitle();
        Element image = entry.getImage();
        ImageDTO imageDTO = new ImageDTO(image.attr("src"));
        return new ComicDTO(title, imageDTO);
    }
}
