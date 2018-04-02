package coffee.synyx.frontpage.plugin.islieb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
class IsLiebContentRenderer {

    private final TemplateEngine templateEngine;

    @Autowired
    IsLiebContentRenderer(@Qualifier("isliebTemplateEngine") TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    String render(ComicDTO comicDTO) {
        Context context = new Context();
        context.setVariable("comic", comicDTO);
        return templateEngine.process("comic", context);
    }
}
