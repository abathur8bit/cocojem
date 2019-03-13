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
    Register pc;
    Register x;
    Register y;
    Register u;
    Register s;
    Register dp;
    Register d;
    ConditionCodeRegister cc;

    public Cpu6809(int[] regs) {
        this(regs[0],regs[1],regs[2],regs[3],regs[4],regs[5],regs[6],regs[7]);
    }

    public Cpu6809(int pc,int x,int y,int u,int s,int dp,int d,int cc) {
        super(DEVICE_NAME);
        this.pc = new Register(pc);
        this.x = new Register(x);
        this.y = new Register(y);
        this.u = new Register(u);
        this.s = new Register(s);
        this.dp = new Register(dp);
        this.d = new Register(d);
        this.cc = new ConditionCodeRegister(cc);
    }

    public Cpu6809() {
        super(DEVICE_NAME);
    }

    public void exec(long now) {
        int opcode = memory[pc.getReg()];
        if(opcode == 0x10 || opcode == 0x11) {
            opcode = opcode*0x100+memory[pc.getReg()];
        }
        mnemonic = MnemonicEnum6809.lookupMnemonic(opcode);

        switch(mnemonic) {
            case ABX_M: abx(); break;
            case CLRA: clra(); break;
            case CLRB: clrb(); break;

            default: //todo lee throw exception as the instruction was not valid
                break;
        }

//        if(pc.getReg() > 0) { //this seems to be the case when I'm running in edtasm, is this right?
//            cc.setEntire(true);
//        }
    }

    public void abx() {
        cycleCounter += mnemonic.getCycles();
        x.setReg((x.getReg()+d.getLSB()) & Register.mask);
        pc.inc();
    }

    public void clra() {
        cycleCounter += mnemonic.getCycles();
        d.setReg(0,d.getLSB());
        cc.setNegative(false);
        cc.setZero(true);
        cc.setOverflow(false);
        cc.setCarry(false);
        pc.inc();
    }

    public void clrb() {
        cycleCounter += mnemonic.getCycles();
        d.setReg(d.getMSB(),0);
        cc.setNegative(false);
        cc.setZero(true);
        cc.setOverflow(false);
        cc.setCarry(false);
        pc.inc();
    }

    public static String getDeviceName() {
        return DEVICE_NAME;
    }

    public Register getPc() {
        return pc;
    }

    public void setPc(Register pc) {
        this.pc = pc;
    }

    public Register getX() {
        return x;
    }

    public void setX(int x) {
        this.x.setReg(x);
    }

    public Register getY() {
        return y;
    }

    public void setY(Register y) {
        this.y = y;
    }

    public Register getU() {
        return u;
    }

    public void setU(Register u) {
        this.u = u;
    }

    public Register getS() {
        return s;
    }

    public void setS(Register s) {
        this.s = s;
    }

    public Register getDp() {
        return dp;
    }

    public void setDp(Register dp) {
        this.dp = dp;
    }

    public Register getD() {
        return d;
    }

    public void setD(Register d) {
        this.d = d;
    }

    public ConditionCodeRegister getCc() {
        return cc;
    }

    //Careful, you are setting a new cc register, not setting the value of the existing one.
    public void setCc(ConditionCodeRegister cc) {
        this.cc = cc;
    }
}
