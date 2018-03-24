package coffee.synyx.frontpage.plugin.islieb;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;

import java.util.List;

class IsLiebRssFeedEntry {

    private final SyndEntry entry;

    public IsLiebRssFeedEntry(SyndEntry entry) {
        this.entry = entry;
    }

    public String getValue() {
        List<SyndContent> contents = entry.getContents();
        return contents.get(0).getValue();
    }
}
