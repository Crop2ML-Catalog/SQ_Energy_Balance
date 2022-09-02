import numpy 
from math import *
def model_ptsoil(float evapoTranspirationPriestlyTaylor,
                 float Alpha,
                 float tau,
                 float tauAlpha):
    """

    PtSoil EnergyLimitedEvaporation Model
    Author: Peter D. Jamieson, Glen S. Francis, Derick R. Wilson, Robert J. Martin
    Reference: https://doi.org/10.1016/0168-1923(94)02214-5
    Institution: New Zealand Institute for Crop and Food Research Ltd.,
            New Zealand Institute for Crop and Food Research Ltd.,
            New Zealand Institute for Crop and Food Research Ltd.,
            New Zealand Institute for Crop and Food Research Ltd.
        
    ExtendedDescription: Evaporation from the soil in the energy-limited stage 
    ShortDescription: Evaporation from the soil in the energy-limited stage

    """
    cdef float energyLimitedEvaporation
    cdef float AlphaE
    if (tau < tauAlpha):
        AlphaE = 1.0
    else :
        AlphaE = Alpha - ((Alpha - 1.0) * (1.0 - tau) / (1.0 - tauAlpha))
    energyLimitedEvaporation= (evapoTranspirationPriestlyTaylor / Alpha) * AlphaE * tau
    return  energyLimitedEvaporation


