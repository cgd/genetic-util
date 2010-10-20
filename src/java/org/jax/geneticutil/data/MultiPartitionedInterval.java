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

import java.util.Arrays;

import org.jax.util.datastructure.SequenceUtilities;

/**
 * An similar to {@link PartitionedInterval} except that this class can
 * represent several partitions in the same interval
 * @author <A HREF="mailto:keith.sheppard@jax.org">Keith Sheppard</A>
 */
public class MultiPartitionedInterval
extends SimpleBasePairInterval
implements MultiGroupStrainPartition
{
    /**
     * every {@link java.io.Serializable} is supposed to have one of these
     */
    private static final long serialVersionUID = 3349887323263611392L;

    /**
     * @see #getStrainGroups()
     */
    private final short[] strainGroups;
    
    /**
     * Constructor
     * @param chromosomeNumer
     *          see {@link #getChromosomeNumber()}
     * @param startInBasePairs
     *          the starting point for this haplotype
     * @param extentInBasePairs
     *          the range of this haplotype in SNPs
     * @param strainGroups
     *          see {@link #getStrainGroups()}.
     */
    public MultiPartitionedInterval(
            int chromosomeNumer,
            long startInBasePairs,
            long extentInBasePairs,
            short[] strainGroups)
    {
        super(chromosomeNumer, startInBasePairs, extentInBasePairs);
        this.strainGroups = strainGroups;
    }
    
    /**
     * {@inheritDoc}
     */
    public short[] getStrainGroups()
    {
        return this.strainGroups;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object otherHaplotypeObject)
    {
        if(otherHaplotypeObject instanceof MultiPartitionedInterval)
        {
            MultiPartitionedInterval otherHaplotype = (MultiPartitionedInterval)otherHaplotypeObject;
            
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
        MultiPartitionedInterval otherHaplotypeBlock = (MultiPartitionedInterval)otherSnpBlock;
        int superComparison = super.compareTo(otherSnpBlock);
        if(superComparison != 0)
        {
            return superComparison;
        }
        else
        {
            return SequenceUtilities.SHORT_ARRAY_COMPARATOR.compare(
                    this.getStrainGroups(),
                    otherHaplotypeBlock.getStrainGroups());
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode()
    {
        return super.hashCode() ^
               Arrays.asList(this.strainGroups.hashCode()).hashCode();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder(super.toString());
        
        sb.append(", strainGroups=[");
        for(int i = 0; i < this.strainGroups.length; i++)
        {
            if(i >= 1)
            {
                sb.append(", ");
            }
            
            sb.append(this.strainGroups[i]);
        }
        sb.append("]");
        
        return sb.toString();
    }
}
