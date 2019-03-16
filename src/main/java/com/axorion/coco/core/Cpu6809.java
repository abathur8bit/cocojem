/* *****************************************************************************
 * Copyright 2018 Lee Patterson <https://github.com/abathur8bit>
 *
 * You may use and modify at will. Please credit me in the source.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ******************************************************************************/

package com.axorion.coco.core;

import com.axorion.coco.core.mc6809.MnemonicEnum6809;

public class Cpu6809 extends Cpu {
    public static final String DEVICE_NAME = "MC6809";
    protected MnemonicEnum6809 mnemonic;
    Register regPC;
    Register regX;
    Register regY;
    Register regU;
    Register regS;
    Register regDP;
    Register regD;
    ConditionCodeRegister regCC;

    public Cpu6809(int[] regs) {
        this(regs[0],regs[1],regs[2],regs[3],regs[4],regs[5],regs[6],regs[7]);
    }

    public Cpu6809(int pc,int x,int y,int u,int s,int dp,int d,int cc) {
        super(DEVICE_NAME);
        this.regPC = new Register(pc);
        this.regX = new Register(x);
        this.regY = new Register(y);
        this.regU = new Register(u);
        this.regS = new Register(s);
        this.regDP = new Register(dp);
        this.regD = new Register(d);
        this.regCC = new ConditionCodeRegister(cc);
    }

    public Cpu6809() {
        this(0,0,0,0,0,0,0,0);
    }

    public void exec(long now) {
        int opcode = memory[regPC.getValue()];
        regPC.inc();
        if(opcode == 0x10 || opcode == 0x11) {
            opcode = opcode*0x100+memory[regPC.getValue()];
            regPC.inc();
        }
        mnemonic = MnemonicEnum6809.lookupMnemonic(opcode);

        switch(mnemonic) {
            case ABX_I : abx(); break;
            case CLRA_I: clra(); break;
            case CLRB_I: clrb(); break;
            case LDA_M: lda(); break;

            default: //todo lee throw exception as the instruction was not valid
                break;
        }
    }

    public void abx() {
        cycleCounter += mnemonic.getCycles();
        regX.setValue((regX.getValue()+regD.getLSB()) & Register.mask);
    }

    public void clra() {
        cycleCounter += mnemonic.getCycles();
        regD.setValue(0,regD.getLSB());
        regCC.setNegative(false).setZero(true).setOverflow(false).setCarry(false);
    }

    public void clrb() {
        cycleCounter += mnemonic.getCycles();
        regD.setValue(regD.getMSB(),0);
        regCC.setNegative(false).setZero(true).setOverflow(false).setCarry(false);
    }

    public void lda() {
        int operand = getOperand8bit(mnemonic.getMode());
        regD.setValue(operand,regD.getLSB());
        regCC.setZero(operand==0).setNegative((operand&0x80)==0x80).setOverflow(false);
        regPC.inc();
    }

    public int getEffectiveAddress(AddressMode mode) {
        int effectiveAddr = 0;
        switch(mode) {
            case Immediate  : effectiveAddr = regPC.getValue(); break;
            case Direct     : effectiveAddr = memory[regDP.getValue()]*0x100+memory[regPC.getValue()]; break;
        }
        return effectiveAddr;
    }
    protected int getOperand8bit(AddressMode mode) {
        int operand = memory[getEffectiveAddress(mode)];
        return operand;
    }
    protected int getOperand16bit(AddressMode mode) {
        int effectiveAddr = getEffectiveAddress(mode);
        int operand = memory[effectiveAddr]*0x100+memory[effectiveAddr+1];
        return operand;
    }
    public static String getDeviceName() {
        return DEVICE_NAME;
    }

    public Register getRegPC() {
        return regPC;
    }

    public void setRegPC(Register pc) {
        this.regPC = pc;
    }

    public Register getRegX() {
        return regX;
    }

    public void setRegX(Register x) {
        this.regX = x;
    }

    public Register getRegY() {
        return regY;
    }

    public void setRegY(Register y) {
        this.regY = y;
    }

    public Register getRegU() {
        return regU;
    }

    public void setRegU(Register u) {
        this.regU = u;
    }

    public Register getRegS() {
        return regS;
    }

    public void setS(Register s) {
        this.regS = s;
    }

    public Register getRegDP() {
        return regDP;
    }

    public void setRegDP(Register dp) {
        this.regDP = dp;
    }

    public Register getRegD() {
        return regD;
    }

    public void setRegD(Register d) {
        this.regD = d;
    }

    public ConditionCodeRegister getRegCC() {
        return regCC;
    }

    //Careful, you are setting a new regCC register, not setting the value of the existing one.
    public void setRegCC(ConditionCodeRegister regCC) {
        this.regCC = regCC;
    }
}
