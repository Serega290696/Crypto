import com.file_worker.FileWorker;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Serega on 28.07.2015.
 */
public class TestFileWorker extends Assert {

    private static String fileNameToWrite = "myFile100153.txt";
    private static String textToWrite = "Test string! Why milk is white?\n";
    private static String fileNameToRead = "myFile100153.txt";
    private static FileWorker fileWorker = new FileWorker();

    @Test(timeout = 10000)
    public void testWrite() {
        fileWorker.write(fileNameToWrite, textToWrite);
    }

    @Test(timeout = 10000)
    public void testRead() {
        FileWorker fileWorker = new FileWorker();
        String res = fileWorker.read(fileNameToRead);
        System.out.println(res);
        Assert.assertEquals("Something go wrong!", textToWrite, res);
    }
}
