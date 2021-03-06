import numpy 
from math import *
def model_netradiationequivalentevaporation(float lambdaV=2.454,
                                            float netRadiation=1.566):
    """

    NetRadiationEquivalentEvaporation Model
    Author: Peter D. Jamieson, Glen S. Francis, Derick R. Wilson, Robert J. Martin
    Reference: Modelling energy balance in the wheat crop model SiriusQuality2:
            Evapotranspiration and canopy and soil temperature calculations
    Institution: New Zealand Institute for Crop and Food Research Ltd.,
            New Zealand Institute for Crop and Food Research Ltd.,
            New Zealand Institute for Crop and Food Research Ltd.,
            New Zealand Institute for Crop and Food Research Ltd.
        
    ExtendedDescription:  It is given by dividing net radiation by latent heat of vaporization of water 
    ShortDescription: It is given by dividing net radiation by latent heat of vaporization of water

    """
    cdef float netRadiationEquivalentEvaporation
    netRadiationEquivalentEvaporation = netRadiation / lambdaV * 1000.0
    return  netRadiationEquivalentEvaporation
