import numpy 
from math import *
def model_priestlytaylor(float netRadiationEquivalentEvaporation=638.142,
                         float hslope=0.584,
                         float psychrometricConstant=0.66,
                         float Alpha=1.5):
    """

    evapoTranspirationPriestlyTaylor  Model
    Author: Peter D. Jamieson, Glen S. Francis, Derick R. Wilson, Robert J. Martin
    Reference: Modelling energy balance in the wheat crop model SiriusQuality2:
            Evapotranspiration and canopy and soil temperature calculations
    Institution: New Zealand Institute for Crop and Food Research Ltd.,
            New Zealand Institute for Crop and Food Research Ltd.,
            New Zealand Institute for Crop and Food Research Ltd.,
            New Zealand Institute for Crop and Food Research Ltd.
        
    ExtendedDescription: Calculate Energy Balance 
    ShortDescription: It uses Priestly-Taylor method

    """
    cdef float evapoTranspirationPriestlyTaylor
    evapoTranspirationPriestlyTaylor = max((Alpha * hslope * (netRadiationEquivalentEvaporation) / (hslope + psychrometricConstant)), 0.0)
    return  evapoTranspirationPriestlyTaylor
