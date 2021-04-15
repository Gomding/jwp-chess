package chess.dao.jdbc;

import chess.controller.web.dto.state.StateResponseDto;
import chess.dao.StateDao;
import chess.domain.manager.ChessManager;
import chess.exception.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StateDaoJDBC implements StateDao {

    @Override
    public Long saveState(final ChessManager chessManager, final Long gameId) {
        final String query = "INSERT INTO state(game_id, turn_owner, turn_number, playing) VALUES (?, ?, ?, ?)";

        try (final Connection connection = ConnectionProvider.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, gameId.intValue());
            pstmt.setString(2, chessManager.turnOwner().name());
            pstmt.setInt(3, chessManager.turnNumber());
            pstmt.setBoolean(4, chessManager.isPlaying());
            return pstmt.executeLargeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("체스게임의 상태를 저장하는데 실패했습니다.", e);
        }
    }

    @Override
    public Long updateState(final ChessManager chessManager, final Long gameId) {
        final String query = "UPDATE state SET turn_owner=?, turn_number=?, playing=? WHERE game_id=?";

        try (final Connection connection = ConnectionProvider.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, chessManager.turnOwner().name());
            pstmt.setInt(2, chessManager.turnNumber());
            pstmt.setBoolean(3, chessManager.isPlaying());
            pstmt.setInt(4, gameId.intValue());
            return pstmt.executeLargeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("체스게임의 상태를 업데이트하는데 실패했습니다.", e);
        }
    }

    @Override
    public StateResponseDto findStateByGameId(final Long gameId) {
        final String query = "SELECT * from state where game_id = ?";

        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setInt(1, gameId.intValue());
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (!resultSet.next()) {
                    return null;
                }
                return new StateResponseDto(
                        resultSet.getString("turn_owner"),
                        resultSet.getInt("turn_number"),
                        resultSet.getBoolean("playing"));
            }
        } catch (SQLException e) {
            throw new DataAccessException("해당 GameID의 상태를 검색하는데 실패했습니다.", e);
        }
    }
}
