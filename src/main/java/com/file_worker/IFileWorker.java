package com.file_worker;

import java.io.FileNotFoundException;

/**
 * Created by Serega on 23.07.2015.
 */
public interface IFileWorker {

    public String read(String fileName) throws FileNotFoundException;
    public void write(String fileName, String text);

}
