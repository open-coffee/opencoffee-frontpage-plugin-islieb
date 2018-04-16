package coffee.synyx.frontpage.plugin.islieb;


import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEntry;

import java.util.List;

class IsLiebRssFeedEntry {

    private final SyndEntry entry;

    IsLiebRssFeedEntry(SyndEntry entry) {
        this.entry = entry;
    }

    String getTitle() {
        return entry.getTitle();
    }

    String getContent() {
        List<SyndContent> contents = entry.getContents();
        return contents.get(0).getValue();
    }
}
