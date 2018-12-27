package mysp;

interface knight {
    public void embarkOnQuest();
}

public class BraveKnight implements knight {
    private Quest quest;

    public BraveKnight(Quest quest) {
        this.quest = quest;
    }

    public void embarkOnQuest() {
        quest.embark();
    }
}

