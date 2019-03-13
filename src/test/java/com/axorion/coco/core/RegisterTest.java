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

class RegisterTest {
    @Test public void testReg() {
        Register target = new Register(0xABCD);
        assertEquals(0xABCD,target.getReg());
    }
    @Test public void testLsb() {
        Register target = new Register(0xABCD);
        assertEquals(0xCD,target.getLSB());
    }
    @Test public void testMsb() {
        Register target = new Register(0xABCD);
        assertEquals(0xAB,target.getMSB());
    }
    @Test public void testDec() {
        Register target = new Register(1);
        target.dec();
        assertEquals(0,target.getReg());
        target.dec();
        assertEquals(0xFFFF,target.getReg());
    }
    @Test public void testInc() {
        Register target = new Register(0xFFFE);
        target.inc();
        assertEquals(0xFFFF,target.getReg());
        assertEquals(0xFF,target.getLSB());
        assertEquals(0xFF,target.getMSB());
        target.inc();
        assertEquals(0,target.getReg());
        assertEquals(0,target.getLSB());
        assertEquals(0,target.getMSB());
    }
    @Test public void setValueNormal() {
        Register target = new Register(0xABCD);
        target.setReg(0x1234);
        assertEquals(0x1234,target.getReg());
    }
    @Test public void setValueInvalid() {
        Register target = new Register(0xABCD);
        target.setReg(0xF1234);
        assertEquals(0x1234,target.getReg());
    }
    @Test public void bitValid() {
        Register target = new Register(5);
        assertEquals(1,target.bit(0));
        assertEquals(0,target.bit(1));
        assertEquals(1,target.bit(2));
    }
    @Test public void setBit() {
        Register target = new Register(1);
        target.setBit(1);
        assertEquals(3,target.getReg());
        target.setBit(2);
        assertEquals(7,target.getReg());
    }
    @Test public void testSetRegMsbLsb() {
        Register target = new Register(0);
        target.setReg(0xAC,0xDC);
        assertEquals(0xAC,target.getMSB());
        assertEquals(0xDC,target.getLSB());
        assertEquals(0xACDC,target.getReg());
    }
}