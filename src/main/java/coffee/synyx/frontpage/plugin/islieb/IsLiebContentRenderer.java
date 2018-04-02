package coffee.synyx.frontpage.plugin.islieb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

@Component
class IsLiebContentRenderer {

    private final TemplateEngine templateEngine;

    @Autowired
    IsLiebContentRenderer(@Qualifier("isliebTemplateEngine") TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    String render(List<ComicDTO> comicDTOs) {
        if (comicDTOs.isEmpty()) {
            return "";
        }
        Context context = new Context();
        context.setVariable("comic1", comicDTOs.get(0));
        context.setVariable("comic2", comicDTOs.get(1));
        context.setVariable("comic3", comicDTOs.get(2));
        return templateEngine.process("comics", context);
    }
}
