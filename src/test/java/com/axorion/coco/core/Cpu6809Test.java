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
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Cpu6809Test {
    //(D) Direct (I) Inherent (R) Relative (M) Immediate (X) Indexed (E) extened
    //pcReg x y u s dp d cc
    @Test
    public void abx_i() {
        int[] mem = {MnemonicEnum6809.ABX_I.getOpcode()};
        int[] regs = {0,1,0,0,0,0,0x1001,0xFF};
        Cpu6809 cpu = new Cpu6809(regs);
        cpu.setMemoryRange(0,mem);
        cpu.exec(System.nanoTime());
        int[] regsResult = {1,2,0,0,0,0,0x1001,0xFF};
        verifyRegisters(cpu,regsResult);
    }
    @Test
    public void clra_i() {
        int[] mem = {MnemonicEnum6809.CLRA_I.getOpcode()};
        //                                                       EFHINZVC
        int[] regs = {0,0,0,0,0,0,0xAABB,      Integer.parseInt("11111011",2)};
        Cpu6809 cpu = new Cpu6809(regs);
        cpu.setMemoryRange(0,mem);
        cpu.exec(System.nanoTime());
        //                                                       EFHINZVC
        int[] regsResult = {1,0,0,0,0,0,0x00BB,Integer.parseInt("11110100",2)};
        verifyRegisters(cpu,regsResult);
    }
    @Test
    public void clrb_i() {
        int[] mem = {MnemonicEnum6809.CLRB_I.getOpcode()};
        //                                                       EFHINZVC
        int[] regs = {0,0,0,0,0,0,0xAABB,      Integer.parseInt("11111011",2)};
        Cpu6809 cpu = new Cpu6809(regs);
        cpu.setMemoryRange(0,mem);
        cpu.exec(System.nanoTime());
        //                                                       EFHINZVC
        int[] regsResult = {1,0,0,0,0,0,0xAA00,Integer.parseInt("11110100",2)};
        verifyRegisters(cpu,regsResult);
    }
    @Test
    public void ldaImmediate_8bit_NotNegative_Nonzero() {
        int[] mem = {MnemonicEnum6809.LDA_M.getOpcode(),0x01};
        //                     pc     x      y      u      s      dp   d      cc                EFHINZVC
        int[] regs          = {0x0000,0x0000,0x0000,0x0000,0x0000,0x00,0xAABB,Integer.parseInt("00001011",2)};
        Cpu6809 cpu = new Cpu6809(regs);
        cpu.setMemoryRange(0,mem);
        cpu.exec(System.nanoTime());
        //                     pc     x      y      u      s      dp   d      cc                EFHINZVC
        int[] regsResult    = {0x0002,0x0000,0x0000,0x0000,0x0000,0x00,0x01BB,Integer.parseInt("00000001",2)};
        verifyRegisters(cpu,regsResult);
    }
    @Test
    public void ldaImmediate_8bit_NotNegative_Zero() {
        int[] mem = {MnemonicEnum6809.LDA_M.getOpcode(),0x00};
        //                     pc     x      y      u      s      dp   d      cc                EFHINZVC
        int[] regs          = {0x0000,0x0000,0x0000,0x0000,0x0000,0x00,0xAABB,Integer.parseInt("00000111",2)};
        Cpu6809 cpu = new Cpu6809(regs);
        cpu.setMemoryRange(0,mem);
        cpu.exec(System.nanoTime());
        //                     pc     x      y      u      s      dp   d      cc                EFHINZVC
        int[] regsResult    = {0x0002,0x0000,0x0000,0x0000,0x0000,0x00,0x00BB,Integer.parseInt("00000101",2)};
        verifyRegisters(cpu,regsResult);
    }
    @Test
    public void ldaImmediate_8bit_Negative_Nonzero() {
        int[] mem = {MnemonicEnum6809.LDA_M.getOpcode(),0xFF};
        //                     pc     x      y      u      s      dp   d      cc                EFHINZVC
        int[] regs          = {0x0000,0x0000,0x0000,0x0000,0x0000,0x00,0xAABB,Integer.parseInt("00001011",2)};
        Cpu6809 cpu = new Cpu6809(regs);
        cpu.setMemoryRange(0,mem);
        cpu.exec(System.nanoTime());
        //                     pc     x      y      u      s      dp   d      cc                EFHINZVC
        int[] regsResult    = {0x0002,0x0000,0x0000,0x0000,0x0000,0x00,0xFFBB,Integer.parseInt("00001001",2)};
        verifyRegisters(cpu,regsResult);
    }
    @Test
    public void testMemory() {
        Cpu6809 cpu = new Cpu6809();
        int[] memory = cpu.getMemory();
        cpu.poke(0,0xFF);
        assertEquals(0xFF,memory[0]);
        assertEquals(0xFF,cpu.peek(0));
    }
    @Test
    public void memoryRange() {
        int[] mem = {1,2,3,4};
        int offset = 0;
        Cpu6809 cpu = new Cpu6809();
        cpu.setMemoryRange(0,mem);
        verifyMemory(cpu,offset,mem);

        offset = 0xe00;
        cpu.setMemoryRange(offset,mem);
        verifyMemory(cpu,offset,mem);
    }
    @Test
    public void testRegs() {
        int[] regs = {1,2,3,4,5,6,7,8};
        Cpu6809 cpu = new Cpu6809(1,2,3,4,5,6,7,8);
        verifyRegisters(cpu,regs);
        cpu = new Cpu6809(regs);
        verifyRegisters(cpu,regs);
    }
    @Test
    public void getEffectiveAddressImmediate() {
        int[] mem = {4,5,6,7,5, 2 ,7,8,9};
        Cpu6809 cpu = new Cpu6809();
        cpu.setMemoryRange(0,mem);
        cpu.getRegPC().setValue(5);
        assertEquals(5,cpu.getEffectiveAddress(AddressMode.Immediate));
    }
    @Test
    public void getEffectiveAddressDirect() {
        int[] mem = {0x04,0x05,0x06,0x01,0x02,0x03};
        Cpu6809 cpu = new Cpu6809();
        cpu.setMemoryRange(0,mem);

        cpu.getRegPC().setValue(0);
        cpu.getRegDP().setValue(0);
        assertEquals(0x0404,cpu.getEffectiveAddress(AddressMode.Direct));

        cpu.getRegPC().setValue(5);
        cpu.getRegDP().setValue(2);
        assertEquals(0x0603,cpu.getEffectiveAddress(AddressMode.Direct));
    }
    @Test
    public void getOperand() {
        int[] mem = {0,1,5,6,7,8};
        Cpu6809 cpu = new Cpu6809();
        cpu.setMemory(mem);

        cpu.getRegPC().setValue(1);
        assertEquals(1,cpu.getOperand8bit(AddressMode.Immediate));
        cpu.getRegPC().setValue(2);
        assertEquals(0x08,cpu.getOperand8bit(AddressMode.Direct));
    }


    protected void verifyMemory(Cpu cpu,int offset,int[] mem) {
        for(int i=0; i<mem.length; i++) {
            assertEquals(mem[i],cpu.peek(offset+i));
        }
    }
    protected void verifyRegisters(Cpu6809 cpu,int[] regs) {
        int i=0;
        assertEquals(regs[i++], cpu.getRegPC().getValue());
        assertEquals(regs[i++], cpu.getRegX() .getValue());
        assertEquals(regs[i++], cpu.getRegY() .getValue());
        assertEquals(regs[i++], cpu.getRegU() .getValue());
        assertEquals(regs[i++], cpu.getRegS() .getValue());
        assertEquals(regs[i++], cpu.getRegDP().getValue());
        assertEquals(regs[i++], cpu.getRegD() .getValue());
        assertEquals(regs[i++], cpu.getRegCC().getAll());
    }

}
