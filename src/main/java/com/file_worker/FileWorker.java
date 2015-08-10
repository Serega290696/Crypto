package com.file_worker;

import org.apache.log4j.Logger;

import java.io.*;

public class FileWorker implements IFileWorker {

    private static final Logger logger = Logger.getLogger("logFileWorker");

    public void write(String fileName, String text) {
        if (logger.isTraceEnabled())
            logger.trace("Write in file: \"" + fileName + "\". Text: \"" + text.substring(0, 20) + "...\".");
        File file = new File(fileName);

        try {
            if (!file.exists()) {
                if (logger.isTraceEnabled())
                    logger.trace("File \"" + fileName + "\" was created!");
                file.createNewFile();
            } else {
                if (logger.isTraceEnabled())
                    logger.warn("File \"" + fileName + "\" was rewrite!");
            }
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());

            try {
                out.print(text);
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String read(String fileName) {
        if (logger.isTraceEnabled())
            logger.trace("Read from file: \"" + fileName + "\".");
        StringBuilder sb = new StringBuilder();
        File file = new File(fileName);
        try {
            exists(fileName);
        } catch (FileNotFoundException e) {
            logger.warn("File \"" + fileName + "\" does not exist!");
            System.err.println("File \"" + fileName + "\" does not exist!");
            return "";
        }
        try(BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()))){
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    public void update(String nameFile, String newText) throws FileNotFoundException {
        exists(nameFile);
        StringBuilder sb = new StringBuilder();
        String oldFile = read(nameFile);
        sb.append(oldFile);
        sb.append(newText);
        write(nameFile, sb.toString());
    }

    public void delete(String nameFile) throws FileNotFoundException {
        exists(nameFile);
        new File(nameFile).delete();
    }

    private void exists(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException(file.getName());
        }
    }

}


