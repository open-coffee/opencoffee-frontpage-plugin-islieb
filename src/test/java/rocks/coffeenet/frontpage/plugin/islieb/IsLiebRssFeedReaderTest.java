package rocks.coffeenet.frontpage.plugin.islieb;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IsLiebRssFeedReaderTest {

    @Test
    public void verifyGetNewest() throws Exception {
        final SyndFeed syndFeed = TestDomain.anySyndFeed(
            asList(
                TestDomain.anySyndEntry("title1", "content-first"),
                TestDomain.anySyndEntry("title2", "content-second")
            )
        );
        final URL expectedURL = new URL("https://islieb.de/feed/");
        final SyndFeedXmlFactory syndFeedXmlFactory = mock(SyndFeedXmlFactory.class);
        when(syndFeedXmlFactory.build(expectedURL)).thenReturn(syndFeed);

        IsLiebRssFeedReader sut = new IsLiebRssFeedReader(syndFeedXmlFactory);
        Optional<IsLiebRssFeedEntry> newest = sut.getNewest();
        assertThat(newest.isPresent()).isTrue();
        assertThat(newest.get().getContent()).isEqualTo("content-first");
    }

    @Test
    public void verifyGetNewestEmptyWhenFeedExceptionIsCatched() throws Exception {
        verifyEmptyOptionalForException(new FeedException("whoops. bad feed exception."));
    }

    @Test
    public void verifyGetNewestEmptyWhenIOExceptionIsCatched() throws Exception {
        verifyEmptyOptionalForException(new IOException());
    }

    private void verifyEmptyOptionalForException(Throwable throwable) throws Exception {
        final URL expectedURL = new URL("https://islieb.de/feed/");
        final SyndFeedXmlFactory syndFeedXmlFactory = mock(SyndFeedXmlFactory.class);
        when(syndFeedXmlFactory.build(expectedURL)).thenThrow(throwable);

        IsLiebRssFeedReader sut = new IsLiebRssFeedReader(syndFeedXmlFactory);
        Optional<IsLiebRssFeedEntry> newest = sut.getNewest();
        assertThat(newest.isPresent()).isFalse();
    }
}
