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
            entry(content(img("image-a")), content("image-b")),
            entry(content(img("image-c")))
        ));

        when(syndFeedXmlFactory.build(any())).thenReturn(syndFeed);

        String content = isLiebPlugin.content();
        assertThat(content).isEqualTo(
            "<div style=\"display:flex;justify-content:center;height:100%;\">\n" +
            "  <img src=\"image-a\" style=\"max-height:100%;width:auto;\" />\n" +
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

    private static String img(String src) {
        return String.format("<img src=\"%s\" >", src);
    }

}
