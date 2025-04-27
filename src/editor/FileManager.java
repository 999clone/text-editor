package editor;

import javax.swing.*;
import java.io.*;

public class FileManager {

    public static void openFile(TextEditor textEditor, JTextArea textArea) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            textEditor.currentFile = file;
            try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textArea.read(reader, null);
            }catch (IOException e){
                e.printStackTrace();

            }
        }
    }

    public static void saveFile(TextEditor textEditor, JTextArea textArea){
        if (textEditor.currentFile == null){
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                textEditor.currentFile = file;
                try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    textArea.write(writer);

                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        else{
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(textEditor.currentFile))) {
                textArea.write(writer);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void newFile(TextEditor textEditor, JTextArea textArea) {
        textArea.setText("");
        textEditor.currentFile = null;
    }
}
