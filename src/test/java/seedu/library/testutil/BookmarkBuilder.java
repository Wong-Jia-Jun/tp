package seedu.library.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.library.model.bookmark.Author;
import seedu.library.model.bookmark.Bookmark;
import seedu.library.model.bookmark.Genre;
import seedu.library.model.bookmark.Progress;
import seedu.library.model.bookmark.Title;
import seedu.library.model.tag.Tag;
import seedu.library.model.bookmark.UrlLink;
import seedu.library.model.util.SampleDataUtil;

/**
 * A utility class to help with building Bookmark objects.
 */
public class BookmarkBuilder {

    public static final String DEFAULT_TITLE = "Amy Bee";
    public static final String DEFAULT_PROGRESS = "85355255";
    public static final String DEFAULT_GENRE = "amy@gmail.com";
    public static final String DEFAULT_AUTHOR = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_URL = "https://www.abc.com";


    private Title title;
    private Progress progress;
    private Genre genre;
    private Author author;
    private UrlLink url;
    private Set<Tag> tags;

    /**
     * Creates a {@code BookmarkBuilder} with the default details.
     */
    public BookmarkBuilder() {
        title = new Title(DEFAULT_TITLE);
        progress = new Progress(DEFAULT_PROGRESS);
        genre = new Genre(DEFAULT_GENRE);
        author = new Author(DEFAULT_AUTHOR);
//        url = new UrlLink(DEFAULT_URL);
        tags = new HashSet<>();
    }

    /**
     * Initializes the BookmarkBuilder with the data of {@code bookmarkToCopy}.
     */
    public BookmarkBuilder(Bookmark bookmarkToCopy) {
        title = bookmarkToCopy.getTitle();
        progress = bookmarkToCopy.getProgress();
        genre = bookmarkToCopy.getGenre();
        author = bookmarkToCopy.getAuthor();
//        url = bookmarkToCopy.getUrl();
        tags = new HashSet<>(bookmarkToCopy.getTags());
    }

    /**
     * Sets the {@code Title} of the {@code Bookmark} that we are building.
     */
    public BookmarkBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Bookmark} that we are building.
     */
    public BookmarkBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Author} of the {@code Bookmark} that we are building.
     */
    public BookmarkBuilder withAuthor(String author) {
        this.author = new Author(author);
        return this;
    }

    /**
     * Sets the {@code Progress} of the {@code Bookmark} that we are building.
     */
    public BookmarkBuilder withProgress(String progress) {
        this.progress = new Progress(progress);
        return this;
    }

    /**
     * Sets the {@code Genre} of the {@code Bookmark} that we are building.
     */
    public BookmarkBuilder withGenre(String genre) {
        this.genre = new Genre(genre);
        return this;
    }
    /**
     * Sets the {@code Genre} of the {@code Bookmark} that we are building.
     */
    public BookmarkBuilder withUrl(String url) {
        this.url = new UrlLink(url);
        return this;
    }

    public Bookmark build() {
        return new Bookmark(title, progress, genre, author, url, tags);
    }

}
