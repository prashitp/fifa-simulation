package com.database_operations;

import com.models.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author vasugamdha
 */

class DatabaseImportTest {

    @Mock
    DatabaseImport mock_instance;

    @Mock
    PlayerPositions[] mock_playerPositions = new PlayerPositions[]{PlayerPositions.ST, PlayerPositions.LW};

    @Mock
    PlayerPositions mock_clubPosition = PlayerPositions.ST;

    @Mock
    PlayerWorkRate mock_workRate = PlayerWorkRate.HIGH;

    @Mock
    String[] mock_playerTraits = new String[]{"Power Free-Kick", "Flair", "Long Shot Taker (AI)", "Speed Dribbler (AI)", "Outside Foot Shot"};

    @Mock
    HashMap<PlayerAttributes, Integer> mock_playerAttributes = new HashMap<PlayerAttributes, Integer>() {{
        put(PlayerAttributes.Pace, 87);
        put(PlayerAttributes.Shooting, 87);
        put(PlayerAttributes.AttackingCrossing, 87);
        put(PlayerAttributes.SkillBallControl, 88);
        put(PlayerAttributes.MovementAcceleration, 85);
    }};

    @Mock
    static HashMap<ClubAttributes, Integer> mock_clubAttributes = new HashMap<ClubAttributes, Integer>() {{
        put(ClubAttributes.Overall, 79);
        put(ClubAttributes.Attack, 83);
        put(ClubAttributes.Midfield, 79);
        put(ClubAttributes.Defence, 77);
    }};

    @Mock
    ClubModel[] mock_clubs = {
            new ClubModel(1, "Arsenal", mock_clubAttributes, 80000000)
    };
    @Mock
    PlayerModel[] mock_players = {
            new PlayerModel(1, "Cristiano Ronaldo", mock_playerPositions,
                    91, 91, 45000000, 36, 187, 83, "Manchester United",
                    mock_clubPosition, 7, 2023, "Portugal", "Right",
                    5, mock_workRate, mock_playerTraits, mock_playerAttributes
            )
    };

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
        int expected = mock_players[0].skills.get(PlayerAttributes.Pace);
        int actual = mock_instance.getPlayers()[0].skills.get(PlayerAttributes.Pace);
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
        int expected = mock_clubs[0].attributes.get(ClubAttributes.Attack);
        int actual = mock_instance.getClubs()[0].attributes.get(ClubAttributes.Attack);
        assertEquals(expected, actual);
    }
}