package com.crocodic.coderepo.helper;

/**
 * Created by crocodic-mbp8 on 5/18/17.
 */

public class Filename {

    /**
     * This class is used to get filename with ext or without ext
     */

    String fileNameWithExt = "";
    String ext = "";
    String fileNameWithoutExt = "";

    public Filename(String pathFile) {
        int dotposition = pathFile.lastIndexOf(".");
        fileNameWithExt = pathFile.substring(pathFile.lastIndexOf("/") + 1);
        ext = pathFile.substring(dotposition + 1, pathFile.length());
    }

    public String getFileNameWithExt() {
        return fileNameWithExt;
    }

    public void setFileNameWithExt(String fileNameWithExt) {
        this.fileNameWithExt = fileNameWithExt;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getFileNameWithoutExt() {
        fileNameWithoutExt = removeExtension(fileNameWithExt);
        return fileNameWithoutExt;
    }

    private String removeExtension(String fileNameWithExt) {
        String result = "";
        int pos = fileNameWithExt.lastIndexOf(".");
        if (pos > 0) {
            result = fileNameWithExt.substring(0, pos);
        }

        return result;
    }
}
