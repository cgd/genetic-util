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
import java.util.List;


/**
 * @author <A HREF="mailto:keith.sheppard@jax.org">Keith Sheppard</A>
 */
public class SnpIntervalList implements Serializable
{
    /**
     * every {@link java.io.Serializable} is supposed to have one of these
     */
    private static final long serialVersionUID = 8810802880980094L;
    
    final List<BasePairInterval> snpBlocks;
    final long startInBasePairs;
    final long extentInBasePairs;
    
    /**
     * @param snpBlocks
     * @param startInBasePairs
     * @param extentInBasePairs
     */
    public SnpIntervalList(
            List<BasePairInterval> snpBlocks,
            long startInBasePairs,
            long extentInBasePairs)
    {
        this.snpBlocks = snpBlocks;
        this.startInBasePairs = startInBasePairs;
        this.extentInBasePairs = extentInBasePairs;
    }
    
    /**
     * @return the snpBlocks
     */
    public List<BasePairInterval> getSnpBlocks()
    {
        return this.snpBlocks;
    }
    
    /**
     * @return the startInBasePairs
     */
    public long getStartInBasePairs()
    {
        return this.startInBasePairs;
    }
    
    /**
     * @return the extentInBasePairs
     */
    public long getExtentInBasePairs()
    {
        return this.extentInBasePairs;
    }
}
