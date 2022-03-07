import numpy 
from math import *
def model_penman(float evapoTranspirationPriestlyTaylor=449.367,
                 float hslope=0.584,
                 float VPDair=2.19,
                 float psychrometricConstant=0.66,
                 float Alpha=1.5,
                 float lambdaV=2.454,
                 float rhoDensityAir=1.225,
                 float specificHeatCapacityAir=0.00101,
                 float conductance=598.685):
    """

    Penman Model
    Author: Peter D. Jamieson, Glen S. Francis, Derick R. Wilson, Robert J. Martin
    Reference: Modelling energy balance in the wheat crop model SiriusQuality2:
            Evapotranspiration and canopy and soil temperature calculations
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
