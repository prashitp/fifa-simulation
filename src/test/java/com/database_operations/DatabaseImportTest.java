package com.database_operations;

import com.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author vasugamdha
 */

class DatabaseImportTest {

    DatabaseImport mock_instance;

    PlayerPositions[] mock_playerPositions = new PlayerPositions[]{PlayerPositions.ST, PlayerPositions.LW};

    PlayerPositions mock_clubPosition = PlayerPositions.ST;

    PlayerWorkRate mock_workRate = PlayerWorkRate.HIGH;

    String[] mock_playerTraits = new String[]{"Power Free-Kick", "Flair", "Long Shot Taker (AI)", "Speed Dribbler (AI)", "Outside Foot Shot"};

    HashMap<PlayerAttributes, Integer> mock_playerAttributes = new HashMap<PlayerAttributes, Integer>() {{
        put(PlayerAttributes.PACE, 87);
        put(PlayerAttributes.SHOOTING, 87);
        put(PlayerAttributes.ATTACKING_CROSSING, 87);
        put(PlayerAttributes.SKILL_BALL_CONTROL, 88);
        put(PlayerAttributes.MOVEMENT_ACCELERATION, 85);
    }};

    static HashMap<ClubAttributes, Integer> mock_clubAttributes = new HashMap<ClubAttributes, Integer>() {{
        put(ClubAttributes.OVERALL, 79);
        put(ClubAttributes.ATTACK, 83);
        put(ClubAttributes.MIDFIELD, 79);
        put(ClubAttributes.DEFENCE, 77);
    }};

    ClubModel[] mock_clubs = {
            new ClubModel(1, "Arsenal", mock_clubAttributes, 80000000)
    };
    PlayerModel[] mock_players = {
            new PlayerModel(1, "Cristiano Ronaldo", mock_playerPositions,
                    91, 91, 45000000, 36, 187, 83, "Manchester United",
                    mock_clubPosition, 7, 2023, "Portugal", "Right",
                    5, mock_workRate, mock_playerTraits, mock_playerAttributes, false ,
                    0, true
            )
    };

    @BeforeEach
    @Test
    void getInstanceTest() {
        mock_instance = DatabaseImport.getInstance();
        assertEquals("DatabaseImport", mock_instance.getClass().getSimpleName());
    }

    @Test
    void getPlayersTestClassName() {
        String expected = mock_players[0].getClass().getSimpleName();
        String actual = mock_instance.getPlayers()[0].getClass().getSimpleName();
        assertEquals(expected, actual);
    }

    @Test
    void getPlayersTestInstanceMethods() {
        String expected = mock_players[0].getPlayerName();
        String actual = mock_instance.getPlayers()[0].getPlayerName();
        assertEquals(expected, actual);
    }

    @Test
    void getPlayersTestInstanceAttributes() {
        int expected = mock_players[0].skills.get(PlayerAttributes.PACE);
        int actual = mock_instance.getPlayers()[0].skills.get(PlayerAttributes.PACE);
        assertEquals(expected, actual);
    }

    @Test
    void getClubsTestClassName(){
        String expected = mock_clubs[0].getClass().getSimpleName();
        String actual = mock_instance.getClubs()[0].getClass().getSimpleName();
        assertEquals(expected, actual);
    }

    @Test
    void getClubsTestInstanceMethods() {
        String expected = mock_clubs[0].getClubName();
        String actual = mock_instance.getClubs()[0].getClubName();
        assertEquals(expected, actual);
    }

    @Test
    void getClubsTestInstanceAttributes() {
        int expected = mock_clubs[0].attributes.get(ClubAttributes.ATTACK);
        int actual = mock_instance.getClubs()[0].attributes.get(ClubAttributes.ATTACK);
        assertEquals(expected, actual);
    }
}