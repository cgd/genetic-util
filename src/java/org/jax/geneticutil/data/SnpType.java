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
 * An enum for single nucleotide polymorphism (SNP) types.
 * @author <A HREF="mailto:keith.sheppard@jax.org">Keith Sheppard</A>
 */
public enum SnpType
{
    /**
     * enum for 'A' SNPs
     */
    A_SNP,
    
    /**
     * enum for 'T' SNPs
     */
    T_SNP,
    
    /**
     * enum for 'C' SNPs
     */
    C_SNP,
    
    /**
     * enum for 'G' SNPs
     */
    G_SNP;
    
    /**
     * Convert the given string to a SNP value
     * @param snpString
     *          the string. should be of length 1
     * @return
     *          the enum
     * @throws IllegalArgumentException
     *          if length isn't 1 or if {@link #snpCharToSNPEnum(char)}
     *          throws an exception on the strings char
     */
    public static SnpType snpStringToSNPEnum(String snpString) throws IllegalArgumentException
    {
        if(snpString.length() != 1)
        {
            throw new IllegalArgumentException(
                    "SNP string \"" + snpString +
                    "\" cannot be converted to a SNP Enum since its length " +
                    "is greater than 1");
        }
        else
        {
            return snpCharToSNPEnum(snpString.charAt(0));
        }
    }
    
    /**
     * Convert the given character to a SNP value
     * @param snpChar
     *          the SNP char to convert
     * @return
     *          the enum
     * @throws IllegalArgumentException
     *          if snpChar isn't one of A, T, C, G (case insensitive)
     */
    public static SnpType snpCharToSNPEnum(char snpChar) throws IllegalArgumentException
    {
        char upperSNPChar = Character.toUpperCase(snpChar);
        
        switch(upperSNPChar)
        {
            case 'A':
                return A_SNP;
            case 'T':
                return T_SNP;
            case 'C':
                return C_SNP;
            case 'G':
                return G_SNP;
            default:
                throw new IllegalArgumentException(
                        "cannot convert \'" + snpChar +
                        "\' to a SNP enum");
        }
    }
    
    /**
     * convert the ordinal to an enum
     * @param ordinal
     *          the ordinal... see {@link SnpType#ordinal()}
     * @return
     *          the enum  
     */
    public static SnpType ordinalToSNPEnum(int ordinal)
    {
        return SnpType.values()[ordinal];
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        switch(this)
        {
            case A_SNP: return "A";
            case T_SNP: return "T";
            case C_SNP: return "C";
            case G_SNP: return "G";
            default: throw new IllegalStateException("Don't know who I am!!");
        }
    }
}