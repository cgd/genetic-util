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
import java.util.Comparator;


/**
 * Holds the data for a SNP
 * @author <A HREF="mailto:keith.sheppard@jax.org">Keith Sheppard</A>
 */
public final class SingleNucleotidePolymorphism implements Serializable
{
    /**
     * every {@link java.io.Serializable} is supposed to have one of these
     */
    private static final long serialVersionUID = -6089220396720958042L;

    /**
     * A comparator that only cares about the bp ordering of snps
     */
    public static final Comparator<SingleNucleotidePolymorphism> POSITION_ONLY_COMPARATOR =
        new Comparator<SingleNucleotidePolymorphism>()
        {
            /**
             * {@inheritDoc}
             */
            public int compare(
                    SingleNucleotidePolymorphism snp1,
                    SingleNucleotidePolymorphism snp2)
            {
                return (int)(snp1.positionInBasePairs - snp2.positionInBasePairs);
            }
        };
    
    /**
     * storing this as a byte 'cause i'm cheap
     * @see #getSnpType()
     */
    private final byte snpTypeOrdinal;
    
    /**
     * @see #getPositionInBasePairs()
     */
    private final long positionInBasePairs;
    
    /**
     * Constructs a new SNP
     * @param snpType
     *          the type of SNP. see {@link #getSnpType()}
     * @param positionInBasePairs
     *          see {@link #getPositionInBasePairs()}
     */
    public SingleNucleotidePolymorphism(
            SnpType snpType,
            long positionInBasePairs)
    {
        this.snpTypeOrdinal = (byte)snpType.ordinal();
        this.positionInBasePairs = positionInBasePairs;
    }
    
    /**
     * Getter for the SNP type.
     * @return the snpType
     */
    public SnpType getSnpType()
    {
        return SnpType.ordinalToSNPEnum(this.snpTypeOrdinal);
    }
    
    /**
     * Getter for the position in base pairs (bp)
     * @return the positionInBasePairs
     */
    public long getPositionInBasePairs()
    {
        return this.positionInBasePairs;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof SingleNucleotidePolymorphism)
        {
            SingleNucleotidePolymorphism otherSnp =
                (SingleNucleotidePolymorphism)obj;
            return this.positionInBasePairs == otherSnp.positionInBasePairs &&
                   this.snpTypeOrdinal == otherSnp.snpTypeOrdinal;
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
        return (int)(this.positionInBasePairs ^ this.snpTypeOrdinal);
    }
}
