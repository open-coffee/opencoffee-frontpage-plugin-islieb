package rocks.coffeenet.frontpage.plugin.islieb;


import com.rometools.rome.feed.synd.SyndFeed;

import java.util.List;

class IsLiebRssFeed {

    private final SyndFeed feed;

    IsLiebRssFeed(SyndFeed feed) {
        this.feed = feed;
    }

    List<IsLiebRssFeedEntry> getEntries() {
        return ListUtil.map(feed.getEntries(), IsLiebRssFeedEntry::new);
    }
}
