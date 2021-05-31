package fr.rader.bane.nbt.editor.cells;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

public class EditorCellRenderer extends DefaultTreeCellRenderer {

    public EditorCellRenderer() {
        setBackgroundSelectionColor(new Color(0, 120, 215));
        setOpaque(true);
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        this.selected = selected;
        this.hasFocus = hasFocus;

        setSelectionColors(selected);

        if(value instanceof EditorCell) {
            EditorCell cell = ((EditorCell) value);

            URL imageURL = this.getClass().getResource("/images/" + cell.getIconName() + ".png");
            if(imageURL == null) {
                throw new IllegalStateException("Image `" + cell.getIconName() + ".png` does not exist");
            }

            setIcon(new ImageIcon(imageURL));
            setText("<html>" + cell.getCellName() + "</html>");
        }

        return this;
    }

    private void setSelectionColors(boolean selected) {
        if(selected) {
            setBackground(getBackgroundSelectionColor());
            setForeground(getTextSelectionColor());
        } else {
            setBackground(getBackgroundNonSelectionColor());
            setForeground(getTextNonSelectionColor());
        }
    }
}
