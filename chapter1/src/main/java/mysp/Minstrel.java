package mysp;

import java.io.PrintStream;

public class Minstrel {
    private PrintStream stream;

    public Minstrel(PrintStream stream) {
        this.stream = stream;
    }

    public void singBeforeQuest() {
        stream.println("Aaaaaaa the knight is questing.");
    }

    public void singAfterQuest() {
        stream.println("Ohhhhhhh the knight finished questing.");
    }
}