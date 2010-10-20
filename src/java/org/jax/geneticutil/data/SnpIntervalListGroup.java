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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * A class that can be used to hold a reference to a number of SNP block
 * lists which all span the same region of the genome
 * @author <A HREF="mailto:keith.sheppard@jax.org">Keith Sheppard</A>
 */
public class SnpIntervalListGroup implements Serializable
{
    /**
     * every {@link java.io.Serializable} is supposed to have one of these
     */
    private static final long serialVersionUID = 81080280780980094L;
    
    final Map<String, List<BasePairInterval>> snpBlocksMap;
    final long startInBasePairs;
    final long extentInBasePairs;
    
    /**
     * Constructor
     * @param snpBlocksMap
     *          the mapping from name to snp block list that defines
     *          this class
     */
    public SnpIntervalListGroup(
            Map<String, SnpIntervalList> snpBlocksMap)
    {
        Map<String, List<BasePairInterval>> ibsBlocksMap =
            new HashMap<String, List<BasePairInterval>>();
        
        long minStartPositionInBasePairs = Long.MAX_VALUE;
        long maxEndPositionInBasePairs = 0L;
        for(Entry<String, SnpIntervalList> ibsListEntry: snpBlocksMap.entrySet())
        {
            SnpIntervalList ibsList = ibsListEntry.getValue();
            long newMinPositionInBasePairs = ibsList.getStartInBasePairs();
            long newEndPositionInBasePairs =
                newMinPositionInBasePairs +
                ibsList.getExtentInBasePairs();
            
            if(newMinPositionInBasePairs < minStartPositionInBasePairs)
            {
                minStartPositionInBasePairs = newMinPositionInBasePairs;
            }
            
            if(newEndPositionInBasePairs > maxEndPositionInBasePairs)
            {
                maxEndPositionInBasePairs = newEndPositionInBasePairs;
            }
            
            ibsBlocksMap.put(
                    ibsListEntry.getKey(),
                    ibsList.getSnpBlocks());
        }
        
        this.snpBlocksMap = ibsBlocksMap;
        this.startInBasePairs = minStartPositionInBasePairs;
        this.extentInBasePairs = maxEndPositionInBasePairs - minStartPositionInBasePairs;
    }
    
    /**
     * Constructor
     * @param snpBlocksMap
     *          the mapping from name to snp block list that defines
     *          this class
     * @param startInBasePairs
     *          the starting position in base pairs
     * @param extentInBasePairs
     *          the extent in base pairs
     */
    public SnpIntervalListGroup(
            Map<String, List<BasePairInterval>> snpBlocksMap,
            long startInBasePairs,
            long extentInBasePairs)
    {
        this.snpBlocksMap = snpBlocksMap;
        this.startInBasePairs = startInBasePairs;
        this.extentInBasePairs = extentInBasePairs;
    }
    
    /**
     * Getter for the name to SNP block list mapping
     * @return the snpBlocks
     */
    public Map<String, List<BasePairInterval>> getSnpBlocksMap()
    {
        return this.snpBlocksMap;
    }
    
    /**
     * Getter for the starting position in base pairs
     * @return the startInBasePairs
     */
    public long getStartInBasePairs()
    {
        return this.startInBasePairs;
    }
    
    /**
     * Getter for the extent in base pairs
     * @return the extentInBasePairs
     */
    public long getExtentInBasePairs()
    {
        return this.extentInBasePairs;
    }
}
