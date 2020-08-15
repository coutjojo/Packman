package Tiles;

import ImageLoad.Assets;

public class LowerU extends Tile{
    public LowerU(int id) {
        super(id, Assets.lowerU);
    }
    @Override
    public boolean isSolid(){
        return true;
    }
}
