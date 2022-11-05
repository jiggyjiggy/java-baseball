package baseball;

import baseball.enums.Announcement;

public class Application {
    public static void main(String[] args) throws IllegalArgumentException {
        Announcement.GAME_START.printlnAnnouncement();

        Gamer gamer = new Gamer();
        GameMachine gameMachine = new GameMachine();

        boolean playFlag = true;

        while (playFlag) {
            gamer.play(gameMachine, new Game());
            Announcement.GAME_END.printlnAnnouncement();
            playFlag = gameMachine.askReplay();
        }
    }
}
