package com.solactive.bmri.main;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.solactive.bmri.helper.Extracter;
import com.solactive.bmri.helper.Storage;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author abhin
 */
public class PdfExtracter extends javax.swing.JFrame {

    // Variables declaration - do not modify
    private javax.swing.JButton previousButton;
    private javax.swing.JButton nextButton;
    private javax.swing.JButton loadPdfButton;
    private javax.swing.JButton ownerButton;
    private javax.swing.JButton notOwnerButton;
    private javax.swing.JScrollPane jScrollPane2;
    private java.awt.List pdfListing;
    private java.awt.TextArea pdfContentTextArea;
    private static String pageContent;
    PdfReader pdfReader;
    int globalFileIndex = -1;
    private static Extracter objExtracter ;
    private File selectedDirectory;
    //private File selectedFile;
    //private String storedJSON;
    private String storageFileName = "ownership.json";
    private String storedJSON;


    public PdfExtracter() {
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        previousButton = new javax.swing.JButton();
         nextButton = new javax.swing.JButton();
         pdfContentTextArea = new java.awt.TextArea();
         loadPdfButton = new javax.swing.JButton();
         ownerButton = new javax.swing.JButton();
         notOwnerButton = new javax.swing.JButton();
         jScrollPane2 = new javax.swing.JScrollPane();
         pdfListing = new java.awt.List();
         objExtracter = new Extracter();
        selectedDirectory = new File(".");


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        previousButton.setText("Previous");

        nextButton.setText("Next");

        loadPdfButton.setText("Load Files");

        loadPdfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadPdfButtonClicked(e);
            }
        });
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextPdf();
            }
        });
        previousButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(pdfListing.getItemCount()>1) {
                    if (globalFileIndex == -1) {
                        globalFileIndex = 1;
                    }
                    if(globalFileIndex ==0){

                        readFileContent(pdfListing.getItem(0).toString());

                        pdfListing.select(0);
                    }
                    else{
                        globalFileIndex = globalFileIndex - 1;
                        readFileContent(pdfListing.getItem(globalFileIndex).toString());

                        pdfListing.select(globalFileIndex);
                    }
                }
                classifyOwnership();
            }
        });

        loadPdfButton.setToolTipText("Load PDFs");

        ownerButton.setText("Owner");

        ownerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                objExtracter.storeOwnership(true);
                nextPdf();
            }
        });

        notOwnerButton.setText("Not Owner");
        notOwnerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                objExtracter.storeOwnership(false);
                nextPdf();
            }
        });

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setVerifyInputWhenFocusTarget(false);
        jScrollPane2.setViewportView(pdfListing);

        pdfListing.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 1) {
                    pdfContentTextArea.setText("");
                    readFileContent(pdfListing.getItem(pdfListing.getSelectedIndex()).toString());
                    globalFileIndex = pdfListing.getSelectedIndex();

                }
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(loadPdfButton))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(previousButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap())
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(31, 31, 31)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(pdfContentTextArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(ownerButton)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 269, Short.MAX_VALUE)
                                                                .addComponent(notOwnerButton)))
                                                .addGap(97, 97, 97))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(97, 97, 97)
                                                .addComponent(pdfContentTextArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(ownerButton)
                                                        .addComponent(notOwnerButton))
                                                .addGap(19, 19, 19)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(previousButton)
                                                        .addComponent(nextButton)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(51, 51, 51)
                                                .addComponent(loadPdfButton)
                                                .addGap(18, 18, 18)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 67, Short.MAX_VALUE)))
                                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>

    public void readFileContent(String filename){
        try {

            pdfReader = new PdfReader(selectedDirectory.toString()+"\\"+filename);

            //Get the number of pages in pdf.
            int pages = pdfReader.getNumberOfPages();

            //Iterate the pdf through pages.
            for(int i=1; i<=pages; i++) {
                //Extract the page content using PdfTextExtractor.
                pageContent =
                        PdfTextExtractor.getTextFromPage(pdfReader, i);
                pdfContentTextArea.append(pageContent);
                //Print the page content on console.
                //System.out.println("Content on Page "
                  //      + i + ": " + pageContent);
            }

            pdfReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void nextPdf(){
        if(pdfListing.getItemCount()>1) {
            globalFileIndex = globalFileIndex + 1;
            readFileContent(pdfListing.getItem(globalFileIndex).toString());
            pdfListing.select(globalFileIndex);
        }
        classifyOwnership();
    }

    private void loadPdfButtonClicked(java.awt.event.ActionEvent evt) {



        //File folder = new File("C:\\Users\\abhin\\Documents\\pdfs");
        //File[] listOfFiles = folder.listFiles();
//        Arrays.sort(listOfFiles);
//        for (int i = 0; i < listOfFiles.length; i++) {
//            if (listOfFiles[i].isFile()) {
//                pdfListing.add(listOfFiles[i].getName());
//                //System.out.println("File " + listOfFiles[i].getName());
//            } else if (listOfFiles[i].isDirectory()) {
//                //System.out.println("Directory " + listOfFiles[i].getName());
//            }
//        }

        final JFileChooser directoryChooser = new JFileChooser();
        directoryChooser.setCurrentDirectory(new File("."));
        directoryChooser.setDialogTitle("Select a directory with index PDFs");
        directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        directoryChooser.setAcceptAllFileFilterUsed(false);

        final int dialog = directoryChooser.showOpenDialog(this);
        if (dialog != JFileChooser.APPROVE_OPTION) {
            return;
        }

        selectedDirectory = directoryChooser.getSelectedFile();
        System.out.println(selectedDirectory);
//      File selectedDirectory = new File("C:\\Users\\abhin\\Documents\\pdfs");


        File[] listOfFiles = selectedDirectory.listFiles();
        ArrayList<String> files = new ArrayList<>();
        for (File file : listOfFiles) {
            String fileName = file.getName();
            if (!file.isFile()) {
                continue;
            }

            if (fileName.endsWith(".pdf")) {
                files.add(fileName);
                System.out.println("File " + fileName);
            } else if (fileName.equals(storageFileName)) {
                try {
                    storedJSON = Storage.load(new File(selectedDirectory, storageFileName));
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                pdfListing.add(listOfFiles[i].getName());
                //System.out.println("File " + listOfFiles[i].getName());
            } else if (listOfFiles[i].isDirectory()) {
                //System.out.println("Directory " + listOfFiles[i].getName());
            }
        }
        //pdfListing.setListData(files.toArray());
        //pdfListing.setSelectedIndex(0);
        globalFileIndex = Math.max(0, files.size() - 1);
        pdfContentTextArea.requestFocus();
    }
    private void classifyOwnership() {

            final String calculator = "(?i)(Index\\s+Calculator)";
            final String solactiveAG = "(Solactive\\s+AG)";
            final ArrayList<String> filterd = new ArrayList<>();

            ArrayList<String> findings = com.solactive.bmri.helper.Extracter.findInText(pageContent, calculator);

            if (findings.size() == 0) {
                findings = com.solactive.bmri.helper.Extracter.findInText(pageContent, solactiveAG);
            } else {
                for (String finding : findings) {
                    final boolean containsSolactive = com.solactive.bmri.helper.Extracter.findInText(finding, solactiveAG).size() > 0;
                    if (containsSolactive) {
                        filterd.add(finding);
                    }
                }
            }

            if (filterd.size() == 0) {
                notOwnerButton.setBackground(new Color(0, 255, 0));
                ownerButton.setBackground(new Color(200, 200, 200));
                System.out.println("No Findings");
            } else {
                System.out.println("Findings");
                ownerButton.setBackground(new Color(0, 255, 0));
                notOwnerButton.setBackground(new Color(200, 200, 200));
                findings = filterd;
            }

            if (findings.size() == 0) {
                pdfContentTextArea.setText(pageContent);
            } else {
                final StringBuilder sb = new StringBuilder();
                for (int i = 0; i < findings.size(); i++) {
                    sb.append(findings.get(i));
                    if (i < findings.size() - 1) {
                        sb.append("\n...\n");
                    }
                }
                pdfContentTextArea.setText(sb.toString());
            }
        pdfContentTextArea.setCaretPosition(0);


    }

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PdfExtracter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PdfExtracter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PdfExtracter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PdfExtracter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PdfExtracter().setVisible(true);
            }
        });
    }
}