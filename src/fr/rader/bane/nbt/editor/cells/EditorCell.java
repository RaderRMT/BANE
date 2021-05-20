package fr.rader.bane.nbt.editor.cells;

import javax.swing.tree.DefaultMutableTreeNode;

public abstract class EditorCell extends DefaultMutableTreeNode {

    private String iconName;
    private String cellName;

    public EditorCell() {
    }

    public String getCellName() {
        return cellName;
    }

    public void setCellName(String name) {
        this.cellName = name;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    /**
     * Invoke a name editor, this will allow the user
     * to edit a cell's name.
     * @return {@code true} if the name was changed,
     *         {@code false} otherwise.
     */
    public abstract boolean invokeNameEditor();

    /**
     * Invoke a value editor, this will allow the user
     * to edit a cell's value.
     * @return {@code true} if the value was changed,
     *         {@code false} otherwise.
     */
    public abstract boolean invokeValueEditor();
}
