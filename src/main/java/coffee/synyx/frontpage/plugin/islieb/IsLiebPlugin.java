package coffee.synyx.frontpage.plugin.islieb;

import coffee.synyx.frontpage.plugin.api.ConfigurationDescription;
import coffee.synyx.frontpage.plugin.api.ConfigurationInstance;
import coffee.synyx.frontpage.plugin.api.FrontpagePlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static coffee.synyx.frontpage.plugin.islieb.ListUtil.map;

@Component
public class IsLiebPlugin implements FrontpagePlugin {

    private final IsLiebRssFeedReader isLiebRssFeedReader;
    private final IsLiebContentRenderer renderer;

    @Autowired
    public IsLiebPlugin(IsLiebRssFeedReader isLiebRssFeedReader, IsLiebContentRenderer renderer) {
        this.isLiebRssFeedReader = isLiebRssFeedReader;
        this.renderer = renderer;
    }

    @Override
    public String title(ConfigurationInstance configurationInstance) {

        return "";
    }

    @Override
    public String id() {

        return "isLieb";
    }

    @Override
    public Optional<ConfigurationDescription> getConfigurationDescription() {

        return Optional.empty();
    }

    @Override
    public String content(ConfigurationInstance configurationInstance) {

        List<IsLiebRssFeedEntry> entries = isLiebRssFeedReader.getEntries();
        List<ComicDTO> comicDTOs = map(entries, IsLiebFeedEntryMapper::mapToComicDTO);
        return renderer.render(comicDTOs);
    }
}
