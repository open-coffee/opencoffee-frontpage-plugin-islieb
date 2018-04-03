package coffee.synyx.frontpage.plugin.islieb;

import org.jsoup.nodes.Element;

class JsoupElementMapper {

    private JsoupElementMapper() {
        // ok
    }

    static ImageDTO mapImageElement(Element imageElement) {
        ensureTagNameThrowOtherwise(imageElement, "img");
        return new ImageDTO(imageElement.attr("src"));
    }

    private static void ensureTagNameThrowOtherwise(Element element, String expectedTagName) {
        String tagName = element.tagName();
        if (!tagName.equals(expectedTagName)) {
            throw new IllegalArgumentException(String.format(
                "expected tagName %s but got %s", expectedTagName, tagName
            ));
        }
    }
}
