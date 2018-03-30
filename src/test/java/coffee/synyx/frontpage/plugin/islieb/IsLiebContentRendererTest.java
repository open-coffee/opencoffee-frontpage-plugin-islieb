package coffee.synyx.frontpage.plugin.islieb;

import org.jsoup.nodes.Element;
import org.junit.Test;

import static coffee.synyx.frontpage.plugin.islieb.TestDomain.anyImageElement;
import static org.assertj.core.api.Assertions.assertThat;

public class IsLiebContentRendererTest {

    @Test
    public void ensureFeedEntryRendering() {
        Element image = anyImageElement();
        IsLiebRssFeedEntry entry = TestDomain.anyIsLiebRssFeedEntry(image);
        IsLiebContentRenderer renderer = new IsLiebContentRenderer();

        String actual = renderer.render(entry);

        assertThat(actual).isEqualTo(
            "<div style=\"display:flex;justify-content:center;height:100%;\">" +
                "<img width=\"800\" height=\"600\" src=\"https://islieb.de/picture/123\" style=\"max-height:100%;width:auto;\">" +
            "</div>"
        );
    }
}
