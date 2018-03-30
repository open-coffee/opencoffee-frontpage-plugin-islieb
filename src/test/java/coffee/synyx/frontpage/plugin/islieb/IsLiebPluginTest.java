package coffee.synyx.frontpage.plugin.islieb;

import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IsLiebPluginTest {

    @Test
    public void hasTitle() {
        final IsLiebRssFeedReader feedReader = mock(IsLiebRssFeedReader.class);
        final IsLiebPlugin sut = new IsLiebPlugin(feedReader);
        assertThat(sut.title(), is(""));
    }

    @Test
    public void hasContentForDefinedFeedEntry() {
        final IsLiebRssFeedReader feedReader = mock(IsLiebRssFeedReader.class);
        final IsLiebRssFeedEntry feedEntry = mock(IsLiebRssFeedEntry.class);
        when(feedEntry.getValue()).thenReturn("islieb content");
        when(feedReader.getNewest()).thenReturn(Optional.of(feedEntry));

        final IsLiebPlugin sut = new IsLiebPlugin(feedReader);
        final String actualContent = sut.content();

        verify(feedReader).getNewest();
        verify(feedEntry).getValue();
        assertThat(actualContent, is("islieb content"));
    }

    @Test
    public void hasNoContentForEmptyFeedEntry() {
        final IsLiebRssFeedReader feedReader = mock(IsLiebRssFeedReader.class);
        when(feedReader.getNewest()).thenReturn(Optional.empty());

        final IsLiebPlugin sut = new IsLiebPlugin(feedReader);
        final String actualContent = sut.content();

        verify(feedReader).getNewest();
        assertThat(actualContent, is(""));
    }
}
