package com.solactive.bmri.helper;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private static String encoding = "UTF-16";

    public List<PdfEntry> pdfEntries = new ArrayList<>();

    private Storage() {
    }

    public static Storage open(final String json) {

        if(json == null || json.isEmpty())
            return new Storage();

        final Gson gson = new Gson();
        return gson.fromJson(json, Storage.class);
    }

    public static void store(final File file, final String json) throws IOException {
        FileUtils.writeStringToFile(file,json,encoding,false);
    }

    public static String load(final File file) throws IOException {
      return  FileUtils.readFileToString(file, encoding);
    }

    public String addEntry(final String pdfName, final boolean isSolactiveOwner) {

        boolean containEntry = false;
        for (PdfEntry entry : pdfEntries) {
            if (entry.name.equals(pdfName)) {
                entry.solactiveIsOwner = isSolactiveOwner;
                containEntry = true;
                break;
            }
        }

        if(!containEntry) {
            pdfEntries.add(new PdfEntry(pdfName, isSolactiveOwner));
        }

        final Gson gson = new Gson();
        return gson.toJson(this);
    }

    public class PdfEntry {
        public String name;
        public boolean solactiveIsOwner;

        public PdfEntry() {
        }

        public PdfEntry(String name, boolean solactiveIsOwner) {
            this.name = name;
            this.solactiveIsOwner = solactiveIsOwner;
        }
    }


}
