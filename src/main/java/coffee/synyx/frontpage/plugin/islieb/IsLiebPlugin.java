package coffee.synyx.frontpage.plugin.islieb;

import coffee.synyx.frontpage.plugin.api.FrontpagePluginInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IsLiebPlugin implements FrontpagePluginInterface {

    private final IsLiebRssFeedReader isLiebRssFeedReader;

    @Autowired
    public IsLiebPlugin(IsLiebRssFeedReader isLiebRssFeedReader) {
        this.isLiebRssFeedReader = isLiebRssFeedReader;
    }

    @Override
    public String title() {
        return "";
    }

    @Override
    public String content() {
        return isLiebRssFeedReader.getNewest()
            .map(IsLiebRssFeedEntry::getValue)
            .orElse("");
    }
}
