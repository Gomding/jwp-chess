package chess.dao.dto.history;

import chess.domain.entity.History;

public class HistoryDto {

    private Long id;
    private Long gameId;
    private String moveCommand;
    private String turnOwner;
    private int turnNumber;
    private boolean isPlaying;

    public HistoryDto() {
    }

    public HistoryDto(Long id, Long gameId, String moveCommand, String turnOwner, int turnNumber, boolean isPlaying) {
        this.id = id;
        this.gameId = gameId;
        this.moveCommand = moveCommand;
        this.turnOwner = turnOwner;
        this.turnNumber = turnNumber;
        this.isPlaying = isPlaying;
    }

    public HistoryDto(Long gameId, String moveCommand, String turnOwner, int turnNumber, boolean isPlaying) {
        this.gameId = gameId;
        this.moveCommand = moveCommand;
        this.turnOwner = turnOwner;
        this.turnNumber = turnNumber;
        this.isPlaying = isPlaying;
    }

    public static HistoryDto from(final History history) {
        return new HistoryDto(
                history.getGameId(),
                history.getMoveCommand(),
                history.getTurnOwner(),
                history.getTurnNumber(),
                history.isPlaying()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public String getMoveCommand() {
        return moveCommand;
    }

    public void setMoveCommand(String moveCommand) {
        this.moveCommand = moveCommand;
    }

    public String getTurnOwner() {
        return turnOwner;
    }

    public void setTurnOwner(String turnOwner) {
        this.turnOwner = turnOwner;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }
}