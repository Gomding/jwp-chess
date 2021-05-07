package chess.domain.board.piece.knight;

import chess.domain.board.piece.Owner;

public class BlackKnight extends Knight {

    private static final BlackKnight BLACK_KNIGHT = new BlackKnight();

    private BlackKnight() {
        super(Owner.BLACK);
    }

    public static BlackKnight getInstance() {
        return BLACK_KNIGHT;
    }

    @Override
    public String getSymbol() {
        return "N";
    }
}
