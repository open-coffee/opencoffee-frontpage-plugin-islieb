package coffee.synyx.frontpage.plugin.islieb;

import coffee.synyx.frontpage.plugin.api.FrontpagePluginInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        return isLiebRssFeedReader.getNewest()
            .map(IsLiebRssFeedEntry::getImage)
            .map(JsoupElementMapper::mapImageElement)
            .map(renderer::render)
            .orElse("");
    }
}
