package chess.controller.web.dto.piece;

import chess.domain.board.position.Position;

public class PieceResponseDto {

    private final String symbol;
    private final String position;

    public PieceResponseDto(final String symbol, final String position) {
        this.symbol = symbol;
        this.position = position;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getPosition() {
        return position;
    }
}
