package coffee.synyx.frontpage.plugin.islieb;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
    IsLiebPlugin.class,
    IsLiebRssFeedReader.class,
    IsLiebContentRenderer.class,
    IsLiebPluginConfiguration.class,
})
public class IsLiebPluginIntegrationTest {

    @Autowired
    private IsLiebPlugin isLiebPlugin;

    @MockBean
    private SyndFeedXmlFactory syndFeedXmlFactory;

    @Test
    public void ensureContent() throws Exception {
        SyndFeedImpl syndFeed = new SyndFeedImpl();
        syndFeed.setEntries(Arrays.asList(
            entry(content(img("image-a", "alt-text-1")), content("not-used-image")),
            entry(content(img("image-b", "alt-text-2"))),
            entry(content(img("image-c", "alt-text-3")))
        ));

        when(syndFeedXmlFactory.build(any())).thenReturn(syndFeed);

        String content = isLiebPlugin.content();
        assertThat(content).isEqualTo(
            "<div class=\"islieb-wrapper\">\n" +
            "  \n" +
            "    <h3 class=\"islieb-comic--title\">\n" +
            "      <a href=\"image-a\"></a>\n" +
            "    </h3>\n" +
            "    <div class=\"islieb-comic--content\">\n" +
            "      <a href=\"image-a\">\n" +
            "        <img src=\"image-a\" alt=\"alt-text-1\" />\n" +
            "      </a>\n" +
            "    </div>\n" +
            "  \n" +
            "  \n" +
            "    <h3 class=\"islieb-comic--title\">\n" +
            "      <a href=\"image-b\"></a>\n" +
            "    </h3>\n" +
            "    <div class=\"islieb-comic--content\">\n" +
            "      <a href=\"image-b\">\n" +
            "        <img src=\"image-b\" alt=\"alt-text-2\" />\n" +
            "      </a>\n" +
            "    </div>\n" +
            "  \n" +
            "  \n" +
            "    <h3 class=\"islieb-comic--title\">\n" +
            "      <a href=\"image-c\"></a>\n" +
            "    </h3>\n" +
            "    <div class=\"islieb-comic--content\">\n" +
            "      <a href=\"image-c\">\n" +
            "        <img src=\"image-c\" alt=\"alt-text-3\" />\n" +
            "      </a>\n" +
            "    </div>\n" +
            "  \n" +
            "</div>\n" +
            "<style>\n" +
            "  .islieb-wrapper {\n" +
            "    height: 100%;\n" +
            "    overflow: auto;\n" +
            "  }\n" +
            "  .islieb-comic--title {\n" +
            "    margin: 0;\n" +
            "    padding: 0;\n" +
            "    height: 2rem;\n" +
            "    font-size: 1rem;\n" +
            "    font-weight: 400;\n" +
            "  }\n" +
            "  .islieb-comic--title a {\n" +
            "    color: inherit;\n" +
            "    text-decoration: none\n" +
            "  }\n" +
            "\n" +
            "  .islieb-comic--content {\n" +
            "    height: calc(100% - 2rem);\n" +
            "    text-align: center;\n" +
            "  }\n" +
            "  .islieb-comic--content img {\n" +
            "    max-height: 100%;\n" +
            "    width: auto;\n" +
            "  }\n" +
            "</style>\n"
        );
    }

    private static SyndEntry entry(SyndContent... content) {
        SyndEntryImpl entry = new SyndEntryImpl();
        entry.setContents(Arrays.asList(content));
        return entry;
    }

    private static SyndContent content(String value) {
        SyndContentImpl content = new SyndContentImpl();
        content.setValue(value);
        return content;
    }

    private static String img(String src, String alt) {
        return String.format("<img src=\"%s\" alt=\"%s\" >", src, alt);
    }

}
