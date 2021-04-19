package chess.dao.jdbc;

import chess.dao.ScoreDao;
import chess.dao.dto.score.ScoreDto;
import chess.domain.manager.GameStatus;
import chess.exception.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScoreDaoJDBC implements ScoreDao {

    @Override
    public Long saveScore(final GameStatus gameStatus, final Long gameId) {
        final String query = "INSERT INTO score(game_id, white_score, black_score) VALUES (?, ?, ?)";

        try (final Connection connection = ConnectionProvider.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, gameId.intValue());
            pstmt.setDouble(2, gameStatus.whiteScore());
            pstmt.setDouble(3, gameStatus.blackScore());
            return pstmt.executeLargeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("체스 게임의 점수를 저장하는데 실패했습니다.", e);
        }
    }

    @Override
    public Long updateScore(final GameStatus gameStatus, final Long gameId) {
        final String query = "UPDATE score SET white_score=?, black_score=? WHERE game_id=?";

        try (final Connection connection = ConnectionProvider.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setDouble(1, gameStatus.whiteScore());
            pstmt.setDouble(2, gameStatus.blackScore());
            pstmt.setInt(3, gameId.intValue());
            return pstmt.executeLargeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("체스 게임의 점수를 업데이트하는데 실패했습니다.", e);
        }
    }

    @Override
    public ScoreDto findScoreByGameId(final Long gameId) {
        final String query = "SELECT * from score where game_id = ?";

        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setInt(1, gameId.intValue());
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (!resultSet.next()) {
                    return null;
                }
                return new ScoreDto(
                        resultSet.getDouble("white_score"),
                        resultSet.getDouble("black_score"));
            }
        } catch (SQLException e) {
            throw new DataAccessException("해당 GameID의 점수를 검색하는데 실패했습니다.", e);
        }
    }
}
