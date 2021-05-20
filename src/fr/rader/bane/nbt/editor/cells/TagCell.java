package fr.rader.bane.nbt.editor.cells;

import fr.rader.bane.nbt.tags.TagBase;

public class TagCell extends EditorCell {

    private final TagBase associatedTag;
    private final String tagName;
    private final byte tagID;

    public TagCell(TagBase associatedTag) {
        this.associatedTag = associatedTag;
        this.tagName = associatedTag.getName();
        this.tagID = associatedTag.getTagID();

        this.setAllowsChildren(false);

        setIconName(associatedTag.getTagNameFromClass());

        if(associatedTag.getName() != null) {
            setCellName(associatedTag.getName() + ": ");
        } else {
            setCellName("");
        }

        defineName();
    }

    private void defineName() {
        switch(tagID) {
            case 1:
                this.setCellName(getCellName() + associatedTag.getAsByte());
                break;
            case 2:
                this.setCellName(getCellName() + associatedTag.getAsShort());
                break;
            case 3:
                this.setCellName(getCellName() + associatedTag.getAsInt());
                break;
            case 4:
                this.setCellName(getCellName() + associatedTag.getAsLong());
                break;
            case 5:
                this.setCellName(getCellName() + associatedTag.getAsFloat());
                break;
            case 6:
                this.setCellName(getCellName() + associatedTag.getAsDouble());
                break;
            case 8:
                this.setCellName(getCellName() + associatedTag.getAsString());
                break;
            default:
                System.out.println("[TagCell] -> (#defineName) Unknown tag ID: " + tagID);
        }
    }

    public String getTagName() {
        if(associatedTag.getName() != null) {
            return this.tagName;
        }

        return "";
    }
    public TagBase getAssociatedTag() {
        return this.associatedTag;
    }

    @Override
    public boolean invokeNameEditor() {
        return false;
    }

    @Override
    public boolean invokeValueEditor() {
        return false;
    }
}
