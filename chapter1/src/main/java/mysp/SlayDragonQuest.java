package mysp;

import java.io.PrintStream;

interface Quest {
    public void embark();
}

public class SlayDragonQuest implements Quest {
    private PrintStream stream;

    public SlayDragonQuest(PrintStream stream) {
        this.stream = stream;
    }
 
    public void embark() {
        stream.println("SlayDragonQuest embarking.");
    }
}