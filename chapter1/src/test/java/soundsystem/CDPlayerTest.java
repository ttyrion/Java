package soundsystem;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=CDPlayerConfig.class)
public class CDPlayerTest {
    private int i = 0;
    @Autowired
    private CDPlayer player = null;

    @Test
    public void onTest() {
        while(i <= 100) {
            ++i;
            System.out.println("onTest times: " + i);
            try{
                play();
            }
            catch(NullPointerException e) {
                System.out.println("onTest RuntimeException catched.");
            }
            finally {
                System.out.println("onTest RuntimeException out of catch.");
            }
        }
    }
    
    public void play() {
        System.out.println("Test running...");
        player.play();
        throw new RuntimeException();
    }
}