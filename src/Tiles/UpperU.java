package Tiles;

import ImageLoad.Assets;

public class UpperU extends Tile {
    public UpperU(int id) {
        super(id, Assets.upperU);
    }
    @Override
    public boolean isSolid(){
        return true;
    }
}
