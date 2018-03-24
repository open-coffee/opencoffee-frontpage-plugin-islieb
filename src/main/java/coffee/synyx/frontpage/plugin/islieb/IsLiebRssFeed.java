package coffee.synyx.frontpage.plugin.islieb;

import com.sun.syndication.feed.synd.SyndFeed;

import java.util.List;

import static coffee.synyx.frontpage.plugin.islieb.ListUtil.map;

class IsLiebRssFeed {

    private final SyndFeed feed;

    public IsLiebRssFeed(SyndFeed feed) {
        this.feed = feed;
    }

    public List<IsLiebRssFeedEntry> getEntries() {
        return map(feed.getEntries(), IsLiebRssFeedEntry::new);
    }
}
