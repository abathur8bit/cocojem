package com.axorion.coco.core;

/** Condition code register.
 *                                        Condition Code Register
 *                                   -------------------------------
 *                                  | E | F | H | I | N | Z | V | C |
 *                                   -------------------------------
 *                     Entire flag____|   |   |   |   |   |   |   |____Carry flag
 *                       FIRQ mask________|   |   |   |   |   |________Overflow
 *                      Half carry____________|   |   |   |____________Zero
 *                        IRQ mask________________|   |________________Negative
 */
public class ConditionCodeRegister {
    public static final int ENTIRE_MASK     = 0x80;
    public static final int FIRQ_MASK       = 0x40;
    public static final int HALF_CARRY_MASK = 0x20;
    public static final int IRQ_MASK_MASK   = 0x10;
    public static final int NEGATIVE_MASK   = 0x08;
    public static final int ZERO_MASK       = 0x04;
    public static final int OVERFLOW_MASK   = 0x02;
    public static final int CARRY_MASK      = 0x01;

    boolean entire;
    boolean firq;
    boolean halfCarry;
    boolean irqMask;
    boolean negative;
    boolean zero;
    boolean overflow;
    boolean carry;

    public ConditionCodeRegister() {
        clearAll();
    }

    public ConditionCodeRegister(int cc) {
        setAll(cc);
    }

    /**
     * Set all flags at once using an ORed value.
     * If you pass in 0x40 only the FIRQ flag is set.
     **/
    public void setAll(int cc) {
        clearAll();
        if((cc&ENTIRE_MASK)     == ENTIRE_MASK      ) entire = true;
        if((cc&FIRQ_MASK)       == FIRQ_MASK        ) firq = true;
        if((cc&HALF_CARRY_MASK) == HALF_CARRY_MASK  ) halfCarry= true;
        if((cc&IRQ_MASK_MASK)   == IRQ_MASK_MASK    ) irqMask = true;
        if((cc&NEGATIVE_MASK)   == NEGATIVE_MASK    ) negative = true;
        if((cc&ZERO_MASK)       == ZERO_MASK        ) zero = true;
        if((cc&OVERFLOW_MASK)   == OVERFLOW_MASK    ) overflow = true;
        if((cc&CARRY_MASK)      == CARRY_MASK       ) carry = true;
    }

    /** Get the all the flags as a single byte. */
    public int getAll() {
        int combined  = 0;
        if(entire) combined += ENTIRE_MASK;
        if(firq) combined += FIRQ_MASK;
        if(halfCarry) combined += HALF_CARRY_MASK;
        if(irqMask) combined += IRQ_MASK_MASK;
        if(negative) combined += NEGATIVE_MASK;
        if(zero) combined += ZERO_MASK;
        if(overflow) combined += OVERFLOW_MASK;
        if(carry) combined += CARRY_MASK;
        return combined;
    }

    /** Clear all flags at once. */
    public void clearAll() {
        entire = false;
        firq = false;
        halfCarry = false;
        irqMask = false;
        negative = false;
        zero = false;
        overflow = false;
        carry = false;
    }

    public boolean isEntire() {
        return entire;
    }

    public ConditionCodeRegister setEntire(boolean entire) {
        this.entire = entire;
        return this;
    }

    public boolean isFirq() {
        return firq;
    }

    public ConditionCodeRegister setFirq(boolean firq) {
        this.firq = firq;
        return this;
    }

    public boolean isHalfCarry() {
        return halfCarry;
    }

    public ConditionCodeRegister setHalfCarry(boolean halfCarry) {
        this.halfCarry = halfCarry;
        return this;
    }

    public boolean isIrqMask() {
        return irqMask;
    }

    public ConditionCodeRegister setIrqMask(boolean irqMask) {
        this.irqMask = irqMask;
        return this;
    }

    public boolean isNegative() {
        return negative;
    }

    public ConditionCodeRegister setNegative(boolean negative) {
        this.negative = negative;
        return this;
    }

    public boolean isZero() {
        return zero;
    }

    public ConditionCodeRegister setZero(boolean zero) {
        this.zero = zero;
        return this;
    }

    public boolean isOverflow() {
        return overflow;
    }

    public ConditionCodeRegister setOverflow(boolean overflow) {
        this.overflow = overflow;
        return this;
    }

    public boolean isCarry() {
        return carry;
    }

    public ConditionCodeRegister setCarry(boolean carry) {
        this.carry = carry;
        return this;
    }
}
