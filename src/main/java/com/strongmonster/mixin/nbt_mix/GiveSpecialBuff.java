package com.strongmonster.mixin.nbt_mix;

public class GiveSpecialBuff implements SpecialBuffAccess{
    private boolean sbuff;

    @Override
    public boolean getSBuff() {
        return sbuff;
    }

    @Override
    public void setSBuff(boolean value) {
        this.sbuff = value;
    }
}
