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

    private String pattern = "[{index}]: ";

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

        panel.add(scrollPane, BorderLayout.CENTER);
    }

    private EditorCell buildTree(TagBase base) {
        // base must either be a list or a compound. if it's not, we throw a IllegalArgumentException
        if(!(base instanceof TagCompound)
                && !(base instanceof TagList)) {
            throw new IllegalArgumentException("Tag must either be TagCompound or TagList. Got " + base.getClass().getSimpleName() + " instead");
        }

        EditorCell out = getEditorCell(base);

        if(out == null) {
            throw new IllegalStateException("[NBTEditor] -> [#buildTree(base)] out is null; base is " + base.getClass().getSimpleName());
        }

        List<TagBase> bases = getChildrenTags(base);
        for(TagBase tag : bases) {
            if(tag.getTagID() == TagList.TAG_ID
                    || tag.getTagID() == TagCompound.TAG_ID) {
                out.add(buildTree(tag));
            } else if(tag.getTagID() == TagByteArray.TAG_ID
                    || tag.getTagID() == TagIntArray.TAG_ID
                    || tag.getTagID() == TagLongArray.TAG_ID) {
                out.add(buildArray(tag));
            } else {
                out.add(new TagCell(tag));
            }
        }

        return out;
    }

    private EditorCell buildArray(TagBase base) {
        if(!(base instanceof TagByteArray)
                && !(base instanceof TagIntArray)
                && !(base instanceof TagLongArray)) {
            throw new IllegalArgumentException("Tag must either be TagByteArray, TagIntArray or TagLongArray. Got " + base.getClass().getSimpleName() + " instead");
        }

        EditorCell out = getEditorCell(base);

        if(base instanceof TagByteArray) {
            TagByteArray byteArray = base.getAsTagByteArray();
            for(int i = 0; i < byteArray.size(); i++) {
                out.add(new ArrayValueCell("byte", pattern.replaceAll("\\{index}", Integer.toUnsignedString(i)) + byteArray.get(i)));
            }
        }

        if(base instanceof TagIntArray) {
            TagIntArray intArray = base.getAsTagIntArray();
            for(int i = 0; i < intArray.size(); i++) {
                out.add(new ArrayValueCell("int", pattern.replaceAll("\\{index}", Integer.toUnsignedString(i)) + intArray.get(i)));
            }
        }

        if(base instanceof TagLongArray) {
            TagLongArray longArray = base.getAsTagLongArray();
            for (int i = 0; i < longArray.size(); i++) {
                out.add(new ArrayValueCell("long", pattern.replaceAll("\\{index}", Integer.toUnsignedString(i)) + longArray.get(i)));
            }
        }

        return out;
    }

    private List<TagBase> getChildrenTags(TagBase tag) {
        if(tag instanceof TagCompound) {
            return tag.getAsTagCompound().getTags();
        } else {
            return (List<TagBase>) tag.getAsUnknownList().getTags();
        }
    }

    private EditorCell getEditorCell(TagBase tag) {
        String name = tag.getName();
        if(name == null) {
            name = "";
        } else {
            name += ": ";
        }

        switch(tag.getTagID()) {
            case TagCompound.TAG_ID:
                return new CompoundCell(name + tag.getAsTagCompound().getTags().size() + " entries");
            case TagList.TAG_ID:
                return new ListCell(name + tag.getAsUnknownList().getTags().size() + " entries");
            case TagByteArray.TAG_ID:
                return new ArrayCell(name + tag.getAsTagByteArray().size() + " bytes", "byte");
            case TagIntArray.TAG_ID:
                return new ArrayCell(name + tag.getAsTagIntArray().size() + " integers", "int");
            case TagLongArray.TAG_ID:
                return new ArrayCell(name + tag.getAsTagLongArray().size() + " long integers", "long");
        }

        return null;
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
