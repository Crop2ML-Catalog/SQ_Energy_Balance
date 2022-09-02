import numpy 
from math import *
def model_canopytemperature(float minTair,
                            float maxTair,
                            float cropHeatFlux,
                            float conductance,
                            float lambdaV,
                            float rhoDensityAir,
                            float specificHeatCapacityAir):
    """

    CanopyTemperature Model
    Author: Peter D. Jamieson, Glen S. Francis, Derick R. Wilson, Robert J. Martin
    Reference: https://doi.org/10.1016/0168-1923(94)02214-5
    Institution: New Zealand Institute for Crop and Food Research Ltd.,
            New Zealand Institute for Crop and Food Research Ltd.,
            New Zealand Institute for Crop and Food Research Ltd.,
            New Zealand Institute for Crop and Food Research Ltd.
        
    ExtendedDescription: It is calculated from the crop heat flux and the boundary layer conductance 
    ShortDescription: It is calculated from the crop heat flux and the boundary layer conductance 

    """
    cdef float minCanopyTemperature
    cdef float maxCanopyTemperature
    minCanopyTemperature = minTair + cropHeatFlux / ((rhoDensityAir * specificHeatCapacityAir * conductance / lambdaV) * 1000.0)
    maxCanopyTemperature = maxTair + cropHeatFlux / ((rhoDensityAir * specificHeatCapacityAir * conductance / lambdaV) * 1000.0)
    return  minCanopyTemperature, maxCanopyTemperature


