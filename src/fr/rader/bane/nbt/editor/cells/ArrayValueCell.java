package fr.rader.bane.nbt.editor.cells;

public class ArrayValueCell extends EditorCell {

    public ArrayValueCell(String icon, String name) {
        setIconName(icon);
        setCellName(name);
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
