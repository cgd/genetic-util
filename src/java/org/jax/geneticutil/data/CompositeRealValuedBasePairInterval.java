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


/**
 * A composite implementation of {@link RealValuedBasePairInterval}
 * @author <A HREF="mailto:keith.sheppard@jax.org">Keith Sheppard</A>
 */
public class CompositeRealValuedBasePairInterval
implements RealValuedBasePairInterval
{
    /**
     * every {@link java.io.Serializable} is supposed to have one of these
     */
    private static final long serialVersionUID = -7050077779476756587L;
    
    private final BasePairInterval delegateInterval;
    
    private final double realValue;

    /**
     * Constructor
     * @param delegateInterval
     *          the interval we delegate almost all calls to
     * @param realValue
     *          the value
     */
    public CompositeRealValuedBasePairInterval(
            BasePairInterval delegateInterval,
            double realValue)
    {
        this.delegateInterval = delegateInterval;
        this.realValue = realValue;
    }
    
    /**
     * Getter for the delegate interval
     * @return the delegate interval
     */
    public BasePairInterval getDelegateInterval()
    {
        return this.delegateInterval;
    }

    /**
     * {@inheritDoc}
     */
    public double getRealValue()
    {
        return this.realValue;
    }

    /**
     * {@inheritDoc}
     */
    public boolean contains(BasePairInterval otherSnpInterval)
    {
        return this.delegateInterval.contains(otherSnpInterval);
    }

    /**
     * {@inheritDoc}
     */
    public boolean contains(
            long otherStartInBasePairs,
            long otherExtentInBasePairs)
    {
        return this.delegateInterval.contains(
                otherStartInBasePairs,
                otherExtentInBasePairs);
    }

    /**
     * {@inheritDoc}
     */
    public int getChromosomeNumber()
    {
        return this.delegateInterval.getChromosomeNumber();
    }

    /**
     * {@inheritDoc}
     */
    public long getEndInBasePairs()
    {
        return this.delegateInterval.getEndInBasePairs();
    }

    /**
     * {@inheritDoc}
     */
    public long getExtentInBasePairs()
    {
        return this.delegateInterval.getExtentInBasePairs();
    }

    /**
     * {@inheritDoc}
     */
    public long getOverlapInBasePairs(
            long otherStartInBasePairs,
            long otherExtentInBasePairs)
    {
        return this.delegateInterval.getOverlapInBasePairs(
                otherStartInBasePairs,
                otherExtentInBasePairs);
    }

    /**
     * {@inheritDoc}
     */
    public long getOverlapInBasePairs(BasePairInterval otherSnpBlock)
    {
        return this.delegateInterval.getOverlapInBasePairs(otherSnpBlock);
    }

    /**
     * {@inheritDoc}
     */
    public long getStartInBasePairs()
    {
        return this.delegateInterval.getStartInBasePairs();
    }
    
    /**
     * {@inheritDoc}
     */
    public boolean intersects(BasePairInterval otherInterval)
    {
        return this.delegateInterval.intersects(otherInterval);
    }

    /**
     * {@inheritDoc}
     */
    public boolean intersects(
            long otherStartInBasePairs,
            long otherExtentInBasePairs)
    {
        return this.delegateInterval.intersects(
                otherStartInBasePairs,
                otherExtentInBasePairs);
    }

    /**
     * Comparison function
     * @param otherInterval
     *          the interval we are comparing ourselves to
     * @return
     *          a total ordering based on
     *          {@link BasePairInterval#compareTo(BasePairInterval)} followed
     *          by {@link #getRealValue()}
     */
    public int compareTo(BasePairInterval otherInterval)
    {
        CompositeRealValuedBasePairInterval otherCompositeRealValuedInterval =
            (CompositeRealValuedBasePairInterval)otherInterval;
        int intervalComparison = this.delegateInterval.compareTo(
                otherCompositeRealValuedInterval.getDelegateInterval());
        if(intervalComparison != 0)
        {
            return intervalComparison;
        }
        else
        {
            return Double.compare(
                    this.getRealValue(),
                    otherCompositeRealValuedInterval.getRealValue());
        }
    }
}
