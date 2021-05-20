package fr.rader.bane.nbt.editor.cells;

public class ArrayCell extends EditorCell {

    private final String type;

    public ArrayCell(String cellName, String type) {
        this.type = type;

        setCellName(cellName);
        setIconName(type + "Array");

        this.setAllowsChildren(true);
    }

    public String getType() {
        return type;
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
