package fr.rader.bane.nbt.editor.cells;

public class CompoundCell extends EditorCell {

    public CompoundCell(String cellName) {
        setCellName(cellName);
        setIconName("compound");

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
