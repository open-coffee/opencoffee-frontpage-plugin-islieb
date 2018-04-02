package coffee.synyx.frontpage.plugin.islieb;

import coffee.synyx.frontpage.plugin.api.FrontpagePluginInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static coffee.synyx.frontpage.plugin.islieb.ListUtil.map;
import static java.util.Arrays.asList;

@Component
public class IsLiebPlugin implements FrontpagePluginInterface {

    private final IsLiebRssFeedReader isLiebRssFeedReader;
    private final IsLiebContentRenderer renderer;

    @Autowired
    public IsLiebPlugin(IsLiebRssFeedReader isLiebRssFeedReader, IsLiebContentRenderer renderer) {
        this.isLiebRssFeedReader = isLiebRssFeedReader;
        this.renderer = renderer;
    }

    @Override
    public String title() {
        return "";
    }

    @Override
    public String content() {
        List<IsLiebRssFeedEntry> entries = isLiebRssFeedReader.getEntries();
        List<ComicDTO> comicDTOs = map(entries, IsLiebFeedEntryMapper::mapToComicDTO);
        return renderer.render(
            asList(
                comicDTOs.get(0),
                comicDTOs.get(1),
                comicDTOs.get(2)
            )
        );
    }
}
