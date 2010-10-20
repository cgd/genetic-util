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


/**
 * Holds all the data that we care about for a chromosome.
 * @author <A HREF="mailto:keith.sheppard@jax.org">Keith Sheppard</A>
 */
public class StrainChromosome
implements Comparable<StrainChromosome>, Serializable
{
    /**
     * every {@link java.io.Serializable} is supposed to have one of these
     */
    private static final long serialVersionUID = 9073468292285619566L;

    /**
     * @see #getStrainName()
     */
    private final String strainName;
    
    /**
     * @see #getChromosomeNumber()
     */
    private final int chromosomeNumber;
    
    /**
     * @see #getSingleNucleotidePolymorphisms()
     */
    private SingleNucleotidePolymorphism[] singleNucleotidePolymorphisms;
    
    /**
     * Constructor
     * @param strainName
     *          see {@link #getStrainName()}
     * @param chromosomeNumber
     *          see {@link #getChromosomeNumber()}
     */
    public StrainChromosome(String strainName, int chromosomeNumber)
    {
        this.strainName = strainName;
        this.chromosomeNumber = chromosomeNumber;
    }

    /**
     * Get the name of the strain that this chromosome belongs to
     * @return the strainName
     */
    public String getStrainName()
    {
        return this.strainName;
    }

    /**
     * Get the chromosome number
     * @return the chromosomeNumber
     */
    public int getChromosomeNumber()
    {
        return this.chromosomeNumber;
    }

    /**
     * This equals comparison just delegates to {@link #equals(StrainChromosome)}
     * @param chromosome
     *          the {@link StrainChromosome} to compare
     * @return
     *          true iff we're equal
     */
    @Override
    public boolean equals(Object chromosome)
    {
        return this.equals((StrainChromosome)chromosome);
    }
    
    /**
     * Compares this chromosome for equaltiy using strain name and chromosome number
     * @param otherChromosome
     *          the other {@link StrainChromosome} that we're comparing to
     * @return
     *          true iff we're equal
     */
    public boolean equals(StrainChromosome otherChromosome)
    {
        return this.compareTo(otherChromosome) == 0;
    }

    /**
     * we're overriding this because we overrode {@link #equals(Object)}. it's
     * part of being a good object.
     * @return
     *          the hash code
     * @see Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        return this.getStrainName().hashCode() + this.getChromosomeNumber();
    }

    /**
     * Get the SNPs that make up this chromosome
     * @return the singleNucleotidePolymorphisms
     */
    public SingleNucleotidePolymorphism[] getSingleNucleotidePolymorphisms()
    {
        return this.singleNucleotidePolymorphisms;
    }

    /**
     * Setter for the SNPs that make up this chromosome.
     * @param singleNucleotidePolymorphisms the singleNucleotidePolymorphisms to set
     */
    public void setSingleNucleotidePolymorphisms(
            SingleNucleotidePolymorphism[] singleNucleotidePolymorphisms)
    {
        this.singleNucleotidePolymorphisms = singleNucleotidePolymorphisms;
    }

    /**
     * use a pretty arbitrary comparison. note that this comparison only looks
     * at strain name and chromosome number, ignoring the snp values
     * @param otherChromosome
     *          the chromosome that we're being compared to
     * @return
     *          see {@link java.lang.Comparable#compareTo(Object)} for
     *          the rules
     */
    public int compareTo(StrainChromosome otherChromosome)
    {
        int strainCompare = this.getStrainName().compareTo(
                otherChromosome.getStrainName());
        if(strainCompare == 0)
        {
            return this.getChromosomeNumber() - otherChromosome.getChromosomeNumber();
        }
        else
        {
            return strainCompare;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return "Chromosome Strain: " + this.strainName +
               ", Chromosome #:" + this.chromosomeNumber;
    }
}
