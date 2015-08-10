package com.file_worker;

import java.io.FileNotFoundException;


public interface IFileWorker {

    public String read(String fileName) throws FileNotFoundException;
    public void write(String fileName, String text);

}

