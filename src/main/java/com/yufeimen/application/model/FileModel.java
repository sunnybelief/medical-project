package com.yufeimen.application.model;

/**
 * Created by roper on 2017/6/24.
 */
public class FileModel {
    String fileName;
    String filePath;
    Long size;
    String fileType;
    XLSModel xlsModel;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public XLSModel getXlsModel() {
        return xlsModel;
    }

    public void setXlsModel(XLSModel xlsModel) {
        this.xlsModel = xlsModel;
    }
}
