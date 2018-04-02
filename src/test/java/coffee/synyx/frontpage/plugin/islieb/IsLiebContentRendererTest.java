package coffee.synyx.frontpage.plugin.islieb;

import org.junit.Ignore;
import org.junit.Test;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IsLiebContentRendererTest {

    @Test
    @Ignore("TemplateEngine#process is final. waiting for Thymeleaf3 and ITemplateEngine")
    public void ensureFeedEntryRendering() {
        TemplateEngine templateEngine = mock(TemplateEngine.class);
        when(templateEngine.process(anyString(), any(Context.class))).thenReturn("rendered content");

        final ImageDTO imageDTO = new ImageDTO("image-src");
        final ComicDTO comicDTO = new ComicDTO("my-title", imageDTO);
        final Context expectedContext = new Context();
        expectedContext.setVariable("image", imageDTO);

        IsLiebContentRenderer renderer = new IsLiebContentRenderer(templateEngine);
        String actual = renderer.render(comicDTO);

        verify(templateEngine).process("image", expectedContext);
        assertThat(actual).isEqualTo("rendered content");
    }
}
