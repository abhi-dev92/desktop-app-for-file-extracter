package com.solactive.bmri.helper;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author abhin
 */
public class Extracter extends javax.swing.JFrame {


    private javax.swing.JButton pdfLoaderButton;
    private javax.swing.JButton buttonOwner;
    private javax.swing.JButton buttonNotOwner;
    private javax.swing.JButton buttonOpenFile;
    private javax.swing.JScrollPane jScrollPane2;
    private JList listPdfs;
    private TextArea textView;
    private File selectedDirectory = new File(".");
    private File selectedFile;

    private javax.swing.JLabel description = new JLabel("'o','O','y' or 'Y' for select ownership --- 'n' or 'N' for select NO ownership --- 'ENTER' to open PDF in Viewer --- 'Backspace to step one file back");

    private int selectedIndex = 0;
    private int lastIndex;

    private String storedJSON;
    private String storageFileName = "ownership.json";


    /**
     * Creates new form com.solactive.bmri.helper.com.solactive.bmri.main.PdfExtracter
     */
    public Extracter() {
        initComponents();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {


        textView = new TextArea();
        textView.setEditable(false);
        textView.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char keyChar = e.getKeyChar();
                if (keyChar == 'o' || 'O' == keyChar || 'y' == keyChar || 'Y' == keyChar) {
                    storeOwnership(true);
                    nextPdf();
                } else if ('n' == keyChar || 'N' == keyChar) {
                    storeOwnership(false);
                    nextPdf();
                } else if (keyChar == KeyEvent.VK_ENTER) {
                    openFile();
                } else if (keyChar == KeyEvent.VK_BACK_SPACE) {
                    previousPdf();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        pdfLoaderButton = new javax.swing.JButton();
        buttonOwner = new javax.swing.JButton();
        buttonOwner.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storeOwnership(true);
                nextPdf();
            }
        });

        buttonNotOwner = new javax.swing.JButton();
        buttonNotOwner.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storeOwnership(false);
                nextPdf();
            }
        });
        buttonOpenFile = new JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        listPdfs = new JList();


        listPdfs.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
               // classifyOwnership();
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pdfLoaderButton.setText("Load PDFs");
        pdfLoaderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });
        pdfLoaderButton.setToolTipText("");

        buttonOwner.setText("Owner");
        buttonNotOwner.setText("Not Owner");
        buttonOpenFile.setText("Open PDF file");

        buttonOpenFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });



        Container contentPane = getContentPane();
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(contentPane);
        contentPane.setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(150, 150, 150)
                                .addComponent(description)
                        )
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(pdfLoaderButton))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(31, 31, 31)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(textView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(buttonOwner)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                                                                .addComponent(buttonNotOwner)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 269, Short.MAX_VALUE)
                                                                .addComponent(buttonOpenFile)
                                                        ))
                                                .addGap(97, 97, 97))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(50, 50, 50)
                                                .addComponent(description)
                                                .addGap(47, 47, 47)
                                                .addComponent(textView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(buttonOwner)
                                                        .addComponent(buttonNotOwner)
                                                        .addComponent(buttonOpenFile))
                                                .addGap(19, 19, 19))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(51, 51, 51)
                                                .addComponent(pdfLoaderButton)
                                                .addGap(18, 18, 18)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 67, Short.MAX_VALUE)))
                                .addGap(20, 20, 20))
        );

        pack();
        textView.requestFocus();

    }// </editor-fold>



    private void openFile() {
        Desktop dt = Desktop.getDesktop();
        try {
            dt.open(selectedFile);
        } catch (IOException e1) {
            System.out.println(e1);
        }
    }

    private void nextPdf() {
        selectedIndex++;
        selectedIndex = Math.min(selectedIndex, lastIndex);
        listPdfs.setSelectedIndex(selectedIndex);
        //classifyOwnership();
        textView.requestFocus();
    }

    private void previousPdf() {
        selectedIndex--;
        selectedIndex = Math.max(0, selectedIndex);
        listPdfs.setSelectedIndex(selectedIndex);
        //classifyOwnership();
        textView.requestFocus();
    }

    /*private void classifyOwnership() {
        final String selectedFilename = listPdfs.getSelectedValue().toString();
        selectedFile = new File(selectedDirectory, selectedFilename);

        try {
            PDDocument doc = PDDocument.load(selectedFile);
            final PDFTextStripper textStripper = new PDFTextStripper();
            final String pdfText = textStripper.getText(doc);

            final String calculator = "(?i)(Index\\s+Calculator)";
            final String solactiveAG = "(Solactive\\s+AG)";
            final ArrayList<String> filterd = new ArrayList<>();


            ArrayList<String> findings = findInText(pdfText, calculator);
            if (findings.size() == 0) {
                findings = findInText(pdfText, solactiveAG);
            } else {
                for (String finding : findings) {
                    final boolean containsSolactive = findInText(finding, solactiveAG).size() > 0;
                    if (containsSolactive) {
                        filterd.add(finding);
                    }
                }
            }

            if (filterd.size() == 0) {
                buttonNotOwner.setBackground(new Color(0, 255, 0));
                buttonOwner.setBackground(new Color(200, 200, 200));
            } else {
                buttonOwner.setBackground(new Color(0, 255, 0));
                buttonNotOwner.setBackground(new Color(200, 200, 200));
                findings = filterd;
            }

            if (findings.size() == 0) {
                textView.setText(pdfText);
            } else {
                final StringBuilder sb = new StringBuilder();
                for (int i = 0; i < findings.size(); i++) {
                    sb.append(findings.get(i));
                    if (i < findings.size() - 1) {
                        sb.append("\n...\n");
                    }
                }
                textView.setText(sb.toString());
            }
            textView.setCaretPosition(0);

        } catch (IOException e1) {
            System.out.println("could not load " + selectedFile);

        }
    }*/

    public void storeOwnership(boolean b) {
        Storage storage = Storage.open(storedJSON);
        storedJSON = storage.addEntry(selectedFile.getName(), b);
        System.out.println(storedJSON);
        File storageFile = new File(selectedDirectory, storageFileName);
        try {
            Storage.store(storageFile, storedJSON);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static ArrayList<String> findInText(String pdfText, String regex) {
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(pdfText);

        ArrayList<String> findings = new ArrayList<>();
        final int offset = 100;
        while (matcher.find()) {
            final int start = Math.max(0, matcher.start() - offset);
            final int end = Math.min(matcher.end() + offset, pdfText.length());
            final String finding = pdfText.substring(start, end);
            findings.add(finding);
        }
        return findings;
    }





}
