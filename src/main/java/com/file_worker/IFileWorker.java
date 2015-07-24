package com.file_worker;

/**
 * Created by Serega on 23.07.2015.
 */
public interface IFileWorker {

    public String read(String fileName);
    public void write(String fileName, String text);

}
