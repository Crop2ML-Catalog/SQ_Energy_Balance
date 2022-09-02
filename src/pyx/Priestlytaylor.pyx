import numpy 
from math import *
def model_priestlytaylor(float netRadiationEquivalentEvaporation,
                         float hslope,
                         float psychrometricConstant,
                         float Alpha):
    """

    evapoTranspirationPriestlyTaylor  Model
    Author: Peter D. Jamieson, Glen S. Francis, Derick R. Wilson, Robert J. Martin
    Reference:  https://doi.org/10.1016/0168-1923(94)02214-5
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


