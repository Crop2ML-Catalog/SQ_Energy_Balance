import numpy 
from math import *
def model_potentialtranspiration(float evapoTranspiration=830.958,
                                 float tau=0.9983):
    """

    PotentialTranspiration Model
    Author: Peter D. Jamieson, Glen S. Francis, Derick R. Wilson, Robert J. Martin
    Reference: Modelling energy balance in the wheat crop model SiriusQuality2:
            Evapotranspiration and canopy and soil temperature calculations
    Institution: New Zealand Institute for Crop and Food Research Ltd.,
            New Zealand Institute for Crop and Food Research Ltd.,
            New Zealand Institute for Crop and Food Research Ltd.,
            New Zealand Institute for Crop and Food Research Ltd.
        
    ExtendedDescription: SiriusQuality2 uses availability of water from the soil reservoir as a method to restrict
                    transpiration as soil moisture is depleted 
    ShortDescription: It uses the availability of water from the soil reservoir as a method to restrict
            transpiration as soil moisture is depleted

    """
    cdef float potentialTranspiration
    potentialTranspiration= evapoTranspiration * (1.0 - tau)
    return  potentialTranspiration
