package cn.xdev.core.util.office.word;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by felix on 2016-06-07-0007.
 */
public class WordReplacer {
    XWPFDocument doc;
    XWPFWordExtractor we;

    public WordReplacer() {
    }

    public WordReplacer(FileInputStream input) {
        try {
            this.doc = new XWPFDocument(input);
            this.we = new XWPFWordExtractor(doc);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void replaceParagraph(List<String> oldWordList, List<String> newWordList) {
        for (int i = 0; i < oldWordList.size(); i++) {
            replaceParagraph(oldWordList.get(i), newWordList.get(i));
        }
    }

    public void replaceTable(List<String> oldWordList, List<String> newWordList) {
        for (int i = 0; i < oldWordList.size(); i++) {
            replaceTable(oldWordList.get(i), newWordList.get(i));
        }
    }

    public void replaceParagraph(String oldWord, String newWord) {
        for (XWPFParagraph p : doc.getParagraphs()) {
            for (XWPFRun r : p.getRuns()) {
                String text = replace(r.getText(0), oldWord, newWord);
                r.setText(text, 0);
            }
        }
    }

    public void replaceTable(String oldWord, String newWord) {

        for (XWPFTable tbl : doc.getTables()) {
            for (XWPFTableRow row : tbl.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph p : cell.getParagraphs()) {
                        for (XWPFRun r : p.getRuns()) {
                            String text = replace(r.getText(0), oldWord, newWord);
                            r.setText(text, 0);
                        }
                    }
                }
            }
        }
    }

    public String replace(String text, String oldWord, String newWord) {
        if (text != null && text.contains(oldWord)) {
            text = text.replace(oldWord, newWord);
        }
        return text;
    }
}
