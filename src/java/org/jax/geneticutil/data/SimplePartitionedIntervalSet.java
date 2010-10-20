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

import java.util.BitSet;


/**
 * A data structure that contains a set of (potentially) non-contiguous
 * haplotype blocks that are all associated with the same strain set
 * (represented by the bits of a {@link BitSet})
 * @author <A HREF="mailto:keith.sheppard@jax.org">Keith Sheppard</A>
 */
public class SimplePartitionedIntervalSet implements PartitionedIntervalSet
{
    private final BasePairInterval[] snpIntervals;
    
    private final BitSet strainBitSet;
    
    private final long cumulativeExtentInBasePairs;
    
    /**
     * Convenience constructor for when there is only a single partition
     * @param strainBitSet
     * @param snpInterval
     */
    public SimplePartitionedIntervalSet(
            BitSet strainBitSet,
            BasePairInterval snpInterval)
    {
        this(strainBitSet, new BasePairInterval[] {snpInterval});
    }
    
    /**
     * Constructor
     * @param strainBitSet
     *          the strain names
     * @param snpIntervals
     *          this snp blocks
     */
    public SimplePartitionedIntervalSet(
            BitSet strainBitSet,
            BasePairInterval[] snpIntervals)
    {
        this.strainBitSet = strainBitSet;
        this.snpIntervals = snpIntervals;
        
        long cumulativeExtent = 0L;
        for(BasePairInterval currBlock: snpIntervals)
        {
            cumulativeExtent += currBlock.getExtentInBasePairs();
        }
        this.cumulativeExtentInBasePairs = cumulativeExtent;
    }
    
    /**
     * {@inheritDoc}
     */
    public BasePairInterval[] getSnpIntervals()
    {
        return this.snpIntervals;
    }
    
    /**
     * {@inheritDoc}
     */
    public BitSet getStrainBitSet()
    {
        return this.strainBitSet;
    }
    
    /**
     * {@inheritDoc}
     */
    public long getCumulativeExtentInBasePairs()
    {
        return this.cumulativeExtentInBasePairs;
    }
}
