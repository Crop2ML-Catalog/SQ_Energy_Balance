import numpy 
from math import *
def model_diffusionlimitedevaporation(float deficitOnTopLayers=5341.0,
                                      float soilDiffusionConstant=4.2):
    """

    DiffusionLimitedEvaporation Model
    Author: Peter D. Jamieson, Glen S. Francis, Derick R. Wilson, Robert J. Martin
    Reference: Modelling energy balance in the wheat crop model SiriusQuality2:
            Evapotranspiration and canopy and soil temperature calculations
    Institution: New Zealand Institute for Crop and Food Research Ltd.,
            New Zealand Institute for Crop and Food Research Ltd.,
            New Zealand Institute for Crop and Food Research Ltd.,
            New Zealand Institute for Crop and Food Research Ltd.
        
    ExtendedDescription: the evaporation from the diffusion limited soil 
    ShortDescription: It calculates the diffusion limited evaropration
        

    """
    cdef float diffusionLimitedEvaporation
    if (deficitOnTopLayers / 1000.0 <= 0.0): diffusionLimitedEvaporation = 8.3 * 1000.0
    else :
        if (deficitOnTopLayers / 1000.0 < 25.0):
            diffusionLimitedEvaporation = (2.0 * soilDiffusionConstant * soilDiffusionConstant / (deficitOnTopLayers / 1000.0)) * 1000.0
        else:
            diffusionLimitedEvaporation = 0.0
    return  diffusionLimitedEvaporation
