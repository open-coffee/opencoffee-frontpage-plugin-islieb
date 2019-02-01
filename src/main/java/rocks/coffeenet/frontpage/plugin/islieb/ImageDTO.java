package rocks.coffeenet.frontpage.plugin.islieb;

import java.util.Objects;

class ImageDTO {

    private final String src;
    private final String alt;

    ImageDTO(String src, String alt) {
        this.src = src;
        this.alt = alt;
    }

    public String getSrc() {
        return this.src;
    }

    public String getAlt() {
        return alt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageDTO imageDTO = (ImageDTO) o;
        return Objects.equals(src, imageDTO.src) &&
            Objects.equals(alt, imageDTO.alt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(src, alt);
    }
}
