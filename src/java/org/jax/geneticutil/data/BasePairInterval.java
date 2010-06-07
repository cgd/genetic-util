/*
 * Copyright (c) 2008 The Jackson Laboratory
 *
 * Permission is hereby granted, free of charge, to any person obtaining  a copy
 * of this software and associated documentation files (the  "Software"), to
 * deal in the Software without restriction, including  without limitation the
 * rights to use, copy, modify, merge, publish,  distribute, sublicense, and/or
 * sell copies of the Software, and to  permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be  included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,  EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF  MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY  CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,  TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE  SOFTWARE OR THE
 * USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.jax.geneticutil.data;

import java.io.Serializable;

/**
 * An interface for describing an interval with base pair coordinates
 * @author <A HREF="mailto:keith.sheppard@jax.org">Keith Sheppard</A>
 */
public interface BasePairInterval
extends Serializable, Comparable<BasePairInterval>
{
    /**
     * Getter for the starting SNP position.
     * @return
     *          the ending SNP position
     */
    public long getStartInBasePairs();
    
    /**
     * Getter for the ending SNP. This is just a convenience function
     * that returns the same result as calling
     * {@link #getStartInBasePairs()} + {@link #getExtentInBasePairs()} - 1
     * @return
     *          the ending SNP
     */
    public long getEndInBasePairs();
    
    /**
     * Getter for the extent in SNPs (how many snps forward does
     * this haplotype extend)
     * @return the extentInSnps
     */
    public long getExtentInBasePairs();

    /**
     * The chromosome number that this haplotype applys to
     * @return
     *          the chromosome number
     */
    public int getChromosomeNumber();
    
    /**
     * Convenience version of {@link #contains(long, long)}
     * @param otherSnpInterval
     *          the other interval
     * @return
     *          true if the other block is contained
     */
    public boolean contains(BasePairInterval otherSnpInterval);
    
    /**
     * See if this block contains the given other block
     * @param otherStartInBasePairs
     *          the other block's start
     * @param otherExtentInBasePairs
     *          the other block's extent
     * @return
     *          true iff the other block is contained by this block
     */
    public boolean contains(
            long otherStartInBasePairs,
            long otherExtentInBasePairs);
    
    /**
     * See if this block intersects the given other block
     * @param otherInterval
     *          the other interval that we're checking for intersection
     * @return
     *          true iff the other block is intersected by this block
     */
    public boolean intersects(BasePairInterval otherInterval);
    
    /**
     * See if this block intersects the given other block
     * @param otherStartInBasePairs
     *          the other block's start
     * @param otherExtentInBasePairs
     *          the other block's extent
     * @return
     *          true iff the other block is intersected by this block
     */
    public boolean intersects(
            long otherStartInBasePairs,
            long otherExtentInBasePairs);
    
    /**
     * Get the overlap between this block and the other
     * @param otherStartInBasePairs
     *          the other block's start
     * @param otherExtentInBasePairs
     *          the other block's extent
     * @return
     *          the overlap in base pairs
     */
    public long getOverlapInBasePairs(
            long otherStartInBasePairs,
            long otherExtentInBasePairs);
    
    /**
     * Get the overlap between this block and the other
     * @param otherSnpBlock
     *          the other SNP block that we're calculating overlap for
     * @return
     *          the overlap in base pairs
     */
    public long getOverlapInBasePairs(
            BasePairInterval otherSnpBlock);
}
