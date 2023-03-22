package seedu.library.model.bookmark;

import static java.util.Objects.requireNonNull;
import static seedu.library.commons.util.AppUtil.checkArgument;

public class UrlLink {
    public static final String MESSAGE_CONSTRAINTS =
            "UrlLink should be in valid format for example: http://www.example.com/index.html ";
    public static final String VALIDATION_REGEX = "^$|^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
    public final String value;

    /**
     * Constructs a {@code Genre}.
     *
     * @param urlLink A valid genre.
     */
    public UrlLink(String urlLink) {
        requireNonNull(urlLink);
        checkArgument(isValidUrlLink(urlLink), MESSAGE_CONSTRAINTS);
        value = urlLink;
    }


    /**
     * Returns true if a given string is a valid genre.
     */
    public static boolean isValidUrlLink(String test) {
        return test.matches(VALIDATION_REGEX);
    }
    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Genre // instanceof handles nulls
                && value.equals(((Genre) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }


}

