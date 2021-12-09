package com.database_operations;

import com.utils.LogService;
import com.models.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;

/**
 * @author vasugamdha
 */

public class DatabaseImport {
    final private static Connection con = DatabaseConnection.getInstance().getConnection();
    private static final PlayerModel[] players = new PlayerModel[651];
    private static final ClubModel[] clubs = new ClubModel[20];
    static Map<String, List<Object>> table_clubs = new TreeMap<>();
    static Map<String, String> table_nationality = new TreeMap<>();
    static HashMap<PlayerAttributes, Integer> player_attributes = new HashMap<>();
    static HashMap<ClubAttributes, Integer> club_attributes = new HashMap<>();
    static LogService logService = new LogService();

    static String[] empty_array = {};

    private static DatabaseImport instance;

    private DatabaseImport() {
        try {
            fetchClubs();
            logService.log(Level.INFO, "Clubs fetched!");
        } catch (Exception e) {
            System.out.println("Database errored out");
            logService.log(Level.SEVERE, "ERROR! Exception occurred while fetching from clubs table"
                    + e.getStackTrace());
        }

        try {
            fetchNationality();
            logService.log(Level.INFO, "Nationalities fetched!");
        } catch (Exception e) {
            logService.log(Level.SEVERE, "ERROR! Exception occurred while fetching from nationality table"
                    + e.getStackTrace());
        }
            setClubs();

        try {
            setPlayers();
            logService.log(Level.INFO, "Players table imported successfully!");
        } catch (Exception e) {
            logService.log(Level.SEVERE, "ERROR! Exception occurred while storing players table into variable"
                    + e.getStackTrace());
        }
    }

    public static DatabaseImport getInstance() {
        if(instance == null){
            instance = new DatabaseImport();
        }
        return instance;
    }

    public static PlayerModel[] getPlayers() {
        return players;
    }

    public static ClubModel[] getClubs() {
        return clubs;
    }

    private static void fetchNationality() throws Exception {
        String query = "SELECT * FROM nationality";
        ResultSet rs = con.createStatement().executeQuery(query);
        while (rs.next()) {
            table_nationality.put(
                    rs.getString("nationality_id"),
                    rs.getString("nationality")
            );
        }
    }

    private static void setClubAttributes(ResultSet rs) throws SQLException {
        club_attributes = new HashMap<>();
        club_attributes.put(ClubAttributes.OVERALL, rs.getInt("overall"));
        club_attributes.put(ClubAttributes.ATTACK, rs.getInt("attack"));
        club_attributes.put(ClubAttributes.MIDFIELD, rs.getInt("midfield"));
        club_attributes.put(ClubAttributes.DEFENCE, rs.getInt("defence"));
    }

    private static void fetchClubs() throws Exception {
        String query = "SELECT * FROM clubs";
        ResultSet rs = con.createStatement().executeQuery(query);
        while (rs.next()) {
            setClubAttributes(rs);
            table_clubs.put(rs.getString("club_id"),
                    Arrays.asList(
                            rs.getString("club_name"),
                            club_attributes,
                            rs.getLong("transfer_budget")
                    )
            );
        }
    }

    private static void setClubs(){
        int i = 0;
        for (Map.Entry<String, List<Object>> club : table_clubs.entrySet()) {
            List<Object> row = table_clubs.get(club.getKey());
            clubs[i] = new ClubModel(
                    Integer.parseInt(club.getKey().replace('C', '0')),
                    club.getValue().get(0).toString(),
                    (HashMap<ClubAttributes, Integer>) club.getValue().get(1),
                    Long.parseLong(club.getValue().get(2).toString())
            );
            i++;
        }
    }

    private static PlayerPositions[] getPositions(String s) {
        List<PlayerPositions> temp_pos = new ArrayList<>(List.of());
        String[] positions = s.split("(, )");
        for (String position : positions) temp_pos.add(PlayerPositions.valueOf(PlayerPositions.class, position));
        PlayerPositions[] player_poss = {};
        player_poss = temp_pos.toArray(player_poss);
        return player_poss;
    }

    private static void setPlayerAttributes(ResultSet rs) throws SQLException {
        player_attributes = new HashMap<>();
        player_attributes.put(PlayerAttributes.PACE, rs.getInt("pace"));
        player_attributes.put(PlayerAttributes.SHOOTING, rs.getInt("shooting"));
        player_attributes.put(PlayerAttributes.PASSING, rs.getInt("passing"));
        player_attributes.put(PlayerAttributes.DRIBBLING, rs.getInt("dribbling"));
        player_attributes.put(PlayerAttributes.DEFENDING, rs.getInt("defending"));
        player_attributes.put(PlayerAttributes.PHYSIC, rs.getInt("physic"));
        player_attributes.put(PlayerAttributes.ATTACKING_CROSSING, rs.getInt("attacking_crossing"));
        player_attributes.put(PlayerAttributes.ATTACKING_FINISHING, rs.getInt("attacking_finishing"));
        player_attributes.put(PlayerAttributes.ATTACKING_HEADING_ACCURACY, rs.getInt("attacking_heading_accuracy"));
        player_attributes.put(PlayerAttributes.ATTACKING_SHORT_PASSING, rs.getInt("attacking_short_passing"));
        player_attributes.put(PlayerAttributes.ATTACKING_VOLLEYS, rs.getInt("attacking_volleys"));
        player_attributes.put(PlayerAttributes.SKILL_DRIBBLING, rs.getInt("skill_dribbling"));
        player_attributes.put(PlayerAttributes.SKILL_CURVE, rs.getInt("skill_curve"));
        player_attributes.put(PlayerAttributes.SKILL_FK_ACCURACY, rs.getInt("skill_fk_accuracy"));
        player_attributes.put(PlayerAttributes.SKILL_LONG_PASSING, rs.getInt("skill_long_passing"));
        player_attributes.put(PlayerAttributes.SKILL_BALL_CONTROL, rs.getInt("skill_ball_control"));
        player_attributes.put(PlayerAttributes.MOVEMENT_ACCELERATION, rs.getInt("movement_acceleration"));
        player_attributes.put(PlayerAttributes.MOVEMENT_SPRINT_SPEED, rs.getInt("movement_sprint_speed"));
        player_attributes.put(PlayerAttributes.MOVEMENT_REACTIONS, rs.getInt("movement_reactions"));
        player_attributes.put(PlayerAttributes.MOVEMENT_AGILITY, rs.getInt("movement_agility"));
        player_attributes.put(PlayerAttributes.MOVEMENT_BALANCE, rs.getInt("movement_balance"));
        player_attributes.put(PlayerAttributes.POWER_SHOT_POWER, rs.getInt("power_shot_power"));
        player_attributes.put(PlayerAttributes.POWER_JUMPING, rs.getInt("power_jumping"));
        player_attributes.put(PlayerAttributes.POWER_STAMINA, rs.getInt("power_stamina"));
        player_attributes.put(PlayerAttributes.POWER_STRENGTH, rs.getInt("power_strength"));
        player_attributes.put(PlayerAttributes.POWER_LONG_SHOTS, rs.getInt("power_long_shots"));
        player_attributes.put(PlayerAttributes.MENTALITY_AGGRESSION, rs.getInt("mentality_aggression"));
        player_attributes.put(PlayerAttributes.MENTALITY_INTERCEPTIONS, rs.getInt("mentality_interceptions"));
        player_attributes.put(PlayerAttributes.MENTALITY_POSITIONING, rs.getInt("mentality_positioning"));
        player_attributes.put(PlayerAttributes.MENTALITY_VISION, rs.getInt("mentality_vision"));
        player_attributes.put(PlayerAttributes.MENTALITY_PENALTIES, rs.getInt("mentality_penalties"));
        player_attributes.put(PlayerAttributes.MENTALITY_COMPOSURE, rs.getInt("mentality_composure"));
        player_attributes.put(PlayerAttributes.DEFENDING_MARKING_AWARENESS, rs.getInt("defending_marking_awareness"));
        player_attributes.put(PlayerAttributes.DEFENDING_STANDING_TACKLE, rs.getInt("defending_standing_tackle"));
        player_attributes.put(PlayerAttributes.DEFENDING_SLIDING_TACKLE, rs.getInt("defending_sliding_tackle"));
        player_attributes.put(PlayerAttributes.GOALKEEPING_DIVING, rs.getInt("goalkeeping_diving"));
        player_attributes.put(PlayerAttributes.GOALKEEPING_HANDLING, rs.getInt("goalkeeping_handling"));
        player_attributes.put(PlayerAttributes.GOALKEEPING_KICKING, rs.getInt("goalkeeping_kicking"));
        player_attributes.put(PlayerAttributes.GOALKEEPING_POSITIONING, rs.getInt("goalkeeping_positioning"));
        player_attributes.put(PlayerAttributes.GOALKEEPING_REFLEXES, rs.getInt("goalkeeping_reflexes"));
        player_attributes.put(PlayerAttributes.GOALKEEPING_SPEED, rs.getInt("goalkeeping_speed"));
    }

    private static void setPlayers() throws Exception {
        int i = 0;
        String query = "SELECT * FROM players";
        ResultSet rs = con.createStatement().executeQuery(query);
        while (rs.next()) {
            setPlayerAttributes(rs);
            players[i] = new PlayerModel(
                    Integer.parseInt(rs.getString("player_id").replace("P", "0")),
                    rs.getString("player_name"),
                    getPositions(rs.getString("player_positions")),
                    rs.getInt("overall"),
                    rs.getInt("potential"),
                    rs.getLong("value_eur"),
                    rs.getInt("age"),
                    rs.getInt("height_cm"),
                    rs.getInt("weight_kg"),
                    (String) table_clubs.get(rs.getString("club_id")).get(0),
                    PlayerPositions.valueOf(PlayerPositions.class, rs.getString("club_position")),
                    rs.getInt("club_jersey_number"),
                    rs.getInt("club_contract_valid_until"),
                    table_nationality.get(rs.getString("nationality_id")),
                    rs.getString("preferred_foot"),
                    rs.getInt("skill_moves"),
                    PlayerWorkRate.valueOf(PlayerWorkRate.class, Arrays.asList(rs.getString("work_rate").split("/")).get(0).toUpperCase()),
                    (rs.getString("player_traits").equals("NA") ? List.of().toArray(empty_array) : Arrays.asList(rs.getString("player_traits").split("(, )")).toArray(empty_array)),
                    player_attributes,
                    false,
                    0,
                    true
            );
            i++;
        }
    }
}
