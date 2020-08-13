package Tiles;

import ImageLoad.Assets;

public class LowerCorner extends Tile {
    public LowerCorner(int id) {
        super(id, Assets.lowerCorner);
    }
    @Override
    public boolean isSolid(){
        return true;
    }
}
