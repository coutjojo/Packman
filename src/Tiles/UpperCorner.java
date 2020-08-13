package Tiles;

import ImageLoad.Assets;

public class UpperCorner extends Tile {
    public UpperCorner(int id) {
        super(id, Assets.upperCorner);
    }
    @Override
    public boolean isSolid(){
        return true;
    }
}
