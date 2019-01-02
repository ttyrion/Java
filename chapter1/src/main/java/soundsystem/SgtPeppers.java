package soundsystem;
import org.springframework.stereotype.Component;

@Component
public class SgtPeppers implements  CompactDisc {
    private String title = "东风破";
    private String artist = "Jay";

    public void play() {
        System.out.println("Playing " + title + " by " + artist);
    }
}