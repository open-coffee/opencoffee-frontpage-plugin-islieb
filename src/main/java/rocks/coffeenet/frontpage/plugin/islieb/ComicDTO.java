package rocks.coffeenet.frontpage.plugin.islieb;

import java.util.Objects;

class ComicDTO {

    private final String title;
    private final ImageDTO image;

    ComicDTO(String title, ImageDTO image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public ImageDTO getImage() {
        return image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComicDTO comicDTO = (ComicDTO) o;
        return Objects.equals(title, comicDTO.title) &&
            Objects.equals(image, comicDTO.image);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, image);
    }
}
