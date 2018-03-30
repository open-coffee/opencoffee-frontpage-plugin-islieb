package coffee.synyx.frontpage.plugin.islieb;

import org.springframework.stereotype.Component;

@Component
class IsLiebContentRenderer {

    String render(IsLiebRssFeedEntry entry) {
        return entry.getValue();
    }
}
