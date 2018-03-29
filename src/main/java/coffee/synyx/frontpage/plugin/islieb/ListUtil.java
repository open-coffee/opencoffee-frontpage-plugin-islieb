package coffee.synyx.frontpage.plugin.islieb;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

class ListUtil {

    static <T> T getFirst(List<T> list) {
        return list.get(0);
    }

    static <FROM, TO> List<TO> map(Collection<FROM> streamable, Function<FROM, TO> mapper) {
        return streamable.stream().map(mapper).collect(toList());
    }

    private ListUtil() {
        // ok
    }
}
