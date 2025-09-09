package project.fredy.tictactoe.controller;

import project.fredy.tictactoe.model.entity.Game;
import project.fredy.tictactoe.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping("/new")
    public String createGame(@RequestParam int boardSize) {
        Game game = gameService.createNewGame(boardSize);
        return "redirect:/game/" + game.getGameId();
    }

    @GetMapping("/game/{id}")
    public String showGame(@PathVariable("id") String id, Model model) {
        Game game = gameService.getGame(id).orElseThrow(() -> new IllegalArgumentException("Game not found"));
        model.addAttribute("game", game);
        int boardSize = game.getBoardSize();
        model.addAttribute("boardRange", new int[boardSize * boardSize]);
        model.addAttribute("boardSize", boardSize);
        return "game";
    }

    @PostMapping("/game/{id}/move")
    public String makeMove(@PathVariable("id") String id, @RequestParam int position) {
        gameService.makeMove(id, position);
        return "redirect:/game/" + id;
    }
}