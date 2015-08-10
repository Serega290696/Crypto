package com.file_worker;

import org.apache.log4j.Logger;

import java.io.*;

public class FileWorker implements IFileWorker {

    private static final Logger logger = Logger.getLogger("logFileWorker");

    public void write(String fileName, String text) {
    }

    public String read(String fileName) {
        return "";
    }

    public void update(String nameFile, String newText) throws FileNotFoundException {
    }

    public void delete(String nameFile) throws FileNotFoundException {
    }

    private void exists(String fileName) throws FileNotFoundException {
    }

}
