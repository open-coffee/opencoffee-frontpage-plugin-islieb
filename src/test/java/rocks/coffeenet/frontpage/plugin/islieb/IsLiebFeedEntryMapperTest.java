package rocks.coffeenet.frontpage.plugin.islieb;

import com.rometools.rome.feed.synd.SyndEntry;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IsLiebFeedEntryMapperTest {

    @Test
    public void ensureComicDTOMapping() {
        ImageDTO expectedImageDTO = new ImageDTO("image-src", "alt text");
        ComicDTO expectedComicDTO = new ComicDTO("my-title", expectedImageDTO);

        SyndEntry syndEntry = TestDomain.anySyndEntry("my-title", "<img src=\"image-src\" alt=\"alt text\" />");
        IsLiebRssFeedEntry rssFeedEntry = new IsLiebRssFeedEntry(syndEntry);

        ComicDTO comicDTO = IsLiebFeedEntryMapper.mapToComicDTO(rssFeedEntry);
        assertThat(comicDTO).isEqualTo(expectedComicDTO);
    }
}
