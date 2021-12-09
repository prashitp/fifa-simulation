package com.gameplay.controller;

import com.models.SetPieceType;

import java.util.HashMap;
import java.util.List;

/**
 * @author vasugamdha
 */

public interface ISetPieceController {

    HashMap<SetPieceType, List<Integer>> getSetPieces();
}
