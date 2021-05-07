package chess.dao.dto.piece;

public class PieceDto {

    private Long id;
    private Long gameId;
    private String symbol;
    private String position;

    public PieceDto() {
    }

    public PieceDto(Long id, Long gameId, String symbol, String position) {
        this.id = id;
        this.gameId = gameId;
        this.symbol = symbol;
        this.position = position;
    }

    public PieceDto(Long gameId, String symbol, String position) {
        this.gameId = gameId;
        this.symbol = symbol;
        this.position = position;
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}