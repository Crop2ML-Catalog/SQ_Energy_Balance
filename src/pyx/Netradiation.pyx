import numpy 
from math import *
def model_netradiation(float minTair,
                       float maxTair,
                       float albedoCoefficient,
                       float stefanBoltzman,
                       float elevation,
                       float solarRadiation,
                       float vaporPressure,
                       float extraSolarRadiation):
    """

    NetRadiation Model
    Author: Peter D. Jamieson, Glen S. Francis, Derick R. Wilson, Robert J. Martin
    Reference:  https://doi.org/10.1016/0168-1923(94)02214-5
    Institution: New Zealand Institute for Crop and Food Research Ltd.,
            New Zealand Institute for Crop and Food Research Ltd.,
            New Zealand Institute for Crop and Food Research Ltd.,
            New Zealand Institute for Crop and Food Research Ltd.
        
    ExtendedDescription: It is calculated at the surface of the canopy and is givenby the difference between incoming and outgoing radiation of both short
                     and long wavelength radiation 
    ShortDescription: It refers as difference between incoming and outgoing radiation of both short
            and long wavelength radiation 

    """
    cdef float netRadiation
    cdef float netOutGoingLongWaveRadiation
    cdef float Nsr, clearSkySolarRadiation, averageT, surfaceEmissivity, cloudCoverFactor, Nolr
    Nsr = (1.0 - albedoCoefficient) * solarRadiation
    clearSkySolarRadiation = (0.75 + 2 * pow(10.0, -5) * elevation) * extraSolarRadiation
    averageT = (pow(maxTair + 273.16, 4) + pow(minTair + 273.16, 4)) / 2.0
    surfaceEmissivity = (0.34 - 0.14 * sqrt(vaporPressure / 10.0))
    cloudCoverFactor = (1.35 * (solarRadiation / clearSkySolarRadiation) - 0.35)
    Nolr = stefanBoltzman * averageT * surfaceEmissivity * cloudCoverFactor
    netRadiation= Nsr - Nolr
    netOutGoingLongWaveRadiation = Nolr
    return  netRadiation, netOutGoingLongWaveRadiation


