package fr.rader.bane.nbt.editor.cells;

public class ListCell extends EditorCell {

    private byte childrenID;

    public ListCell(String cellName) {
        setCellName(cellName);
        setIconName("list");

        this.setAllowsChildren(true);
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
