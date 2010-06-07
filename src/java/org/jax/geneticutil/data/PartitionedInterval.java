/*
 * Copyright (c) 2008 The Jackson Laboratory
 * 
 * This software was developed by Gary Churchill's Lab at The Jackson
 * Laboratory (see http://research.jax.org/faculty/churchill).
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

import org.jax.util.datastructure.SetUtilities;

/**
 * A snp interval that's also a binary partition (actually its a partitioned
 * set of one interval since we implement {@link PartitionedIntervalSet})
 * @author <A HREF="mailto:keith.sheppard@jax.org">Keith Sheppard</A>
 */
public class PartitionedInterval
extends SimpleBasePairInterval
implements PartitionedIntervalSet
{
    /**
     * every {@link java.io.Serializable} is supposed to have one of these
     */
    private static final long serialVersionUID = 3635617408353809540L;
    
    /**
     * @see #getStrainBitSet()
     */
    private final BitSet strainBitSet;
    
    /**
     * Constructor
     * @param chromosomeNumer
     *          see {@link #getChromosomeNumber()}
     * @param startInBasePairs
     *          the starting point for this haplotype
     * @param extentInBasePairs
     *          the range of this haplotype in SNPs
     * @param strainBitSet
     *          see {@link #getStrainBitSet()}
     */
    public PartitionedInterval(
            int chromosomeNumer,
            long startInBasePairs,
            long extentInBasePairs,
            BitSet strainBitSet)
    {
        super(chromosomeNumer, startInBasePairs, extentInBasePairs);
        this.strainBitSet = strainBitSet;
    }
    
    /**
     * Getter for the strains that have this haplotype
     * @return
     *          the strains with this haplotype
     */
    public BitSet getStrainBitSet()
    {
        return this.strainBitSet;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object otherHaplotypeObject)
    {
        if(otherHaplotypeObject instanceof PartitionedInterval)
        {
            PartitionedInterval otherHaplotype = (PartitionedInterval)otherHaplotypeObject;
            
            return this.compareTo(otherHaplotype) == 0;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(BasePairInterval otherSnpBlock)
    {
        PartitionedInterval otherHaplotypeBlock = (PartitionedInterval)otherSnpBlock;
        int superComparison = super.compareTo(otherSnpBlock);
        if(superComparison != 0)
        {
            return superComparison;
        }
        else
        {
            return SetUtilities.BIT_SET_COMPARATOR.compare(
                    this.strainBitSet,
                    otherHaplotypeBlock.strainBitSet);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode()
    {
        return super.hashCode() ^ this.strainBitSet.hashCode();
    }
    
    /**
     * {@inheritDoc}
     */
    public long getCumulativeExtentInBasePairs()
    {
        return this.extentInBasePairs;
    }
    
    /**
     * {@inheritDoc}
     */
    public BasePairInterval[] getSnpIntervals()
    {
        return new BasePairInterval[] {this};
    }
}
