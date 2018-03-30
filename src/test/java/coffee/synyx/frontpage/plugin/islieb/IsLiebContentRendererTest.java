package coffee.synyx.frontpage.plugin.islieb;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IsLiebContentRendererTest {

    @Test
    public void ensureFeedEntryRendering() {
        IsLiebRssFeedEntry entry = TestDomain.anyIsLiebRssFeedEntry("content");
        IsLiebContentRenderer renderer = new IsLiebContentRenderer();
        String actual = renderer.render(entry);
        assertThat(actual).isEqualTo("content");
    }
}
