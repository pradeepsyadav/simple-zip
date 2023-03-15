// package pkZip;

import java.util.ArrayList;

public class ZipRecord {

    private int pathSize;
    private String path;
    private int contentSize;
    private String content;

    
    public ZipRecord() {}

    public int getPathSize() {
        return pathSize;
    }

    public void setPathSize(int pathSize) {
        this.pathSize = pathSize;
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getContentSize() {
        return contentSize;
    }

    public void setContentSize(int contentSize) {
        this.contentSize = contentSize;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format("path lenght : %d \n Path: %s \n Data length: %d \n Data: %s", 
            getPathSize(), getPath(), getContentSize(), getContent());
    }

}
