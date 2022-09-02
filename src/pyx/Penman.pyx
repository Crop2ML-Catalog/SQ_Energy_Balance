import numpy 
from math import *
def model_penman(float evapoTranspirationPriestlyTaylor,
                 float hslope,
                 float VPDair,
                 float psychrometricConstant,
                 float Alpha,
                 float lambdaV,
                 float rhoDensityAir,
                 float specificHeatCapacityAir,
                 float conductance):
    """

    Penman Model
    Author: Peter D. Jamieson, Glen S. Francis, Derick R. Wilson, Robert J. Martin
    Reference:  https://doi.org/10.1016/0168-1923(94)02214-5
    Institution: New Zealand Institute for Crop and Food Research Ltd.,
            New Zealand Institute for Crop and Food Research Ltd.,
            New Zealand Institute for Crop and Food Research Ltd.,
            New Zealand Institute for Crop and Food Research Ltd.
        
    ExtendedDescription: It uses Penmann-Monteith method vase on the availability of wind and vapor pressure daily data
    ShortDescription: It uses Penmann-Monteith method vase on the availability of wind and vapor pressure daily data

    """
    cdef float evapoTranspirationPenman
    evapoTranspirationPenman = evapoTranspirationPriestlyTaylor / Alpha + 1000.0 * ((rhoDensityAir * specificHeatCapacityAir * VPDair * conductance) / (lambdaV * (hslope + psychrometricConstant)))
    return  evapoTranspirationPenman


