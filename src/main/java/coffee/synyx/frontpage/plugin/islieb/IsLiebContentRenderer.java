package coffee.synyx.frontpage.plugin.islieb;

import org.springframework.stereotype.Component;

@Component
class IsLiebContentRenderer {

    String render(IsLiebRssFeedEntry entry) {
        String containerStyle = "display:flex;justify-content:center;height:100%;";
        String img = prepareImage(entry);
        return String.format("<div style=\"%s\">%s</div>", containerStyle, img);
    }

    private static String prepareImage(IsLiebRssFeedEntry entry) {
        return entry.getImage()
            .removeAttr("class")
            .attr("style","max-height:100%;width:auto;")
            .outerHtml();
    }
}
