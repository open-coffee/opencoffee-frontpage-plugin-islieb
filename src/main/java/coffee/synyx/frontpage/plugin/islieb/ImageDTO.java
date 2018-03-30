package coffee.synyx.frontpage.plugin.islieb;

import java.util.Objects;

class ImageDTO {

    private final String src;

    ImageDTO(String src) {
        this.src = src;
    }

    public String getSrc() {
        return this.src;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageDTO that = (ImageDTO) o;
        return Objects.equals(src, that.src);
    }

    @Override
    public int hashCode() {
        return Objects.hash(src);
    }
}
