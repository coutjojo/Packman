package Tiles;

import ImageLoad.Assets;

public class RightleftTunnel extends Tile {
    public RightleftTunnel(int id) {
        super(id, Assets.rightleftTunnel);
    }
    @Override
    public boolean isSolid(){
        return true;
    }
}
