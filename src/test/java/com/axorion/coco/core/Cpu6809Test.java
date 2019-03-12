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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Cpu6809Test {
    //(D) Direct (I) Inherent (R) Relative (M) Immediate (X) Indexed (E) extened
    //pc x y u s dp d cc
    @Test public void abx_m() {
        int[] mem = {Cpu6809.ABX_M};
        int[] regs = {0,1,0,0,0,0,0x1001,0};
        Cpu6809 cpu = new Cpu6809(regs);
        cpu.setMemoryRange(0,mem);
        cpu.exec(System.nanoTime());
        int[] regsResult = {1,2,0,0,0,0,0x1001,0x80};
        verifyRegisters(cpu,regsResult);
    }
    @Test public void testMemory() {
        Cpu6809 cpu = new Cpu6809();
        int[] memory = cpu.getMemory();
        cpu.poke(0,0xFF);
        assertEquals(0xFF,memory[0]);
        assertEquals(0xFF,cpu.peek(0));
    }
    @Test public void memoryRange() {
        int[] mem = {1,2,3,4};
        int offset = 0;
        Cpu6809 cpu = new Cpu6809();
        cpu.setMemoryRange(0,mem);
        verifyMemory(cpu,offset,mem);

        offset = 0xe00;
        cpu.setMemoryRange(offset,mem);
        verifyMemory(cpu,offset,mem);
    }

    @Test public void testRegs() {
        int[] regs = {1,2,3,4,5,6,7,8};
        Cpu6809 cpu = new Cpu6809(1,2,3,4,5,6,7,8);
        verifyRegisters(cpu,regs);
        cpu = new Cpu6809(regs);
        verifyRegisters(cpu,regs);
    }


    protected void verifyMemory(Cpu cpu,int offset,int[] mem) {
        for(int i=0; i<mem.length; i++) {
            assertEquals(mem[i],cpu.peek(offset+i));
        }
    }

    protected void verifyRegisters(Cpu6809 cpu,int[] regs) {
//        Register pc;
//        Register x;
//        Register y;
//        Register u;
//        Register s;
//        Register dp;
//        Register d;
//        Register cc;
        int i=0;
        assertEquals(regs[i++], cpu.getPc().getReg());
        assertEquals(regs[i++], cpu.getX() .getReg());
        assertEquals(regs[i++], cpu.getY() .getReg());
        assertEquals(regs[i++], cpu.getU() .getReg());
        assertEquals(regs[i++], cpu.getS() .getReg());
        assertEquals(regs[i++], cpu.getDp().getReg());
        assertEquals(regs[i++], cpu.getD() .getReg());
        assertEquals(regs[i++], cpu.getCc().getReg());
    }
}
