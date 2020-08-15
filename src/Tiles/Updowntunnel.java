package Tiles;

import ImageLoad.Assets;

public class Updowntunnel extends Tile{
    public Updowntunnel(int id) {
        super(id, Assets.updowntunnel);
    }
    @Override
    public boolean isSolid(){
        return true;
    }
}
