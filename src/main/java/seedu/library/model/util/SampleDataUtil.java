package seedu.library.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.library.model.Library;
import seedu.library.model.ReadOnlyLibrary;
import seedu.library.model.bookmark.*;
import seedu.library.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Library} with sample data.
 */
public class SampleDataUtil {
    public static Bookmark[] getSampleBookmarks() {
        return new Bookmark[] {
            new Bookmark(new Title("Rankers Guide"), new Progress("Chapter 40"),
                    new Genre("Modern Fantasy"), new Author("TeJe"),
                    new UrlLink("https://www.wuxiaworld.eu/chapter/the-rankers-guide-to-live-an-ordinary-life-41"),
                    getTagSet("Hunters")),
            new Bookmark(new Title("Chainsaw Man"), new Progress("Not Started"), new Genre("Action"),
                    new Author("Tatsuki Fujimoto"), new UrlLink("https://www.chainsaw-man-manga.online/"),
                    getTagSet("Gore")),
            new Bookmark(new Title("Solo Leveling"), new Progress("Chapter 120"), new Genre("Modern Fantasy"),
                    new Author("Chugong"), new UrlLink("https://sololeveling-manhwa.online/"),
                    getTagSet("Hunters", "System", "Cheats")),
            new Bookmark(new Title("Dungeon Defense"), new Progress("Chapter 40"), new Genre("Western Fantasy"),
                    new Author("Yoo Heonhwa"), new UrlLink("https://www.webnovel.com/book/dungeon-defense-(wn)_19020443805961005"),
                    getTagSet("Antihero"))
        };
    }
    public static ReadOnlyLibrary getSampleLibrary() {
        Library sampleAb = new Library();
        for (Bookmark sampleBookmark : getSampleBookmarks()) {
            sampleAb.addBookmark(sampleBookmark);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
