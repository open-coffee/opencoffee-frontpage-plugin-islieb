package coffee.synyx.frontpage.plugin.islieb;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

class ListUtil {

    static <T> Optional<T> getFirst(List<T> list) {
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }

    static <FROM, TO> List<TO> map(Collection<FROM> streamable, Function<FROM, TO> mapper) {
        return streamable.stream().map(mapper).collect(toList());
    }

    private ListUtil() {
        // ok
    }
}
