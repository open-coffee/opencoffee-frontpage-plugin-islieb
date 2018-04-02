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
            "<div style=\"height:100%;overflow:auto;\">\n" +
            "  \n" +
            "    <h3 style=\"margin:0;padding:0;height:2rem;font-size:1rem;font-weight:400;\">\n" +
            "      <a href=\"image-a\" style=\"color:inherit;text-decoration:none\"></a>\n" +
            "    </h3>\n" +
            "    <div style=\"height:calc(100% - 2rem);text-align:center;\">\n" +
            "      <a href=\"image-a\">\n" +
            "        <img src=\"image-a\" style=\"max-height:100%;width:auto;\" alt=\"alt-text-1\" />\n" +
            "      </a>\n" +
            "    </div>\n" +
            "  \n" +
            "  \n" +
            "    <h3 style=\"margin:0;padding:0;height:2rem;font-size:1rem;font-weight:400;\">\n" +
            "      <a href=\"image-b\" style=\"color:inherit;text-decoration:none\"></a>\n" +
            "    </h3>\n" +
            "    <div style=\"height:calc(100% - 2rem);text-align:center;\">\n" +
            "      <a href=\"image-b\">\n" +
            "        <img src=\"image-b\" style=\"max-height:100%;width:auto;\" alt=\"alt-text-2\" />\n" +
            "      </a>\n" +
            "    </div>\n" +
            "  \n" +
            "  \n" +
            "    <h3 style=\"margin:0;padding:0;height:2rem;font-size:1rem;font-weight:400;\">\n" +
            "      <a href=\"image-c\" style=\"color:inherit;text-decoration:none\"></a>\n" +
            "    </h3>\n" +
            "    <div style=\"height:calc(100% - 2rem);text-align:center;\">\n" +
            "      <a href=\"image-c\">\n" +
            "        <img src=\"image-c\" style=\"max-height:100%;width:auto;\" alt=\"alt-text-3\" />\n" +
            "      </a>\n" +
            "    </div>\n" +
            "  \n" +
            "</div>\n"
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
