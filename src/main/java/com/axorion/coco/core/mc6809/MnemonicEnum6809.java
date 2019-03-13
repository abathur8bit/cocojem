package com.axorion.coco.core.mc6809;

import com.axorion.coco.core.AddressMode;

public enum MnemonicEnum6809 {
    //(D) Direct (I) Inherent (R) Relative (M) Immediate (X) Indexed (E) extened

    INCA_I(0x4C,2,AddressMode.INHERENT),
    INCB_I(0x5C,2,AddressMode.INHERENT),
    ABX_M (0x3A,3,AddressMode.INHERENT),
    CLRA  (0x4F,2,AddressMode.INHERENT),
    CLRB  (0x5F,2,AddressMode.INHERENT);

    private final int opcode;
    private final int cycles;
    private final AddressMode mode;

    MnemonicEnum6809(int opcode,int cycles,AddressMode mode) {
        this.opcode = opcode;
        this.cycles = cycles;
        this.mode = mode;
    }
    public static MnemonicEnum6809 lookupMnemonic(int opcode) {
        for(MnemonicEnum6809 e : values()) {
            if(e.opcode == opcode) {
                return e;
            }
        }
        return null;
    }
    public int getOpcode() {
        return opcode;
    }
    public int getCycles() {
        return cycles;
    }
    public AddressMode getMode() {
        return mode;
    }
}
