package project.fredy.tictactoe.model.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String gameId;
    private Integer boardSize;
    @ElementCollection
    private List<String> board;
    private String currentPlayer;
    private String winner;
    private Boolean isGameOver;
}
