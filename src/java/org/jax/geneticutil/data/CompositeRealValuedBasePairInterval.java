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
