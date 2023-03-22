package seedu.library.logic.commands;

import seedu.library.logic.commands.exceptions.CommandException;
import seedu.library.model.Model;
import java.util.List;
import seedu.library.commons.core.Messages;
import seedu.library.commons.core.index.Index;
import seedu.library.logic.commands.exceptions.CommandException;
import seedu.library.model.Model;
import seedu.library.model.bookmark.Bookmark;

import static java.util.Objects.requireNonNull;

public class GoToCommand extends Command{
    public static final String COMMAND_WORD = "goto";

    public static final String MESSAGE_GOTO_URL_SUCCESS = "Opened Url: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Opens the url of bookmark identified by the index number used in the displayed bookmark list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    private final Index targetIndex;
    public GoToCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Bookmark> lastShownList = model.getFilteredBookmarkList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOKMARK_DISPLAYED_INDEX);
        }
        //TODO
        // call get url method here
        Bookmark targetUrl = lastShownList.get(targetIndex.getZeroBased());
        return new CommandResult(MESSAGE_GOTO_URL_SUCCESS);
    }

}
