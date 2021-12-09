package com.utils;

import com.gameplay.entity.Column;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

/**
 * @author Jay Patel
 */
public class Converter {

	public static LogService logService = new LogService();

	public static <T> List<T> resultSetToEntityList(ResultSet resultSet, Class<T> returnType) {
		try {
			List<Field> fields = Arrays.asList(returnType.getDeclaredFields());

			for (Field field : fields) {
				field.setAccessible(true);
			}

			List<T> list = new ArrayList<>();
			while (resultSet.next()) {
				try {
					T dto = returnType.getConstructor().newInstance(); // public default constructor needed
					for (Field field : fields) {
						Column col = field.getAnnotation(Column.class);
						if (col == null) {
							continue;
						}
						String name = col.name();

						String value = resultSet.getString(name);
						field.set(dto, field.getType().getConstructor(String.class).newInstance(value));
					}
					list.add(dto);
				} catch (Exception e) {
					logService.log(Level.WARNING, e.getMessage());
				}
			}
			return list;
		} catch (Exception e) {
			logService.log(Level.WARNING, e.getMessage());
		}
		return null;
	}

	public static String convertToPlayerIdString(Integer playerId) {
		StringBuilder builder = new StringBuilder("P");
		if (playerId >= 100) { // 'P _ _ _'
			builder.append(playerId.toString());
		} else if (playerId >= 10) { // 'P 0 _ _;'
			builder.append("0");
			builder.append(playerId.toString());
		} else { // 'P 0 0 _ '
			builder.append("00");
			builder.append(playerId.toString());
		}
		return builder.toString();
	}

	public static Integer convertToPlayerIdInteger(String playerId) {
		return Integer.parseInt(playerId.substring(1));
	}

	public static String convertToTeamIdString(Integer teamId) {
		StringBuilder builder = new StringBuilder("C");
		if (teamId >= 10) {
			builder.append(teamId.toString());
		} else {
			builder.append("0");
			builder.append(teamId.toString());
		}
		return builder.toString();
	}

	public static Integer convertToTeamIdInteger(String teamId) {
		return Integer.parseInt(teamId.substring(1));
	}
}