package org.nodeclipse.enide.editors.jade.editors;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.source.DefaultCharacterPairMatcher;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;


/**
 * copied from NodeclipseNodejsEditor
 * TODO base classes, color theme support
 * 
 * @author Paul Verest
 */
public class JadeEditor extends TextEditor {

    public static final String EDITOR_ID = "org.nodeclipse.enide.editors.notxml.editors.JadeEditor";
    public static final String RULER_CONTEXT = EDITOR_ID + ".ruler";
    public final static String EDITOR_MATCHING_BRACKETS = "matchingBrackets";
    public final static String EDITOR_MATCHING_BRACKETS_COLOR = "matchingBracketsColor";

    private DefaultCharacterPairMatcher matcher;

    public JadeEditor() {
        setSourceViewerConfiguration(new NodeSourceViewerConfiguration());
    }

    @Override
    protected void initializeEditor() {
        super.initializeEditor();
        setRulerContextMenuId(RULER_CONTEXT);
        setDocumentProvider(new NodeDocumentProvider());
    }

    @Override
    protected void configureSourceViewerDecorationSupport(SourceViewerDecorationSupport support) {
        super.configureSourceViewerDecorationSupport(support);

        char[] matchChars = { '(', ')', '[', ']', '{', '}', '<', '>' }; // which brackets
                                                              // to match
        matcher = new DefaultCharacterPairMatcher(matchChars, IDocumentExtension3.DEFAULT_PARTITIONING);
        support.setCharacterPairMatcher(matcher);
        support.setMatchingCharacterPainterPreferenceKeys(EDITOR_MATCHING_BRACKETS, EDITOR_MATCHING_BRACKETS_COLOR);

        // Enable bracket highlighting in the preference store
        IPreferenceStore store = getPreferenceStore();
        store.setDefault(EDITOR_MATCHING_BRACKETS, true);
        store.setDefault(EDITOR_MATCHING_BRACKETS_COLOR, "128,128,128");
    }

}

