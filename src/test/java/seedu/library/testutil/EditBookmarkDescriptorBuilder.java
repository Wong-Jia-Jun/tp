package seedu.library.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.library.logic.commands.EditCommand.EditBookmarkDescriptor;
import seedu.library.model.bookmark.*;
import seedu.library.model.tag.Tag;

/**
 * A utility class to help with building EditBookmarkDescriptor objects.
 */
public class EditBookmarkDescriptorBuilder {

    private EditBookmarkDescriptor descriptor;

    public EditBookmarkDescriptorBuilder() {
        descriptor = new EditBookmarkDescriptor();
    }

    public EditBookmarkDescriptorBuilder(EditBookmarkDescriptor descriptor) {
        this.descriptor = new EditBookmarkDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditBookmarkDescriptor} with fields containing {@code bookmark}'s details
     */
    public EditBookmarkDescriptorBuilder(Bookmark bookmark) {
        descriptor = new EditBookmarkDescriptor();
        descriptor.setTitle(bookmark.getTitle());
        descriptor.setProgress(bookmark.getProgress());
        descriptor.setGenre(bookmark.getGenre());
        descriptor.setAuthor(bookmark.getAuthor());
        descriptor.setTags(bookmark.getTags());
    }

    /**
     * Sets the {@code Title} of the {@code EditBookmarkDescriptor} that we are building.
     */
    public EditBookmarkDescriptorBuilder withName(String title) {
        descriptor.setTitle(new Title(title));
        return this;
    }

    /**
     * Sets the {@code Progress} of the {@code EditBookmarkDescriptor} that we are building.
     */
    public EditBookmarkDescriptorBuilder withProgress(String progress) {
        descriptor.setProgress(new Progress(progress));
        return this;
    }

    /**
     * Sets the {@code Genre} of the {@code EditBookmarkDescriptor} that we are building.
     */
    public EditBookmarkDescriptorBuilder withGenre(String genre) {
        descriptor.setGenre(new Genre(genre));
        return this;
    }

    /**
     * Sets the {@code Author} of the {@code EditBookmarkDescriptor} that we are building.
     */
    public EditBookmarkDescriptorBuilder withAuthor(String author) {
        descriptor.setAuthor(new Author(author));
        return this;
    }
    public EditBookmarkDescriptorBuilder withUrl(String url) {
        descriptor.setUrl(new UrlLink(url));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditBookmarkDescriptor}
     * that we are building.
     */
    public EditBookmarkDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditBookmarkDescriptor build() {
        return descriptor;
    }
}
