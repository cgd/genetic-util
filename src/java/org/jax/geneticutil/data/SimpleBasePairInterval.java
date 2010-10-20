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

import java.util.Comparator;

/**
 * This class holds information about a contiguous section of SNPs
 * @author <A HREF="mailto:keith.sheppard@jax.org">Keith Sheppard</A>
 */
public class SimpleBasePairInterval implements BasePairInterval
{
    /**
     * every {@link java.io.Serializable} is supposed to have one of these
     */
    private static final long serialVersionUID = -4515977579010507563L;

    /**
     * A comparator that sorts on starting snp before ending snp
     */
    public static Comparator<BasePairInterval> SNP_INTERVAL_COMPARATOR =
        new Comparator<BasePairInterval>()
        {
            public int compare(
                    BasePairInterval snpBlock1,
                    BasePairInterval snpBlock2)
            {
                int chromosomeDifference =
                    snpBlock1.getChromosomeNumber() -
                    snpBlock2.getChromosomeNumber();
                
                if(chromosomeDifference != 0)
                {
                    return chromosomeDifference;
                }
                else
                {
                    int startingSnpDifference =
                        (int)(snpBlock1.getStartInBasePairs() -
                        snpBlock2.getStartInBasePairs());
                    if(startingSnpDifference != 0)
                    {
                        return startingSnpDifference;
                    }
                    else
                    {
                        return (int)(snpBlock1.getExtentInBasePairs() -
                               snpBlock2.getExtentInBasePairs());
                    }
                }
            }
        };
    
    /**
     * @see #getStartInBasePairs()
     */
    protected final long startInBasePairs;
    
    /**
     * @see #getExtentInBasePairs()
     */
    protected final long extentInBasePairs;
    
    /**
     * @see #getChromosomeNumber()
     */
    protected final int chromosomeNumber;
    
    /**
     * Constructor where the chromosome # is a don't care
     * @param startInBasePairs
     *          the starting snp for this block
     * @param extentInBasePairs
     *          the range of this block in SNPs
     */
    public SimpleBasePairInterval(
            long startInBasePairs,
            long extentInBasePairs)
    {
        this(Integer.MAX_VALUE, startInBasePairs, extentInBasePairs);
    }

    /**
     * Constructor
     * @param chromosomeNumber
     *          the chromosome number for this block
     * @param startInBasePairs
     *          the starting snp for this block
     * @param extentInBasePairs
     *          the range of this block in SNPs
     */
    public SimpleBasePairInterval(
            int chromosomeNumber,
            long startInBasePairs,
            long extentInBasePairs)
    {
        this.chromosomeNumber = chromosomeNumber;
        this.startInBasePairs = startInBasePairs;
        this.extentInBasePairs = extentInBasePairs;
    }
    
    /**
     * {@inheritDoc}
     */
    public long getStartInBasePairs()
    {
        return this.startInBasePairs;
    }
    
    /**
     * {@inheritDoc}
     */
    public long getEndInBasePairs()
    {
        return SimpleBasePairInterval.getEndInBasePairs(
                this.startInBasePairs,
                this.extentInBasePairs);
    }
    
    private static long getEndInBasePairs(
            long startInBasePairs,
            long extentInBasePairs)
    {
        return startInBasePairs + extentInBasePairs - 1;
    }

    /**
     * {@inheritDoc}
     */
    public long getExtentInBasePairs()
    {
        return this.extentInBasePairs;
    }

    /**
     * {@inheritDoc}
     */
    public int getChromosomeNumber()
    {
        return this.chromosomeNumber;
    }
    
    /**
     * {@inheritDoc}
     */
    public boolean contains(BasePairInterval otherSnpInterval)
    {
        return this.contains(
                otherSnpInterval.getStartInBasePairs(),
                otherSnpInterval.getExtentInBasePairs());
    }
    
    /**
     * {@inheritDoc}
     */
    public boolean contains(
            long otherStartInBasePairs,
            long otherExtentInBasePairs)
    {
        return this.startInBasePairs <= otherStartInBasePairs &&
               this.getEndInBasePairs() >=
                   SimpleBasePairInterval.getEndInBasePairs(
                           otherStartInBasePairs,
                           otherExtentInBasePairs);
    }
    
    /**
     * {@inheritDoc}
     */
    public boolean intersects(BasePairInterval otherInterval)
    {
        return this.intersects(
                otherInterval.getStartInBasePairs(),
                otherInterval.getExtentInBasePairs());
    }
    
    /**
     * {@inheritDoc}
     */
    public boolean intersects(
            long otherStartInBasePairs,
            long otherExtentInBasePairs)
    {
        return this.getEndInBasePairs() >= otherStartInBasePairs &&
               this.getStartInBasePairs() <=
                   SimpleBasePairInterval.getEndInBasePairs(
                           otherExtentInBasePairs,
                           otherStartInBasePairs);
    }
    
    /**
     * {@inheritDoc}
     */
    public long getOverlapInBasePairs(
            long otherStartInBasePairs,
            long otherExtentInBasePairs)
    {
        if(this.intersects(otherStartInBasePairs, otherExtentInBasePairs))
        {
            long overlapStartInBasePairs = Math.max(
                    this.startInBasePairs,
                    otherStartInBasePairs);
            long overlapEndInBasePairs = Math.min(
                    this.getEndInBasePairs(),
                    SimpleBasePairInterval.getEndInBasePairs(
                            otherExtentInBasePairs,
                            otherStartInBasePairs));
            
            return 1 + overlapEndInBasePairs - overlapStartInBasePairs;
        }
        else
        {
            return 0;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public long getOverlapInBasePairs(
            BasePairInterval otherSnpBlock)
    {
        if(this.getChromosomeNumber() == otherSnpBlock.getChromosomeNumber())
        {
            return this.getOverlapInBasePairs(
                    otherSnpBlock.getStartInBasePairs(),
                    otherSnpBlock.getExtentInBasePairs());
        }
        else
        {
            // if the chromosome is different then there is no overlap
            return 0L;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object otherBlockObject)
    {
        if(otherBlockObject instanceof SimpleBasePairInterval)
        {
            SimpleBasePairInterval otherBlock =
                (SimpleBasePairInterval)otherBlockObject;
            
            return this.compareTo(otherBlock) == 0;
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
    public int hashCode()
    {
        return (int)this.extentInBasePairs ^ (int)this.startInBasePairs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return "SNP Block: start=" + this.getStartInBasePairs() +
               ", end=" + this.getEndInBasePairs() +
               ", extent=" + this.getExtentInBasePairs();
    }
    
    /**
     * {@inheritDoc}
     */
    public int compareTo(BasePairInterval otherSnpBlock)
    {
        return SNP_INTERVAL_COMPARATOR.compare(this, otherSnpBlock);
    }
}
