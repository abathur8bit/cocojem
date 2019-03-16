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

public class Register {
    public static final int mask = 0xFFFF;
    protected int reg;
    public Register(int value) {
        this.reg = value;
    }
    public void setValue(int value) {
        reg = value&mask;
    }
    public void setValue(int msb,int lsb) {
        reg = (msb&0xFF)*0x100 + (lsb&0xFF);
    }
    public int getLSB() {
        return reg & 0x00FF;
    }
    public int getMSB() {
        return (reg & 0xFF00) / 0x100;
    }
    public int getValue() {
        return reg;
    }
    public int inc() {
        reg = (reg+1)&mask;
        return reg;
    }
    public int dec() {
        reg = (reg-1)&mask;
        return reg;
    }
    public int bit(int b) {
        int n = 1<<b;
        if((reg&n) == n)
            return 1;
        return 0;
    }
    public int setBit(int b) {
        int n = 1<<b;
        reg = (reg | n) & mask;
        return reg;
    }
}
