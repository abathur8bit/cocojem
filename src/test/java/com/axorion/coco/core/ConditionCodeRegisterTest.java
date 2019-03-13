package com.axorion.coco.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConditionCodeRegisterTest {

    @Test
    void setAll() {
        ConditionCodeRegister target = new ConditionCodeRegister();
        target.setAll(0xFF);
        assertTrue(target.isEntire());
        assertTrue(target.isFirq());
        assertTrue(target.isHalfCarry());
        assertTrue(target.isIrqMask());
        assertTrue(target.isNegative());
        assertTrue(target.isZero());
        assertTrue(target.isOverflow());
        assertTrue(target.isCarry());
    }

    @Test
    void getAll() {
        ConditionCodeRegister target = new ConditionCodeRegister();
        target.setEntire(true);
        target.setFirq(true);
        target.setHalfCarry(true);
        target.setIrqMask(true);
        target.setNegative(true);
        target.setZero(true);
        target.setOverflow(true);
        target.setCarry(true);
        assertEquals(0xFF,target.getAll());
    }

    @Test
    void clearAll() {
        ConditionCodeRegister target = new ConditionCodeRegister();
        target.setEntire(true);
        target.setFirq(true);
        target.setHalfCarry(true);
        target.setIrqMask(true);
        target.setNegative(true);
        target.setZero(true);
        target.setOverflow(true);
        target.setCarry(true);
        assertEquals(0xFF,target.getAll());
        target.clearAll();
        assertEquals(0,target.getAll());
    }

    @Test
    void singleBits() {
        ConditionCodeRegister target = new ConditionCodeRegister();
        target.setZero(true);
        assertEquals(ConditionCodeRegister.ZERO_MASK,target.getAll());
        target.setCarry(true);
        assertEquals(ConditionCodeRegister.ZERO_MASK+ConditionCodeRegister.CARRY_MASK,target.getAll());
    }

    @Test
    void combineSets() {
        ConditionCodeRegister target = new ConditionCodeRegister();

        target.setNegative(true).setZero(true);
        assertFalse(target.isEntire());
        assertFalse(target.isFirq());
        assertFalse(target.isHalfCarry());
        assertFalse(target.isIrqMask());
        assertTrue (target.isNegative());
        assertTrue (target.isZero());
        assertFalse(target.isOverflow());
        assertFalse(target.isCarry());
        assertEquals(0x0C,target.getAll());
    }
}