import com.swing_interface.MyFrame;
import org.junit.Test;

/**
 * Created by Serega on 31.07.2015.
 */
public class TestGUI {

    @Test
    public void testGui() {
        MyFrame m = new MyFrame();
        m.launch();
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        m.launch();
    }

}
