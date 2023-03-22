package seedu.library.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.library.logic.parser.CliSyntax.*;
import static seedu.library.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.library.commons.core.index.Index;
import seedu.library.logic.commands.exceptions.CommandException;
import seedu.library.model.Library;
import seedu.library.model.Model;
import seedu.library.model.bookmark.Bookmark;
import seedu.library.model.bookmark.TitleContainsKeywordsPredicate;
import seedu.library.testutil.EditBookmarkDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_TITLE_AMY = "Amy Bee";
    public static final String VALID_TITLE_BOB = "Bob Choo";
    public static final String VALID_PROGRESS_AMY = "11111111";
    public static final String VALID_PROGRESS_BOB = "22222222";
    public static final String VALID_GENRE_AMY = "Amy";
    public static final String VALID_GENRE_BOB = "Bob";
    public static final String VALID_AUTHOR_AMY = "Block 312, Amy Street 1";
    public static final String VALID_AUTHOR_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_URL_AMY = "https://www.abc.com";
    public static final String VALID_URL_BOB = "https://www.def.com";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String TITLE_DESC_AMY = " " + PREFIX_TITLE + VALID_TITLE_AMY;
    public static final String TITLE_DESC_BOB = " " + PREFIX_TITLE + VALID_TITLE_BOB;
    public static final String PROGRESS_DESC_AMY = " " + PREFIX_PROGRESS + VALID_PROGRESS_AMY;
    public static final String PROGRESS_DESC_BOB = " " + PREFIX_PROGRESS + VALID_PROGRESS_BOB;
    public static final String GENRE_DESC_AMY = " " + PREFIX_GENRE + VALID_GENRE_AMY;
    public static final String GENRE_DESC_BOB = " " + PREFIX_GENRE + VALID_GENRE_BOB;
    public static final String AUTHOR_DESC_AMY = " " + PREFIX_AUTHOR + VALID_AUTHOR_AMY;
    public static final String AUTHOR_DESC_BOB = " " + PREFIX_AUTHOR + VALID_AUTHOR_BOB;
    public static final String URL_DESC_AMY = " " + PREFIX_URLLINK + VALID_URL_AMY;
    public static final String URL_DESC_BOB = " " + PREFIX_URLLINK + VALID_URL_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_TITLE_DESC = " " + PREFIX_TITLE + "James&"; // '&' not allowed in names
    public static final String INVALID_PROGRESS_DESC = " " + PREFIX_PROGRESS + "911*"; // '*' not allowed in progress
    public static final String INVALID_GENRE_DESC = " " + PREFIX_GENRE;
    public static final String INVALID_AUTHOR_DESC = " " + PREFIX_AUTHOR; // empty string not allowed for addresses
    public static final String INVALID_URL_DESC = "abc.com" + PREFIX_AUTHOR; // need protocol in fron e.g https://
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditBookmarkDescriptor DESC_AMY;
    public static final EditCommand.EditBookmarkDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditBookmarkDescriptorBuilder().withName(VALID_TITLE_AMY)
                .withProgress(VALID_PROGRESS_AMY).withGenre(VALID_GENRE_AMY).withAuthor(VALID_AUTHOR_AMY)
      //          .withUrl(VALID_URL_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditBookmarkDescriptorBuilder().withName(VALID_TITLE_BOB)
                .withProgress(VALID_PROGRESS_BOB).withGenre(VALID_GENRE_BOB).withAuthor(VALID_AUTHOR_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the library, filtered bookmark list and selected bookmark in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Library expectedLibrary = new Library(actualModel.getLibrary());
        List<Bookmark> expectedFilteredList = new ArrayList<>(actualModel.getFilteredBookmarkList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedLibrary, actualModel.getLibrary());
        assertEquals(expectedFilteredList, actualModel.getFilteredBookmarkList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the bookmark at the given {@code targetIndex} in the
     * {@code model}'s library.
     */
    public static void showBookmarkAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredBookmarkList().size());

        Bookmark bookmark = model.getFilteredBookmarkList().get(targetIndex.getZeroBased());
        final String[] splitName = bookmark.getTitle().value.split("\\s+");
        model.updateFilteredBookmarkList(new TitleContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredBookmarkList().size());
    }

}
