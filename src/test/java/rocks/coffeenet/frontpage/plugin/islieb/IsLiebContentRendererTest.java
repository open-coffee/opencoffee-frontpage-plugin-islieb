package rocks.coffeenet.frontpage.plugin.islieb;

import org.junit.Ignore;
import org.junit.Test;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IsLiebContentRendererTest {

    @Test
    public void ensureFeedEntryRenderingIsEmptyString() {
        TemplateEngine templateEngine = mock(TemplateEngine.class);
        IsLiebContentRenderer renderer = new IsLiebContentRenderer(templateEngine);
        String actual = renderer.render(emptyList());
        assertThat(actual).isEmpty();
    }

    @Test
    @Ignore("TemplateEngine#process is final. waiting for Thymeleaf3 and ITemplateEngine")
    public void ensureFeedEntryRendering() {
        TemplateEngine templateEngine = mock(TemplateEngine.class);
        when(templateEngine.process(anyString(), any(Context.class))).thenReturn("rendered content");

        final List<ComicDTO> givenComicDTOs = Arrays.asList(
            createComicDTO("comic-1", createImageDTO("image-1", "image-alt-1")),
            createComicDTO("comic-2", createImageDTO("image-2", "image-alt-2"))
        );

        final Context expectedContext = new Context();
        expectedContext.setVariable("comics", givenComicDTOs);

        IsLiebContentRenderer renderer = new IsLiebContentRenderer(templateEngine);
        String actual = renderer.render(givenComicDTOs);

        verify(templateEngine).process("image", expectedContext);
        assertThat(actual).isEqualTo("rendered content");
    }

    private static ComicDTO createComicDTO(String title, ImageDTO imageDTO) {
        return new ComicDTO(title, imageDTO);
    }

    private static ImageDTO createImageDTO(String src, String alt) {
        return new ImageDTO(src, alt);
    }
}
