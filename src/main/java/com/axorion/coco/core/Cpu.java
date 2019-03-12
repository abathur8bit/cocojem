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

public class Cpu extends EmulatorDevice {
    public static String DEV_TYPE = "cpu";
    protected int cycleCounter;
    protected int [] memory = new int[512*1024];

    public Cpu(String name) {
        super(name,DEV_TYPE);
    }

    public void init() {
        cycleCounter = 0;
    }

    public void exec(long now) {
        System.out.println("CPU tick "+now);
    }

    public void reset() {

    }

    public void addCycleCounter(int amount) {
        cycleCounter += amount;
    }

    public int getCycleCounter() {
        return cycleCounter;
    }

    public void setMemoryRange(int offset,int[] values) {
        for(int i=0; i<values.length; i++) {
            memory[offset+i] = values[i];
        }
    }

    public void poke(int addr,int value) {
        memory[addr] = value&BYTE_MASK;
    }

    public int peek(int addr) {
        return memory[addr];
    }

    public int[] getMemory() {
        return memory;
    }

    public void setMemory(int[] memory) {
        this.memory = memory;
    }
}
