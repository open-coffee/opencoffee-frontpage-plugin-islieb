package coffee.synyx.frontpage.plugin.islieb;

import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class IsLiebPluginTest {

    @Test
    public void hasTitle() {
        final IsLiebRssFeedReader feedReader = mock(IsLiebRssFeedReader.class);
        final IsLiebContentRenderer renderer = mock(IsLiebContentRenderer.class);
        final IsLiebPlugin sut = new IsLiebPlugin(feedReader, renderer);
        assertThat(sut.title(), is(""));
    }

    @Test
    public void hasContentForDefinedFeedEntry() {
        final IsLiebRssFeedReader feedReader = mock(IsLiebRssFeedReader.class);
        final IsLiebContentRenderer renderer = mock(IsLiebContentRenderer.class);
        final IsLiebRssFeedEntry feedEntry = mock(IsLiebRssFeedEntry.class);
        when(feedReader.getNewest()).thenReturn(Optional.of(feedEntry));
        when(renderer.render(any())).thenReturn("islieb content");

        final IsLiebPlugin sut = new IsLiebPlugin(feedReader, renderer);
        final String actualContent = sut.content();

        verify(feedReader).getNewest();
        verify(renderer).render(feedEntry);
        assertThat(actualContent, is("islieb content"));
    }

    @Test
    public void hasNoContentForEmptyFeedEntry() {
        final IsLiebRssFeedReader feedReader = mock(IsLiebRssFeedReader.class);
        final IsLiebContentRenderer renderer = mock(IsLiebContentRenderer.class);
        when(feedReader.getNewest()).thenReturn(Optional.empty());

        final IsLiebPlugin sut = new IsLiebPlugin(feedReader, renderer);
        final String actualContent = sut.content();

        verify(feedReader).getNewest();
        verifyZeroInteractions(renderer);
        assertThat(actualContent, is(""));
    }
}
