package fr.rader.bane.nbt.editor;

import fr.rader.bane.nbt.editor.cells.*;
import fr.rader.bane.nbt.tags.*;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.net.URL;
import java.util.List;

public class NBTEditor {

    // GUI-related variables
    private JPanel panel;
    private JToolBar toolbar;
    private JScrollPane scrollPane;
    // Tag Tree
    private JTree tagTree;
    private DefaultTreeModel model;

    // Other variables
    private TagCompound serializedNBT;
    private TagCompound nbt;

    public NBTEditor() {
        //buildToolbar();
        buildGUI();

        // change the cell renderer, so we can change how cells look,
        // and give them an icon
        tagTree.setCellRenderer(new EditorCellRenderer());
    }

    /**
     * Refresh the NBT Tree when editing
     */
    public void reload() {

    }

    /**
     * Load a compound to the tree. Must call the {@link #reload() } method
     * for the changes to be applied.
     * @param nbt The nbt to load
     */
    public void loadNBT(TagCompound nbt) {
        this.nbt = nbt;
    }

    /**
     * Show the GUI to the user
     */
    public void invokeEditor() {
        if(nbt == null) {
            throw new IllegalStateException("No NBT were loaded!");
        }

        // todo: show the interface
        JDialog dialog = new JDialog(null, "NBT Editor", Dialog.ModalityType.DOCUMENT_MODAL);

        model = new DefaultTreeModel(buildTree(nbt));
        tagTree.setModel(model);
        model.reload();

        dialog.setContentPane(panel);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        dialog.setSize(640, 460);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        dialog.dispose();
    }

    private void buildToolbar() {
        toolbar.setFloatable(false);

        addButtons(toolbar);
    }

    private void buildGUI() {
        panel = new JPanel();

        tagTree = new JTree();
        scrollPane = new JScrollPane(tagTree);

        panel.add(scrollPane);
    }

    private EditorCell buildTree(TagBase base) {
        // base must either be a list or a compound. if it's not, we throw a IllegalArgumentException
        if(!(base instanceof TagCompound) && !(base instanceof TagList)) {
            throw new IllegalArgumentException("Tag must either be TagCompound or TagList. Got " + base.getClass().getSimpleName() + " instead");
        }

        EditorCell out = getEditorCell(base);

        List<TagBase> bases = getChildrenTags(base);
        for(TagBase tag : bases) {
            if(tag.getTagID() == 9 || tag.getTagID() == 10) {
                out.add(buildTree(tag));
            } else {
                out.add(new TagCell(tag));
            }
        }

        return out;
    }

    private EditorCell buildArray(TagBase base) {
        // todo
        return null;
    }

    private List<TagBase> getChildrenTags(TagBase tag) {
        if(tag instanceof TagCompound) {
            return tag.getAsTagCompound().getTags();
        } else {
            return (List<TagBase>) tag.getAsUnknownList().getTags();
        }
    }

    private EditorCell getEditorCell(TagBase tag) {
        if(tag instanceof TagCompound) {
            return new CompoundCell(tag.getName() + ": " + tag.getAsTagCompound().getTags().size() + " entries");
        }

        return new ListCell(tag.getName() + ": " + tag.getAsUnknownList().getTags().size() + " entries");
    }

    private void addButtons(JToolBar toolbar) {
        JButton button;

        button = makeButton("save", "Save");
        button.addActionListener(e -> {

        });
    }

    private JButton makeButton(String icon, String tooltip) {
        JButton button = new JButton();

        button.setToolTipText(tooltip);

        URL image = getClass().getResource("images/" + icon + ".png");
        if(image == null) {
            button.setText(tooltip);
        } else {
            button.setIcon(new ImageIcon(image, tooltip));
        }

        return button;
    }
}
