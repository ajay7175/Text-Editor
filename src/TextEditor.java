import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;
    JMenuBar menuBar;
    JMenu file, edit;
    JMenuItem newFile, saveFile, openFile;
    JMenuItem cut, copy, paste, selectAll, close;
    JTextArea textArea;
    TextEditor(){
        frame = new JFrame();
        //init textarea
        textArea = new JTextArea();
        //Init menubar
        menuBar = new JMenuBar();
        //Init menu options
        file = new JMenu("File");
        edit = new JMenu("Edit");

        //init file Menu items(sub options)
        newFile = new JMenuItem("New window");
        saveFile = new JMenuItem("Save File");
        openFile = new JMenuItem("Open File");

        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        //init edit menu items (sub options)
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close window");

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        //Add everything to thier menu bars
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //add menus to menubar
        menuBar.add(file);
        menuBar.add(edit);

        frame.setJMenuBar(menuBar);

        //frame.add(textArea);

        JPanel panel=new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0, 0));

        //aadd text area
        panel.add(textArea,BorderLayout.CENTER);
        JScrollPane scrollPane=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //add scroll to panel
        panel.add(scrollPane);
        frame.add(panel);
        frame.setTitle("Text Editor (By Ajay)");
        frame.setBounds(100, 100, 800,500);

        //visible frame
        frame.setVisible(true);
        frame.setLayout(null);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource()==cut){
            //imple cut op
            textArea.cut();
        }
        if(actionEvent.getSource()==copy){
            //imple copy op
            textArea.copy();
        }
        if(actionEvent.getSource()==paste){
            //imple paste op
            textArea.paste();
        }
        if(actionEvent.getSource()==selectAll){
            //imple selectall; op
            textArea.selectAll();
        }
        if(actionEvent.getSource()==openFile){
            //imple selectall; op
            JFileChooser fileChooser=new JFileChooser("C:");
            int chooseOption =fileChooser.showOpenDialog(null);
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //get selected file
                File file=fileChooser.getSelectedFile();

                String filePath=file.getPath();
                try{
                    FileReader fileReader=new FileReader(filePath);
                    BufferedReader bufferedReader=new BufferedReader(fileReader);
                    String intermediate="",output="";
                    while((intermediate=bufferedReader.readLine())!=null){
                        output+=intermediate+"\n";
                    }
                    //set output to textarea
                    textArea.setText(output);
                }
                catch (IOException f){
                    f.printStackTrace();
                }
            }

        }if(actionEvent.getSource()==saveFile){
            //imple selectall; op
            JFileChooser fileChooser=new JFileChooser("C:");
            int chooseOption=fileChooser.showOpenDialog(null);
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //create new file with correct dir
                File file=new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");;
                try{
                    FileWriter fileWriter=new FileWriter(file);
                    BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);

                    textArea.write(bufferedWriter);
                    bufferedWriter.close();;
                }
                catch (IOException f){
                    f.printStackTrace();
                }
            }

        }
        if(actionEvent.getSource()==newFile){
            //imple selectall; op
            TextEditor newTextEditor=new TextEditor();
        }


    }
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }
}