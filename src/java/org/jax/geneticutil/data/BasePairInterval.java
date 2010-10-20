/*
 * Copyright (c) 2010 The Jackson Laboratory
 * 
 * This is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this software.  If not, see <http://www.gnu.org/licenses/>.
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
