package com.gameplay.service;

import com.models.SetPieceType;

import java.util.HashMap;
import java.util.List;

/**
 * @author vasugamdha
 */

public interface ISetPieceService {

    HashMap<SetPieceType, List<Integer>> getSetPieces();
}
