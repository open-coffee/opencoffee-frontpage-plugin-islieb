package coffee.synyx.frontpage.plugin.islieb;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

@Component
class SyndFeedXmlFactory {

    SyndFeed build(URL feedUrl) throws FeedException, IOException {
        SyndFeedInput input = new SyndFeedInput();
        return input.build(new XmlReader(feedUrl));
    }
}
