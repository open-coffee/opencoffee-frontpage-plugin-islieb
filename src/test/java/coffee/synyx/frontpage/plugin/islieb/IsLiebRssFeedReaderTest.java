package coffee.synyx.frontpage.plugin.islieb;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.FeedException;
import org.junit.Test;
import org.mockito.MockSettings;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import static coffee.synyx.frontpage.plugin.islieb.TestDomain.anySyndEntry;
import static coffee.synyx.frontpage.plugin.islieb.TestDomain.anySyndFeed;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IsLiebRssFeedReaderTest {

    @Test
    public void verifyGetNewest() throws Exception {
        final SyndFeed syndFeed = anySyndFeed(
            asList(
                anySyndEntry("first"),
                anySyndEntry("second")
            )
        );
        final URL expectedURL = new URL("https://islieb.de/feed/");
        final SyndFeedXmlFactory syndFeedXmlFactory = mock(SyndFeedXmlFactory.class);
        when(syndFeedXmlFactory.build(expectedURL)).thenReturn(syndFeed);

        IsLiebRssFeedReader sut = new IsLiebRssFeedReader(syndFeedXmlFactory);
        Optional<IsLiebRssFeedEntry> newest = sut.getNewest();
        assertThat(newest.isPresent(), is(true));
        assertThat(newest.get().getValue(), is("first"));
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
        assertThat(newest.isPresent(), is(false));
    }
}
