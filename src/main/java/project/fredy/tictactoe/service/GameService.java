package project.fredy.tictactoe.service;

import project.fredy.tictactoe.model.entity.Game;
import project.fredy.tictactoe.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public Game createNewGame(int boardSize) {
        List<String> board = new ArrayList<>(Collections.nCopies(boardSize * boardSize, ""));
        Game game = Game.builder()
                .boardSize(boardSize)
                .board(board)
                .currentPlayer("X")
                .isGameOver(false)
                .build();
        return gameRepository.save(game);
    }

    public Optional<Game> getGame(String gameId) {
        return gameRepository.findById(gameId);
    }

    public Game makeMove(String gameId, int position) {
        Game game = getGame(gameId).orElseThrow(() -> new IllegalArgumentException("Game not found"));

        if (Boolean.TRUE.equals(game.getIsGameOver()) || !game.getBoard().get(position).isEmpty()) {
            throw new IllegalArgumentException("Invalid move");
        }

        game.getBoard().set(position, game.getCurrentPlayer());

        if (checkWinner(game.getBoard(), game.getBoardSize(), game.getCurrentPlayer())) {
            game.setWinner(game.getCurrentPlayer());
            game.setIsGameOver(true);
        } else if (checkDraw(game.getBoard())) {
            game.setIsGameOver(true);
        } else {
            game.setCurrentPlayer(game.getCurrentPlayer().equals("X") ? "O" : "X");
        }

        return gameRepository.save(game);
    }

    private boolean checkWinner(List<String> board, int boardSize, String player) {
        // Check rows
        for (int i = 0; i < boardSize; i++) {
            final int finalI = i;
            boolean rowWin = IntStream.range(0, boardSize).allMatch(j -> board.get(finalI * boardSize + j).equals(player));
            if (rowWin) return true;
        }

        // Check columns
        for (int j = 0; j < boardSize; j++) {
            final int finalJ = j;
            boolean colWin = IntStream.range(0, boardSize).allMatch(i -> board.get(i * boardSize + finalJ).equals(player));
            if (colWin) return true;
        }

        // Check diagonal
        boolean diag1Win = IntStream.range(0, boardSize).allMatch(i -> board.get(i * boardSize + i).equals(player));
        if (diag1Win) return true;

        boolean diag2Win = IntStream.range(0, boardSize).allMatch(i -> board.get(i * boardSize + (boardSize - 1 - i)).equals(player));
        return diag2Win;
    }

    private boolean checkDraw(List<String> board) {
        return board.stream().noneMatch(String::isEmpty);
    }
}
