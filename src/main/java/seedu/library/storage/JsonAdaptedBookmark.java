package seedu.library.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.library.commons.exceptions.IllegalValueException;
import seedu.library.model.bookmark.*;
import seedu.library.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Bookmark}.
 */
class JsonAdaptedBookmark {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Bookmark's %s field is missing!";

    private final String title;
    private final String progress;
    private final String genre;
    private final String author;
    private final String url;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedBookmark} with the given bookmark details.
     */
    @JsonCreator
    public JsonAdaptedBookmark(@JsonProperty("title") String title, @JsonProperty("progress") String progress,
                               @JsonProperty("genre") String genre, @JsonProperty("author") String author,
                               @JsonProperty("url") String url,
                               @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.title = title;
        this.progress = progress;
        this.genre = genre;
        this.author = author;
        this.url = url;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Bookmark} into this class for Jackson use.
     */
    public JsonAdaptedBookmark(Bookmark source) {
        title = source.getTitle().value;
        progress = source.getProgress().value;
        genre = source.getGenre().value;
        author = source.getAuthor().value;
        url = source.getUrl().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        //TODO
    }

    /**
     * Converts this Jackson-friendly adapted bookmark object into the model's {@code Bookmark} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted bookmark.
     */
    public Bookmark toModelType() throws IllegalValueException {
        final List<Tag> bookmarkTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            bookmarkTags.add(tag.toModelType());
        }

        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        final Title modelTitle = new Title(title);

        if (progress == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Progress.class.getSimpleName()));
        }
        if (!Progress.isValidProgress(progress)) {
            throw new IllegalValueException(Progress.MESSAGE_CONSTRAINTS);
        }
        final Progress modelProgress = new Progress(progress);

        if (genre == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Genre.class.getSimpleName()));
        }
        if (!Genre.isValidGenre(genre)) {
            throw new IllegalValueException(Genre.MESSAGE_CONSTRAINTS);
        }
        final Genre modelGenre = new Genre(genre);

        if (author == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Author.class.getSimpleName()));
        }
        if (!Author.isValidAuthor(author)) {
            throw new IllegalValueException(Author.MESSAGE_CONSTRAINTS);
        }
        final Author modelAuthor = new Author(author);
        if (!UrlLink.isValidUrlLink(url)) {
            throw new IllegalValueException(UrlLink.MESSAGE_CONSTRAINTS);
        }
        final UrlLink modelUrl = new UrlLink(url);

        final Set<Tag> modelTags = new HashSet<>(bookmarkTags);
        return new Bookmark(modelTitle, modelProgress, modelGenre, modelAuthor, modelUrl, modelTags);
    }

}
