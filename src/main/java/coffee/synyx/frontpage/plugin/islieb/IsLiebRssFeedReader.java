package coffee.synyx.frontpage.plugin.islieb;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import static coffee.synyx.frontpage.plugin.islieb.ListUtil.getFirst;
import static java.lang.invoke.MethodHandles.lookup;
import static java.util.Collections.emptyList;

@Component
class IsLiebRssFeedReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(lookup().lookupClass());
    private static final String ISLIEB_FEED_URL = "https://islieb.de/feed/";

    private final SyndFeedXmlFactory syndFeedXmlFactory;

    @Autowired
    IsLiebRssFeedReader(SyndFeedXmlFactory syndFeedXmlFactory) {
        this.syndFeedXmlFactory = syndFeedXmlFactory;
    }

    Optional<IsLiebRssFeedEntry> getNewest() {
        return getFirst(getEntries());
    }

    List<IsLiebRssFeedEntry> getEntries() {
        return getFeed()
            .map(IsLiebRssFeed::new)
            .map(IsLiebRssFeed::getEntries)
            .orElse(emptyList());
    }

    private Optional<SyndFeed> getFeed() {
        try {
            URL feedUrl = new URL(ISLIEB_FEED_URL);
            SyndFeed syndFeed = syndFeedXmlFactory.build(feedUrl);
            return Optional.of(syndFeed);
        } catch (FeedException | IOException e) {
            LOGGER.info("Plugin isLieb: Cannot receive rss feed of islieb.", e);
        }
        return Optional.empty();
    }
}
