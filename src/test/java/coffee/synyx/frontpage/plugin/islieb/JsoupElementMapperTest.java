package coffee.synyx.frontpage.plugin.islieb;

import org.jsoup.nodes.Element;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JsoupElementMapperTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureMapImageElementThrowsForDiv() {
        Element div = new Element("div");
        JsoupElementMapper.mapImageElement(div);
    }

    @Test
    public void ensureMapImageElement() {
        ImageDTO expectedImageDTO = new ImageDTO("image-src");

        Element img = new Element("img");
        img.attr("src", "image-src");
        ImageDTO imageDTO = JsoupElementMapper.mapImageElement(img);
        assertThat(imageDTO).isEqualTo(expectedImageDTO);
    }
}
