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


/**
 * A simple representation for a set of intervals that all have the same
 * binary partition.
 * @author <A HREF="mailto:keith.sheppard@jax.org">Keith Sheppard</A>
 */
public interface PartitionedIntervalSet extends BinaryStrainPartition
{
    /**
     * Getter for all of the intervals in this partition
     * @return the intervals
     */
    public BasePairInterval[] getSnpIntervals();
    
    /**
     * Getter for the cumulative extent (this is a shortcut for adding
     * up all of the extents from {@link #getSnpIntervals()} yourself)
     * @return
     *          the cumulative extent
     */
    public long getCumulativeExtentInBasePairs();
}
