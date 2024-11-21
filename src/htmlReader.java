import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




public class htmlReader implements ActionListener{
    private JFrame mainFrame;
    private JPanel borderPanel, gridPanel;
    private JButton startButton;
    private JLabel urlLabel, keywordLabel;
    private JTextArea urlTextArea, keywordTextArea, outputTextArea;
    private JScrollPane scrollPane;

    public static void main(String[] args) {
        htmlReader myCode = new htmlReader();
    }

    public htmlReader() {
        try {
//            mainFrame = new JFrame("Frame");
//            mainFrame.setSize(550, 500);

            mainFrame = new JFrame("html Reader Frame");
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(550, 500);


            borderPanel = new JPanel();
            borderPanel.setLayout(new BorderLayout());
            mainFrame.add(borderPanel);


            gridPanel = new JPanel(new GridLayout(2, 1));
            borderPanel.add(gridPanel, BorderLayout.NORTH);


            urlTextArea = new JTextArea();
            urlLabel = new JLabel("enter your url:");
            keywordTextArea = new JTextArea();
            keywordLabel = new JLabel("enter your keyword:");
            startButton = new JButton("start generating!");

            outputTextArea = new JTextArea();
            scrollPane = new JScrollPane(outputTextArea);
//            outputTextArea = new JTextArea();
            outputTextArea.setBackground(Color.blue);



            gridPanel.add(urlLabel);
            gridPanel.add(urlTextArea);
            gridPanel.add(keywordLabel);
            gridPanel.add(keywordTextArea);
           // borderPanel.add(scrollPane, BorderLayout.CENTER);

            borderPanel.add(startButton, BorderLayout.SOUTH);
            borderPanel.add(scrollPane, BorderLayout.CENTER);
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

            startButton.addActionListener(this);



            mainFrame.setVisible(true);
            mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



        } catch (Exception e) {

            System.out.println(e);
        }

    }



    public void actionPerformed (ActionEvent e){
        String inputURL = urlTextArea.getText();
        String inputKeyword = keywordTextArea.getText();
        System.out.println(inputURL);
        System.out.println(inputKeyword);

        URL url = null;
        try {
            url = new URL(inputURL);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = "";
            while (line != null) {
                if (line.contains(inputKeyword) && line.contains("href=")) {
                    int start = line.indexOf("href=");
                    int end = line.indexOf("\"", start + 6);
                    System.out.println(line.substring(start + 6, end));
                    String printText = line.substring(start + 6, end);
                    outputTextArea.append(printText + "\n");
                }


                line = reader.readLine();

            }


        }catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}



