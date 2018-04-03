package coffee.synyx.frontpage.plugin.islieb;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
    IsLiebPlugin.class,
    IsLiebRssFeedReader.class,
    IsLiebContentRenderer.class,
    IsLiebPluginConfiguration.class,
})
public class IsLiebPluginIntegrationTest {

    @Autowired
    private IsLiebPlugin isLiebPlugin;

    @MockBean
    private SyndFeedXmlFactory syndFeedXmlFactory;

    @Test
    public void ensureTitle() {
        assertThat(isLiebPlugin.title()).isEmpty();
    }

    @Test
    public void ensureContent() throws Exception {
        SyndFeedImpl syndFeed = new SyndFeedImpl();
        syndFeed.setEntries(Arrays.asList(
            entry(content(img("image-a", "alt-text-1")), content("not-used-image")),
            entry(content(img("image-b", "alt-text-2"))),
            entry(content(img("image-c", "alt-text-3")))
        ));

        when(syndFeedXmlFactory.build(any())).thenReturn(syndFeed);

        String content = isLiebPlugin.content();
        assertThat(content).isEqualTo(
            "<link rel=\"stylesheet prefetch\" href=\"https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css\" />\n" +
            "<div class=\"islieb-wrapper\">\n" +
            "  <input checked=\"checked\" type=\"radio\" name=\"islieb-slider\" id=\"islieb-slide-1\" />\n" +
            "  <input type=\"radio\" name=\"islieb-slider\" id=\"islieb-slide-2\" />\n" +
            "  <input type=\"radio\" name=\"islieb-slider\" id=\"islieb-slide-3\" />\n" +
            "  <div class=\"islieb-slider-wrapper\">\n" +
            "    <div class=\"islieb-slider-wrapper--inner\">\n" +
            "      <article>\n" +
            "        <h3 class=\"islieb-comic--title\">\n" +
            "          <a href=\"image-a\"></a>\n" +
            "        </h3>\n" +
            "        <div class=\"islieb-comic--content\">\n" +
            "          <img src=\"image-a\" alt=\"alt-text-1\" />\n" +
            "        </div>\n" +
            "      </article>\n" +
            "      <article>\n" +
            "        <h3 class=\"islieb-comic--title\">\n" +
            "          <a href=\"image-b\"></a>\n" +
            "        </h3>\n" +
            "        <div class=\"islieb-comic--content\">\n" +
            "          <img src=\"image-b\" alt=\"alt-text-2\" />\n" +
            "        </div>\n" +
            "      </article>\n" +
            "      <article>\n" +
            "        <h3 class=\"islieb-comic--title\">\n" +
            "          <a href=\"image-c\"></a>\n" +
            "        </h3>\n" +
            "        <div class=\"islieb-comic--content\">\n" +
            "          <img src=\"image-c\" alt=\"alt-text-3\" />\n" +
            "        </div>\n" +
            "      </article>\n" +
            "    </div>\n" +
            "  </div>\n" +
            "  <div class=\"islieb-slider-dot-control\">\n" +
            "    <label for=\"islieb-slide-1\"></label>\n" +
            "    <label for=\"islieb-slide-2\"></label>\n" +
            "    <label for=\"islieb-slide-3\"></label>\n" +
            "  </div>\n" +
            "</div>\n" +
            "<style>\n" +
            "  /* kudos: https://codepen.io/trungk18/pen/EydyoL */\n" +
            "  /*---- NUMBER OF SLIDE CONFIGURATION ----*/\n" +
            "  .islieb-wrapper {\n" +
            "    width: 100%;\n" +
            "    height: 100%;\n" +
            "    position: relative;\n" +
            "  }\n" +
            "\n" +
            "  .islieb-wrapper input {\n" +
            "    display: none;\n" +
            "  }\n" +
            "\n" +
            "  .islieb-slider-wrapper--inner {\n" +
            "    width: 300%;\n" +
            "    height: 100%;\n" +
            "    line-height: 0;\n" +
            "  }\n" +
            "\n" +
            "  .islieb-wrapper article {\n" +
            "    width: calc(100% / 3);\n" +
            "    height: 100%;\n" +
            "    float: left;\n" +
            "    position: relative;\n" +
            "    text-align: center;\n" +
            "  }\n" +
            "\n" +
            "  .islieb-wrapper article img {\n" +
            "    margin-top: 2rem;\n" +
            "    height: calc(100% - 2rem);\n" +
            "    width: auto;\n" +
            "  }\n" +
            "\n" +
            "  .islieb-comic--content {\n" +
            "    height: 100%;\n" +
            "  }\n" +
            "\n" +
            "  /*---- SET UP CONTROL ----*/\n" +
            "  .islieb-slider-dot-control {\n" +
            "    position: absolute;\n" +
            "    width: 100%;\n" +
            "    height: 2rem;\n" +
            "    line-height: 2rem;\n" +
            "    bottom: 0;\n" +
            "    text-align: center;\n" +
            "  }\n" +
            "\n" +
            "  .islieb-slider-dot-control label {\n" +
            "    cursor: pointer;\n" +
            "    border-radius: 5px;\n" +
            "    display: inline-block;\n" +
            "    width: 10px;\n" +
            "    height: 10px;\n" +
            "    background: #bbb;\n" +
            "    -webkit-transition: all 0.3s;\n" +
            "    -moz-transition: all 0.3s;\n" +
            "    transition: all 0.3s;\n" +
            "  }\n" +
            "\n" +
            "  .islieb-slider-dot-control label:hover {\n" +
            "    background: #ccc;\n" +
            "    border-color: #777;\n" +
            "  }\n" +
            "\n" +
            "  /* Info Box */\n" +
            "  .islieb-comic--title {\n" +
            "    position: absolute;\n" +
            "    top: 0;\n" +
            "    left: 0;\n" +
            "    height: 2rem;\n" +
            "    line-height: 2rem;\n" +
            "    opacity: 0;\n" +
            "    margin: 0;\n" +
            "    padding: 0;\n" +
            "    font-weight: 400;\n" +
            "    font-size: 1rem;\n" +
            "    -webkit-transition: all 1000ms ease-out 600ms;\n" +
            "    -moz-transition: all 1000ms ease-out 600ms;\n" +
            "    transition: all 1000ms ease-out 600ms;\n" +
            "  }\n" +
            "\n" +
            "  .islieb-comic--title a {\n" +
            "    color: inherit;\n" +
            "    text-decoration: none;\n" +
            "  }\n" +
            "\n" +
            "  /* Slider Styling */\n" +
            "  .islieb-slider-wrapper {\n" +
            "    width: 100%;\n" +
            "    height: calc(100% - 2rem);\n" +
            "    overflow: hidden;\n" +
            "    -webkit-transform: translateZ(0);\n" +
            "    -moz-transform: translateZ(0);\n" +
            "    -ms-transform: translateZ(0);\n" +
            "    -o-transform: translateZ(0);\n" +
            "    transform: translateZ(0);\n" +
            "    -webkit-transition: all 500ms ease-out;\n" +
            "    -moz-transition: all 500ms ease-out;\n" +
            "    transition: all 500ms ease-out;\n" +
            "  }\n" +
            "\n" +
            "  .islieb-slider-wrapper .islieb-slider-wrapper--inner {\n" +
            "    -webkit-transform: translateZ(0);\n" +
            "    -moz-transform: translateZ(0);\n" +
            "    -ms-transform: translateZ(0);\n" +
            "    -o-transform: translateZ(0);\n" +
            "    transform: translateZ(0);\n" +
            "    -webkit-transition: all 800ms cubic-bezier(0.77, 0, 0.175, 1);\n" +
            "    -moz-transition: all 800ms cubic-bezier(0.77, 0, 0.175, 1);\n" +
            "    transition: all 800ms cubic-bezier(0.77, 0, 0.175, 1);\n" +
            "  }\n" +
            "\n" +
            "  /*---- SET POSITION FOR SLIDE ----*/\n" +
            "  #islieb-slide-1:checked ~ .islieb-slider-dot-control label:nth-child(1),\n" +
            "  #islieb-slide-2:checked ~ .islieb-slider-dot-control label:nth-child(2),\n" +
            "  #islieb-slide-3:checked ~ .islieb-slider-dot-control label:nth-child(3) {\n" +
            "    background: #333;\n" +
            "  }\n" +
            "\n" +
            "  #islieb-slide-1:checked ~ .islieb-slider-wrapper article:nth-child(1) .islieb-comic--title,\n" +
            "  #islieb-slide-2:checked ~ .islieb-slider-wrapper article:nth-child(2) .islieb-comic--title,\n" +
            "  #islieb-slide-3:checked ~ .islieb-slider-wrapper article:nth-child(3) .islieb-comic--title {\n" +
            "    opacity: 1;\n" +
            "  }\n" +
            "\n" +
            "  #islieb-slide-1:checked ~ .islieb-slider-wrapper .islieb-slider-wrapper--inner {\n" +
            "    margin-left: 0%;\n" +
            "  }\n" +
            "\n" +
            "  #islieb-slide-2:checked ~ .islieb-slider-wrapper .islieb-slider-wrapper--inner {\n" +
            "    margin-left: -100%;\n" +
            "  }\n" +
            "\n" +
            "  #islieb-slide-3:checked ~ .islieb-slider-wrapper .islieb-slider-wrapper--inner {\n" +
            "    margin-left: -200%;\n" +
            "  }\n" +
            "</style>\n"
        );
    }

    private static SyndEntry entry(SyndContent... content) {
        SyndEntryImpl entry = new SyndEntryImpl();
        entry.setContents(Arrays.asList(content));
        return entry;
    }

    private static SyndContent content(String value) {
        SyndContentImpl content = new SyndContentImpl();
        content.setValue(value);
        return content;
    }

    private static String img(String src, String alt) {
        return String.format("<img src=\"%s\" alt=\"%s\" >", src, alt);
    }

}
