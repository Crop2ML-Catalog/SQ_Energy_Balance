import numpy 
from math import *
def model_soilevaporation(float diffusionLimitedEvaporation,
                          float energyLimitedEvaporation):
    """

    SoilEvaporation Model
    Author: Peter D. Jamieson, Glen S. Francis, Derick R. Wilson, Robert J. Martin
    Reference:  https://doi.org/10.1016/0168-1923(94)02214-5
    Institution: New Zealand Institute for Crop and Food Research Ltd.,
            New Zealand Institute for Crop and Food Research Ltd.,
            New Zealand Institute for Crop and Food Research Ltd.,
            New Zealand Institute for Crop and Food Research Ltd.
        
    ExtendedDescription: Starting from a soil at field capacity, soil evaporation  is assumed to
                be energy limited during the first phase of evaporation and diffusion limited thereafter.
                Hence, the soil evaporation model considers these two processes taking the minimum between
                the energy limited evaporation (PtSoil) and the diffused limited
                evaporation 
    ShortDescription: Starting from a soil at field capacity, soil evaporation  is assumed to
            be energy limited during the first phase of evaporation and diffusion limited thereafter.
            Hence, the soil evaporation model considers these two processes taking the minimum between
            the energy limited evaporation (PtSoil) and the diffused limited
            evaporation

    """
    cdef float soilEvaporation
    soilEvaporation = min(diffusionLimitedEvaporation, energyLimitedEvaporation)
    return  soilEvaporation


